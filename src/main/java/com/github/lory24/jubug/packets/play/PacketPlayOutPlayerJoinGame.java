package com.github.lory24.jubug.packets.play;

import com.github.lory24.jubug.game_utils.PlayerGameMode;

public class PacketPlayOutPlayerJoinGame extends PlayOutPacket {
    private final int entityId;
    private final boolean isHardcore;
    private final PlayerGameMode gamemode; // can be -1
    private final PlayerGameMode previousGamemode;
    private final int worldCount;


    public PacketPlayOutPlayerJoinGame(int entityId, boolean isHardcore, PlayerGameMode gamemode, PlayerGameMode previousGamemode, int worldCount) {
        super(0x26);
        this.entityId = entityId;
        this.isHardcore = isHardcore;
        this.gamemode = gamemode;
        this.previousGamemode = previousGamemode;
        this.worldCount = worldCount;
    }

    @Override
    public void setupData() {

    }
}
