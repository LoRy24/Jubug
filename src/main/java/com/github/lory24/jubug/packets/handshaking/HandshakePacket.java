package com.github.lory24.jubug.packets.handshaking;

import com.github.lory24.jubug.ServerDataUtil;
import com.github.lory24.jubug.packets.Packet;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.net.Socket;

public class HandshakePacket extends Packet<HandshakePacket> {
    @Getter private int protocolVersion;
    @Getter private String serverAddress;
    @Getter private int serverPort;
    @Getter private int nextState;

    public HandshakePacket() {
        super(0x00);
    }


    @SneakyThrows
    @Override
    public HandshakePacket readPacket(Socket socket, DataInputStream dataInputStream) {
        this.protocolVersion = ServerDataUtil.readVarInt(dataInputStream);
        this.serverAddress = ServerDataUtil.readString(dataInputStream);
        this.serverPort = dataInputStream.readUnsignedShort();
        this.nextState = ServerDataUtil.readVarInt(dataInputStream);
        return this;
    }

    @Override
    public void prepareData() { }
}
