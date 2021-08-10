package com.github.lory24.jubug.packets.status;

import com.github.lory24.jubug.packets.Packet;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class StatusPingPacket extends Packet<StatusPingPacket> {
    @Getter private long payload;

    public StatusPingPacket() {
        super(0x01);
    }

    @SneakyThrows
    @Override
    public StatusPingPacket readPacket(Socket socket, DataInputStream dataInputStream) {
        this.payload = dataInputStream.readLong();
        return this;
    }

    @Override
    public void prepareData() { }
}
