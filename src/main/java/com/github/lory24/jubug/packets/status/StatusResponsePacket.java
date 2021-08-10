package com.github.lory24.jubug.packets.status;

import com.github.lory24.jubug.ServerDataUtil;
import com.github.lory24.jubug.packets.Packet;
import lombok.Getter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

@SuppressWarnings("SpellCheckingInspection")
public class StatusResponsePacket extends Packet<StatusResponsePacket> {
    // protocol, max, online, motd
    @Getter private String jsonResponse = "{\"version\":{\"name\":\"Jubug 1.8\",\"protocol\":%d},\"players\":{\"max\":%d,\"online\":%d},\"description\":{\"text\":\"%s\"}}";
    private final int PROTOCOL;
    private final int maxPlayers;
    private final int onlinePlayers;
    private final String motd;

    public StatusResponsePacket(int PROTOCOL, int protocolVersion, int maxPlayers, int onlinePlayers, String motd) {
        super(0x00);
        jsonResponse = protocolVersion == PROTOCOL ? String.format(jsonResponse, PROTOCOL, maxPlayers, onlinePlayers, motd) : String.format(jsonResponse, String.format(jsonResponse,
                PROTOCOL, maxPlayers, onlinePlayers, "\u00a7cBRO CHANGE VERSION NOW!"));
        this.PROTOCOL = PROTOCOL;
        this.maxPlayers = maxPlayers;
        this.onlinePlayers = onlinePlayers;
        this.motd = motd;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public StatusResponsePacket readPacket(Socket socket, DataInputStream dataInputStream) { return this; }

    @Override
    public void sendPacket(Socket socket, DataOutputStream dataOutputStream) {
        ServerDataUtil.writeVarInt(dataOutputStream, 3 + jsonResponse.getBytes().length);
        ServerDataUtil.writeVarInt(dataOutputStream, 0x00);
        ServerDataUtil.writeString(dataOutputStream, String.format(jsonResponse, PROTOCOL, maxPlayers, onlinePlayers, motd));
    }
}
