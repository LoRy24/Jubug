package com.github.lory24.jubug.packets.play;

public class PacketPlayOutPlayerJoinGame extends PlayOutPacket {

    public PacketPlayOutPlayerJoinGame() {
        super(0x26);
    }

    @Override
    public void setupData() {

    }
}
