package com.github.lory24.jubug.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public abstract class Packet<T extends Packet> {
    public final int packetID;

    public Packet(int packetID) {
        this.packetID = packetID;
    }

    public abstract T readPacket(Socket socket, DataInputStream dataInputStream);

    public abstract void sendPacket(Socket socket, DataOutputStream dataOutputStream);
}
