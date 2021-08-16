package com.github.lory24.jubug;

import com.github.lory24.jubug.packets.ChatComponent;
import com.github.lory24.jubug.packets.play.PacketPlayOutPlayerDisconnect;
import com.github.lory24.jubug.util.player.PlayerConnection;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.UUID;

public class CraftPlayer {
    public final PlayerConnection playerConnection;
    @Getter private final String nickname;
    @Getter private final UUID uuid;

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
            if (!socket.isConnected()) {
                Jubug.getServer().removePlayer(this.nickname);
            }
        }
    }

    public void kickPlayer(String reason) {
        this.playerConnection.sendPacket(new PacketPlayOutPlayerDisconnect(new ChatComponent(reason)));
        Jubug.getServer().removePlayer(this.nickname);
        // TODO kick callback
    }
}
