package com.github.lory24.jubug.packets.status;

import com.github.lory24.jubug.packets.Packet;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.net.Socket;

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
    public void prepareData() {
        writeID();
        dataOutputStream.writeLong(getPayload());
    }
}
