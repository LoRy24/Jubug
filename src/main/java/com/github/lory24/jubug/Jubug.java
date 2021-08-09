package com.github.lory24.jubug;

import com.github.lory24.jubug.util.ProprietiesReader;
import com.github.lory24.jubug.util.ProprietiesSheet;
import lombok.SneakyThrows;

import java.io.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class Jubug extends CraftServer {

    protected static CraftServer SERVER;

    @SneakyThrows
    protected static void loadServer() {
        SERVER = new Jubug();
        System.out.println("Starting Jubug Server for Minecraft 1.8...");

        // Files generation ----
        // Eula file
        File eulaFile = new File("eula.txt");
        if (!eulaFile.exists()) {
            eulaFile.createNewFile();
            InputStream defaultEulaStream = Jubug.class.getClassLoader().getResourceAsStream("eula.txt");
            assert defaultEulaStream != null;
            BufferedReader eulaDefaultBF = new BufferedReader(new InputStreamReader(defaultEulaStream));
            StringBuilder defaultEulaText = new StringBuilder();
            while (eulaDefaultBF.ready()) defaultEulaText.append(eulaDefaultBF.readLine()).append("\n");
            FileWriter writer = new FileWriter(eulaFile);
            writer.write(defaultEulaText.toString());
            writer.flush(); writer.close();
        }


        // Eula check
        InputStream eulaStream = new FileInputStream(eulaFile);
        BufferedReader bf = new BufferedReader(new InputStreamReader(eulaStream));
        StringBuilder eulaText = new StringBuilder();
        while (bf.ready()) eulaText.append(bf.readLine()).append("\n");
        ProprietiesSheet ps = ProprietiesReader.parseProprietiesFromString(eulaText.toString());

        if (!Boolean.parseBoolean(ps.getValues().get("eula"))) {
            System.out.println("You need to agree to the EULA in order to run the server. Read `eula.txt` for more info.");
            System.out.println("Stopping the server...");
            return;
        }

        getServer().runServer();
    }

    public static CraftServer getServer() {
        return SERVER;
    }
}
