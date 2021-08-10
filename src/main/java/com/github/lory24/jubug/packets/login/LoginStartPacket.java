package com.github.lory24.jubug.packets.login;

import com.github.lory24.jubug.ServerDataUtil;
import com.github.lory24.jubug.packets.Packet;
import lombok.Getter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class LoginStartPacket extends Packet<LoginStartPacket> {
    @Getter private String name;

    public LoginStartPacket() {
        super(0x00);
    }

    @Override
    public LoginStartPacket readPacket(Socket socket, DataInputStream dataInputStream) {
        this.name = ServerDataUtil.read16string(dataInputStream);
        return this;
    }

    @Override
    public void sendPacket(Socket socket, DataOutputStream dataOutputStream) { }
}
