package com.github.lory24.jubug.packets.status;

import com.github.lory24.jubug.ServerDataUtil;
import com.github.lory24.jubug.packets.Packet;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class StatusPongPacket extends Packet<StatusPongPacket> {
    @Getter private final long payload;

    public StatusPongPacket(long payload) {
        super(0x01);
        this.payload = payload;
    }

    @Override
    public StatusPongPacket readPacket(Socket socket, DataInputStream dataInputStream) { return this; }

    @SneakyThrows
    @Override
    public void sendPacket(Socket socket, DataOutputStream dataOutputStream) {
        ServerDataUtil.writeVarInt(dataOutputStream, 1 + String.valueOf(getPayload()).getBytes(StandardCharsets.UTF_8).length);
        ServerDataUtil.writeVarLong(dataOutputStream, 0x01);
        dataOutputStream.writeLong(getPayload());
    }
}
