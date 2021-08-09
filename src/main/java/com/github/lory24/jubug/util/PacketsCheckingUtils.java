package com.github.lory24.jubug.util;

import com.github.lory24.jubug.Jubug;
import com.github.lory24.jubug.ServerDataUtil;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.net.Socket;

public class PacketsCheckingUtils {

    @SneakyThrows
    public static boolean checkPacketLengthError(Socket socket, DataInputStream dataInputStream) {
        int packetLength = ServerDataUtil.readVarInt(dataInputStream);
        if (packetLength > 2097151) {
            socket.close();
            Jubug.getServer().getLogger().info("Connection from " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + " closed: Too many bytes!");
            return true;
        }
        return false;
    }

    @SneakyThrows
    public static boolean checkPacketError(Socket socket, DataInputStream dataInputStream, int id) {
        int packetID = ServerDataUtil.readVarInt(dataInputStream);
        if (packetID != id) {
            socket.close();
            Jubug.getServer().getLogger().info("Connection from " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() +
                    " closed: Not allowed packet has been sent!");
            return true;
        }
        return false;
    }
}
