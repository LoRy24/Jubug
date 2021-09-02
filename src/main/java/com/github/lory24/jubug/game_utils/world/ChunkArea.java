package com.github.lory24.jubug.game_utils.world;

import com.github.lory24.jubug.game_utils.Location;
import lombok.Getter;

public class ChunkArea {
    @Getter private final Position2 from;
    @Getter private final Position2 to;

    public ChunkArea(Position2 from, Position2 to) {
        this.from = new Position2(Math.min(from.getX(), to.getX()),
                Math.min(from.getZ(), to.getZ()));
        this.to = new Position2(Math.max(from.getX(), to.getX()), Math.max(from.getZ(), to.getZ()));
    }

    public boolean isInside(Location loc) {
        if (loc.getX() < from.getX() || loc.getZ() < from.getZ() || loc.getX() > to.getX()
                || loc.getZ() > to.getZ())
            return false;
        return true;
    }

    public static class Position2 {
        @Getter private final int x, z;

        public Position2(int x, int z) {
            this.x = x;
            this.z = z;
        }
    }
}
