package com.github.lory24.jubug.game_utils.world;

import com.github.lory24.jubug.game_utils.Location;
import lombok.Getter;

import java.util.HashMap;

public class WorldChunk {
    @Getter private final HashMap<Location, Block> blocks;
    @Getter private final ChunkArea chunkArea;

    public WorldChunk(int chunkIndex) {
        ChunkArea.Position2 chunkStartPos = new ChunkArea.Position2(chunkIndex * 16, chunkIndex * 16);
        chunkArea = new ChunkArea(chunkStartPos, new ChunkArea.Position2(chunkStartPos.getX() + 16,
                chunkStartPos.getZ() + 16));
        blocks = new HashMap<>();
    }
}
