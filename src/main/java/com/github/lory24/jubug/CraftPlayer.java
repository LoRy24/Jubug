package com.github.lory24.jubug;

import com.github.lory24.jubug.packets.ChatComponent;
import com.github.lory24.jubug.packets.play.PacketPlayOutPlayerDisconnect;
import com.github.lory24.jubug.util.player.PlayerConnection;
import lombok.Getter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

public class CraftPlayer {
    public final PlayerConnection playerConnection;
    @Getter private final String nickname;
    @Getter private final UUID uuid;
    @Getter private int eid;

    public CraftPlayer(PlayerConnection playerConnection, String nickname, UUID uuid) {
        this.playerConnection = playerConnection;
        this.nickname = nickname;
        this.uuid = uuid;
        for (int i = 0; i <= Jubug.getServer().registeredEID.size(); i++) {
            if (Jubug.getServer().registeredEID.contains(i)) continue;
            this.eid = i;
            Jubug.getServer().registeredEID.add(i);
            break;
        }
    }

    protected void startPlayerConnectionState(Socket socket) {
        // MAKE THE PLAYER SPAWN
        try {
            DataInputStream dataInputStream =
                    new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            Thread checkConnectionThread = new Thread(() -> {
                while (true) {
                    try {
                        if (socket.getInputStream().read() == -1) {
                            Jubug.getServer().getLogger().info("Player " + this.nickname + " disconnected!");
                            Jubug.getServer().removePlayer(this.nickname);
                            this.playerConnection.disconnect();
                            break;
                        }
                    } catch (IOException e) { e.printStackTrace(); }
                }
            });
            checkConnectionThread.start();

            while (Jubug.getServer().getPlayers().containsKey(nickname));
            if (!socket.isClosed()) socket.close();

        } catch (IOException ignored) { }
    }

    public void kickPlayer(String reason) {
        this.playerConnection.sendPacket(new PacketPlayOutPlayerDisconnect(new ChatComponent(reason)));
        this.playerConnection.disconnect();
        Jubug.getServer().removePlayer(this.nickname);
        // TODO kick callback
    }
}
