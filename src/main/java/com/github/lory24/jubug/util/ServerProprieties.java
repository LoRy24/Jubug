package com.github.lory24.jubug.util;

import lombok.SneakyThrows;

import java.io.*;

public class ServerProprieties {
    private final int port;
    private final String motd;
    private final int maxPlayers;

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
    }

    public int getPort() {
        return port;
    }

    public String getMotd() {
        return motd;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}
