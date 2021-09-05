package com.github.lory24.jubug;

import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("unused")
public class ServerDataUtil {

    @SneakyThrows
    public static int readVarInt(DataInputStream dataInputStream) {
        int value = 0;
        int bitOffset = 0;
        byte currentByte;
        do {
            if (bitOffset == 35) throw new RuntimeException("VarInt is too big");

            currentByte = dataInputStream.readByte();
            value |= (currentByte & 0b01111111) << bitOffset;

            bitOffset += 7;
        } while ((currentByte & 0b10000000) != 0);

        return value;
    }

    @SneakyThrows
    public static long readVarLong(DataInputStream dataInputStream) {
        long value = 0;
        int bitOffset = 0;
        byte currentByte;
        do {
            if (bitOffset == 70) throw new RuntimeException("VarLong is too big");

            currentByte = dataInputStream.readByte();
            value |= (long) (currentByte & 0b01111111) << bitOffset;

            bitOffset += 7;
        } while ((currentByte & 0b10000000) != 0);

        return value;
    }

    @SneakyThrows
    public static void writeVarInt(DataOutputStream dataOutputStream, int value) {
        do {
            byte currentByte = (byte) (value & 0b01111111);

            value >>>= 7;
            if (value != 0) currentByte |= 0b10000000;

            dataOutputStream.writeByte(currentByte);
        } while (value != 0);
    }

    @SneakyThrows
    public static void writeVarLong(DataOutputStream dataOutputStream, long value) {
        do {
            byte currentByte = (byte) (value & 0b01111111);

            value >>>= 7;
            if (value != 0) currentByte |= 0b10000000;

            dataOutputStream.writeByte(currentByte);
        } while (value != 0);
    }

    @SneakyThrows
    public static void writeString(final DataOutputStream dataOutputStream, final String s) {
        final byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        if (bytes.length> 32767) System.out.println("The string is too long!");
        writeVarInt(dataOutputStream, bytes.length);
        dataOutputStream.write(bytes, 0, bytes.length);
    }

    @SneakyThrows
    public static String readString(final DataInputStream dataInputStream) {
        final int length = readVarInt(dataInputStream);
        final byte[] bytes = dataInputStream.readNBytes(length);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static String read16string(final DataInputStream dataInputStream) {
        final int length = readVarInt(dataInputStream);
        final byte[] bytes = dataInputStream.readNBytes(length);
        String value = new String(bytes, StandardCharsets.UTF_8);
        return value.toCharArray().length <= 16 ? value : null;
    }
}
