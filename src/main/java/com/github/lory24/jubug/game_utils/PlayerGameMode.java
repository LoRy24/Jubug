package com.github.lory24.jubug.game_utils;

public enum PlayerGameMode {
    SURVIVAL((byte)  0),
    CREATIVE((byte)  1),
    ADVENTURE((byte) 2),
    SPECTATOR((byte) 3),
    ;

    private final byte id;

    PlayerGameMode(byte id) {
        this.id = id;
    }
}
