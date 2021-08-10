package com.github.lory24.jubug.packets.login;

import com.github.lory24.jubug.ServerDataUtil;
import com.github.lory24.jubug.packets.Packet;
import lombok.Getter;

import java.io.DataInputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class LoginSuccessPacket extends Packet<LoginSuccessPacket> {
    @Getter private final String nickname;
    @Getter private final UUID uuid;

    public LoginSuccessPacket(String nickname) {
        super(0x02);

        this.nickname = nickname;
        this.uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + nickname).getBytes(StandardCharsets.UTF_8)); // v3 uuid
    }

    @Override
    public LoginSuccessPacket readPacket(Socket socket, DataInputStream dataInputStream) { return this; }

    @Override
    public void prepareData() {
        writeID();
        ServerDataUtil.writeString(dataOutputStream, uuid.toString());
        ServerDataUtil.writeString(dataOutputStream, nickname);
    }
}
