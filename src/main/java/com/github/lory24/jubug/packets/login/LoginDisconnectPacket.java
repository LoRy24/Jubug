package com.github.lory24.jubug.packets.login;

import com.github.lory24.jubug.packets.ChatComponent;
import com.github.lory24.jubug.packets.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class LoginDisconnectPacket extends Packet<LoginDisconnectPacket> {
    private final ChatComponent reason = new ChatComponent("Your nickname contains more that 16 characters");

    public LoginDisconnectPacket() {
        super(0x00);
    }

    @Override
    public LoginDisconnectPacket readPacket(Socket socket, DataInputStream dataInputStream) {
        return null;
    }

    @Override
    public void sendPacket(Socket socket, DataOutputStream dataOutputStream) {

    }
}
