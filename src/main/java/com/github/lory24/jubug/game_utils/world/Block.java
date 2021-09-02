package com.github.lory24.jubug.game_utils.world;

import com.github.lory24.jubug.game_utils.Location;
import com.github.lory24.jubug.game_utils.Material;
import lombok.Getter;

public class Block {
    @Getter private final Location location;
    @Getter private final Material type;

    public Block(Location location, Material type) {
        this.location = location;
        this.type = type;
    }
}
