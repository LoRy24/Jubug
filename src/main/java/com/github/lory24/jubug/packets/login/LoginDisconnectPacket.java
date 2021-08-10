package com.github.lory24.jubug.packets.login;

import com.github.lory24.jubug.ServerDataUtil;
import com.github.lory24.jubug.packets.ChatComponent;
import com.github.lory24.jubug.packets.Packet;
import lombok.Getter;

import java.io.DataInputStream;
import java.net.Socket;

public class LoginDisconnectPacket extends Packet<LoginDisconnectPacket> {
    @Getter private final ChatComponent reason = new ChatComponent("Your nickname contains more that §c16 §rcharacters. §c§lYou sus!!!");

    public LoginDisconnectPacket() {
        super(0x00);
    }

    @Override
    public LoginDisconnectPacket readPacket(Socket socket, DataInputStream dataInputStream) {
        return null;
    }

    @Override
    public void prepareData() {
        writeID();
        ServerDataUtil.writeString(dataOutputStream, reason.toString());
    }
}
