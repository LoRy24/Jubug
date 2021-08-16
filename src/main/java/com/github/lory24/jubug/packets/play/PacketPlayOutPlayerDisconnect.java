package com.github.lory24.jubug.packets.play;

import com.github.lory24.jubug.ServerDataUtil;
import com.github.lory24.jubug.packets.ChatComponent;

public class PacketPlayOutPlayerDisconnect extends PlayOutPacket {
    private final ChatComponent reason;

    public PacketPlayOutPlayerDisconnect(ChatComponent reason) {
        super(0x1A);
        this.reason = reason;
    }

    @Override
    public void setupData() {
        writeID();
        ServerDataUtil.writeString(dataOutputStream, reason.getText());
    }
}
