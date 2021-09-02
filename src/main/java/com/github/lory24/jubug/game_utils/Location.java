package com.github.lory24.jubug.game_utils;

import com.github.lory24.jubug.CraftWorld;
import lombok.Getter;

public class Location {
    @Getter private final CraftWorld world;
    @Getter private final double x, y, z;
    @Getter private float yaw, pitch;

    public Location(CraftWorld world, double x, double y, double z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location(CraftWorld world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
