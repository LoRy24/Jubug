package com.github.lory24.jubug;

import com.github.lory24.jubug.util.player.PlayerConnection;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.UUID;

public class CraftPlayer {
    public final PlayerConnection playerConnection;
    private final String nickname;
    private final UUID uuid;

    public CraftPlayer(PlayerConnection playerConnection, String nickname, UUID uuid) {
        this.playerConnection = playerConnection;
        this.nickname = nickname;
        this.uuid = uuid;
    }

    @SneakyThrows
    protected void startPlayerConnectionState(Socket socket) {
        // MAKE THE PLAYER SPAWN
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream =
                new DataOutputStream(socket.getOutputStream());

        while (true) {

        }
    }
}
