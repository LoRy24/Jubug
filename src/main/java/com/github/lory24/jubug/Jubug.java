package com.github.lory24.jubug;

import com.github.lory24.jubug.util.ProprietiesReader;
import com.github.lory24.jubug.util.ProprietiesSheet;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Jubug extends CraftServer {

    protected static CraftServer SERVER;

    @SneakyThrows
    protected static void loadServer() {
        SERVER = new Jubug();
        System.out.println("Starting Jubug Server for Minecraft 1.8...");
        InputStream eulaStream = Jubug.class.getClassLoader().getResourceAsStream("eula.txt");
        assert eulaStream != null;
        BufferedReader bf = new BufferedReader(new InputStreamReader(eulaStream));
        StringBuilder eulaText = new StringBuilder();
        while (bf.ready()) eulaText.append(bf.readLine());
        ProprietiesSheet ps = ProprietiesReader.parseProprietiesFromString(eulaText.toString());
        if (Boolean.parseBoolean(ps.getValues().get("name"))) getServer().runServer();
        System.out.println("You have to accept the eula!");
    }

    public static CraftServer getServer() {
        return SERVER;
    }
}
