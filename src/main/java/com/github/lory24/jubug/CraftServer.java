package com.github.lory24.jubug;

import com.github.lory24.jubug.util.PacketsCheckingUtils;
import com.github.lory24.jubug.util.ServerProprieties;
import com.github.lory24.jubug.util.player.CraftPlayer;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "MismatchedQueryAndUpdateOfCollection"})
public abstract class CraftServer {
    private final Logger logger;
    private ServerSocket serverSocket;
    @Getter private final List<CraftPlayer> players = new ArrayList<>();
    private final HashMap<CraftPlayer, Thread> playersThreads;
    private ServerProprieties serverProprieties;
    private final int PROTOCOL = 47;

    public CraftServer() {
        this.logger = new Logger(null, System.out);
        this.playersThreads = new HashMap<>();
    }

    @SneakyThrows
    protected void runServer() {
        getLogger().info("Loading default configuration");
        this.serverProprieties = new ServerProprieties();

        getLogger().info("Starting NET server...");
        this.serverSocket = new ServerSocket(serverProprieties.getPort());

        Thread connectionWaitingThread = new Thread(this::waitForConnections);
        connectionWaitingThread.start();
        getLogger().info("Server is waiting for connections...");
    }

    @SneakyThrows
    private void waitForConnections() {
        while (true) {
            Socket socket = serverSocket.accept();
            getLogger().info("New connection from " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
            Thread thread = new Thread(() -> doConnectionActions(socket));
            thread.start();
        }
    }

    @SneakyThrows
    private void doConnectionActions(Socket socket) {
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream()); // Server <- Client
        DataOutputStream dataOutputStream =
                new DataOutputStream(socket.getOutputStream()); // Server -> Client

        if (PacketsCheckingUtils.checkPacketLengthError(socket, dataInputStream) || PacketsCheckingUtils.checkPacketError(socket, dataInputStream, 0x00)) return;

        int protocolVersion = ServerDataUtil.readVarInt(dataInputStream);
        ServerDataUtil.readString(dataInputStream);
        dataInputStream.readUnsignedShort();

        int nextState = ServerDataUtil.readVarInt(dataInputStream);
        if (nextState == 0x1) { // Status
            try {
                manageStatusRequest(socket, dataInputStream, dataOutputStream, protocolVersion);
            } catch (Exception ignored) { }
        } else if (nextState == 0x2) { // Login
            makePlayerJoin(socket);
        } else {
            socket.close();
            getLogger().info("Connection from " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + " closed: Bad packet!");
        }
    }

    private void manageStatusRequest(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream, int protocolVersion) throws Exception {

        if (PacketsCheckingUtils.checkPacketLengthError(socket, dataInputStream) || PacketsCheckingUtils.checkPacketError(socket, dataInputStream, 0x00)) return;

        // SEND A RESPONSE
        String jsonResp = "{\"version\":{\"name\":\"Jubug 1.8\",\"protocol\":" + PROTOCOL + "},\"players\":{\"max\":" + serverProprieties.getMaxPlayers() + ",\"online\":" + players.size() + "}," +
                "\"description\":{\"text\":\"%s\"}}";
        jsonResp = protocolVersion == PROTOCOL ? String.format(jsonResp, serverProprieties.getMotd()) : String.format(jsonResp, "\u00a7cBRO CHANGE VERSION NOW!");
        ServerDataUtil.writeVarInt(dataOutputStream, 3 + jsonResp.getBytes().length);
        ServerDataUtil.writeVarInt(dataOutputStream, 0x00);
        ServerDataUtil.writeString(dataOutputStream, jsonResp);

        // READ THE PING
        if (PacketsCheckingUtils.checkPacketLengthError(socket, dataInputStream) || PacketsCheckingUtils.checkPacketError(socket, dataInputStream, 0x01)) return;
        long v = dataInputStream.readLong();

        // SEND THE PONG
        ServerDataUtil.writeVarInt(dataOutputStream, String.valueOf(0x01).getBytes(StandardCharsets.UTF_8).length + String.valueOf(v)
                .getBytes(StandardCharsets.UTF_8).length);
        ServerDataUtil.writeVarLong(dataOutputStream, 0x01);
        dataOutputStream.writeLong(v);
        socket.close();
    }

    @SneakyThrows
    private void makePlayerJoin(Socket socket) {
        socket.close();
    }

    public Logger getLogger() {
        return this.logger;
    }
}
