package com.github.lory24.jubug;

import com.github.lory24.jubug.util.ServerProprieties;
import com.github.lory24.jubug.util.SizeFetcher;
import com.github.lory24.jubug.util.player.CraftPlayer;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.instrument.Instrumentation;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class CraftServer {
    private final Logger logger;
    private ServerSocket serverSocket;
    private List<CraftPlayer> players = new ArrayList<>();
    private HashMap<CraftPlayer, Thread> playersThreads;
    private ServerProprieties serverProprieties;

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

        if (checkPacketLengthError(socket, dataInputStream) || checkPacketError(socket, dataInputStream, 0x00)) return;

        int protocolVersion = ServerDataUtil.readVarInt(dataInputStream);
        String serverAddress = ServerDataUtil.readString(dataInputStream);
        int port = dataInputStream.readUnsignedShort();

        int nextState = ServerDataUtil.readVarInt(dataInputStream);
        if (nextState == 0x1) { // Status
            try {
                manageStatusRequest(socket, dataInputStream, dataOutputStream);
            } catch (Exception ignored) { }
        } else if (nextState == 0x2) { // Login
            makePlayerJoin(socket);
        } else {
            socket.close();
            getLogger().info("Connection from " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + " closed: Bad packet!");
        }
    }

    private void manageStatusRequest(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws Exception {

        if (checkPacketLengthError(socket, dataInputStream) || checkPacketError(socket, dataInputStream, 0x00)) return;

        // SEND A RESPONSE
        String jsonResp = "{\"version\":{\"name\":\"Jubug 1.8\",\"protocol\":47},\"players\":{\"max\":" + serverProprieties.getMaxPlayers() +
                ",\"online\":" + players.size() + "},\"description\":{\"text\":\"" + serverProprieties.getMotd() + "\"}}";
        ServerDataUtil.writeVarInt(dataOutputStream, 3 + jsonResp.getBytes().length);
        ServerDataUtil.writeVarInt(dataOutputStream, 0x00);
        ServerDataUtil.writeString(dataOutputStream, jsonResp);

        // READ THE PING
        if (checkPacketLengthError(socket, dataInputStream) || checkPacketError(socket, dataInputStream, 0x01)) return;
        long v = dataInputStream.readLong();

        // SEND THE PONG
        ServerDataUtil.writeVarInt(dataOutputStream, String.valueOf(0x01).getBytes(StandardCharsets.UTF_8).length + String.valueOf(v)
                .getBytes(StandardCharsets.UTF_8).length);
        ServerDataUtil.writeVarLong(dataOutputStream, 0x01);
        dataOutputStream.writeLong(v);
        socket.close();
    }

    @SneakyThrows
    private boolean checkPacketLengthError(Socket socket, DataInputStream dataInputStream) {
        int packetLength = ServerDataUtil.readVarInt(dataInputStream);
        if (packetLength > 2097151) {
            socket.close();
            getLogger().info("Connection from " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + " closed: Too many bytes!");
            return true;
        }
        return false;
    }

    @SneakyThrows
    private boolean checkPacketError(Socket socket, DataInputStream dataInputStream, int id) {
        int packetID = ServerDataUtil.readVarInt(dataInputStream);
        if (packetID != id) {
            socket.close();
            getLogger().info("Connection from " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() +
                    " closed: Not allowed packet has been sent!");
            return true;
        }
        return false;
    }

    @SneakyThrows
    private void makePlayerJoin(Socket socket) {
        socket.close();
    }

    public Logger getLogger() {
        return this.logger;
    }
}
