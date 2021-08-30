package com.github.lory24.jubug.util;

import com.github.lory24.jubug.game_utils.PlayerGameMode;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.*;

public class ServerProprieties {
    @Getter private final int port;
    @Getter private final String motd;
    @Getter private final int maxPlayers;
    @Getter private final PlayerGameMode defaultGamemode;

    @SneakyThrows
    public ServerProprieties() {
        // File loading things
        InputStream serverProprietiesFileInputStream = new FileInputStream("server-proprieties.proprieties");
        BufferedReader bf = new BufferedReader(new InputStreamReader(serverProprietiesFileInputStream));
        StringBuilder fileContent = new StringBuilder();
        while (bf.ready()) fileContent.append(bf.readLine()).append("\n");
        ProprietiesSheet proprietiesSheet = ProprietiesReader.parseProprietiesFromString(fileContent.toString());

        // Values initialization
        this.port = Integer.parseInt(proprietiesSheet.getValues().get("port"));
        this.motd = proprietiesSheet.getValues().get("motd");
        this.maxPlayers = Integer.parseInt(proprietiesSheet.getValues().get("max-players"));
        this.defaultGamemode = PlayerGameMode.valueOf(proprietiesSheet.getValues().get("default-gamemode"));
    }
}
