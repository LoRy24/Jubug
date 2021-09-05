package com.github.lory24.jubug.packets.play;

import com.github.lory24.jubug.game_utils.PlayerGameMode;
import lombok.Getter;

public class PacketPlayOutPlayerJoinGame extends PlayOutPacket {
    @Getter private final int entityId;
    @Getter private final boolean isHardcore;
    @Getter private final PlayerGameMode gamemode;
    @Getter private final PlayerGameMode previousGamemode; // can be -1
    @Getter private final int worldCount;
    @Getter private final String[] worldIdentifiers;
    // dimension codec
    // dimension
    @Getter private final String worldName;
    @Getter private final long hashedSeed;
    @Getter private final int maxPlayers;
    @Getter private final int viewDistance;
    @Getter private final boolean reducedDebugInfo;
    @Getter private final boolean enableRespawnScreen;
    @Getter private final boolean isDebug;
    @Getter private final boolean isFlat;
    

    public PacketPlayOutPlayerJoinGame(int entityId,
                                       boolean isHardcore,
                                       PlayerGameMode gamemode,
                                       PlayerGameMode previousGamemode,
                                       int worldCount,
                                       String[] worldIdentifiers,
                                       String worldName,
                                       long hashedSeed,
                                       int maxPlayers,
                                       int viewDistance,
                                       boolean reducedDebugInfo,
                                       boolean enableRespawnScreen,
                                       boolean isDebug,
                                       boolean isFlat) {
        super(0x26);
        this.entityId = entityId;
        this.isHardcore = isHardcore;
        this.gamemode = gamemode;
        this.previousGamemode = previousGamemode;
        this.worldCount = worldCount;
        this.worldIdentifiers = worldIdentifiers;
        this.worldName = worldName;
        this.hashedSeed = hashedSeed;
        this.maxPlayers = maxPlayers;
        this.viewDistance = viewDistance;
        this.reducedDebugInfo = reducedDebugInfo;
        this.enableRespawnScreen = enableRespawnScreen;
        this.isDebug = isDebug;
        this.isFlat = isFlat;
    }

    @Override
    public void setupData() {

    }
}
