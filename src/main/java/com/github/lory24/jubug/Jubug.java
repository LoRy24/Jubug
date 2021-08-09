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
        File[] defaultFiles = { new File("eula.txt"), new File("server-proprieties.proprieties") };
        for (File f: defaultFiles) {
            if (f.exists()) continue;
            f.createNewFile();
            InputStream inputStream = Jubug.class.getClassLoader().getResourceAsStream(f.getName());
            assert inputStream != null;
            BufferedReader defaultFileBR = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder fileContent = new StringBuilder();
            while (defaultFileBR.ready()) fileContent.append(defaultFileBR.readLine()).append("\n");
            FileWriter writer = new FileWriter(f);
            writer.write(fileContent.toString());
            writer.flush(); writer.close();
        }

        // Eula check
        InputStream eulaStream = new FileInputStream(defaultFiles[0]);
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
