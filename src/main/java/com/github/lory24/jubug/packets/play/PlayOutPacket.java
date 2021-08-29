package com.github.lory24.jubug.packets.play;

import com.github.lory24.jubug.ServerDataUtil;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public abstract class PlayOutPacket {
    private final int packetID;
    protected final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    protected final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

    protected PlayOutPacket(int packetID) {
        this.packetID = packetID;
    }

    public void writeID() {
        ServerDataUtil.writeVarInt(dataOutputStream, packetID);
    }

    @SneakyThrows
    public void send(Socket socket) {
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        setupData();
        ServerDataUtil.writeVarInt(dos, byteArrayOutputStream.size() * 2);
        dos.write(byteArrayOutputStream.toByteArray());
    }

    public abstract void setupData();
}
