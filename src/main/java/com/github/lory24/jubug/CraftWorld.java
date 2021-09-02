package com.github.lory24.jubug;

import com.github.lory24.jubug.game_utils.world.WorldChunk;
import lombok.Getter;

import java.util.HashMap;

public class CraftWorld {
    @Getter private final String name;
    @Getter private final HashMap<Integer, WorldChunk> chunks;

    public CraftWorld(String name, HashMap<Integer, WorldChunk> chunks) {
        this.name = name;
        this.chunks = chunks;
    }

    public void saveWorld() {

    }
}
