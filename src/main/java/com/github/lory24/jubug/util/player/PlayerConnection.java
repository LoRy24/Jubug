package com.github.lory24.jubug.util.player;

import com.github.lory24.jubug.packets.play.PlayOutPacket;

import java.io.IOException;
import java.net.Socket;

public class PlayerConnection {
    private final Socket socket;

    public PlayerConnection(Socket socket) {
        this.socket = socket;
    }

    public void sendPacket(final PlayOutPacket packet) {
        packet.send(socket);
    }

    public void disconnect() {
        try {
            this.socket.close();
        } catch (IOException ignored) { }
    }
}
