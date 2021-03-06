package com.github.lory24.jubug.packets;

import com.github.lory24.jubug.ServerDataUtil;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public abstract class Packet<T extends Packet> {
    public final int packetID;
    protected final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    protected final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

    public Packet(int packetID) {
        this.packetID = packetID;
    }

    public void writeID() {
        ServerDataUtil.writeVarInt(dataOutputStream, packetID);
    }

    @SneakyThrows
    public void sendPacket(DataOutputStream dos) {
        prepareData();
        ServerDataUtil.writeVarInt(dos, byteArrayOutputStream.size());
        dos.write(byteArrayOutputStream.toByteArray());
    }

    public abstract T readPacket(Socket socket, DataInputStream dataInputStream);

    public abstract void prepareData();
}
