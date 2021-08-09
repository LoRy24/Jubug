package com.github.lory24.jubug.util;

import java.util.HashMap;

public class ProprietiesReader {
    public static final char COMMENT_CHAR = '#';

    public static ProprietiesSheet parseProprietiesFromString(String s) {
        final HashMap<String, String> values = new HashMap<>();

        for (String line: s.split("\n")) {
            if (line == null || line.isEmpty()) continue;
            if (line.toCharArray()[0] == COMMENT_CHAR || line.trim().split("=").length < 2) continue;
            String key = line.trim().split("=")[0];
            StringBuilder value = new StringBuilder();
            for (int i = 0; i < line.length(); i++) {
                if (i < (key + "=").length()) continue;
                value.append(line.trim().toCharArray()[i]);
            }
            values.put(key, value.toString());
        }

        return new ProprietiesSheet().loadValues(values);
    }
}
