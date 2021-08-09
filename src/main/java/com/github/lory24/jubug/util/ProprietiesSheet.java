package com.github.lory24.jubug.util;

import java.util.HashMap;

public class ProprietiesSheet {
    private HashMap<String, String> values;

    public ProprietiesSheet loadValues(HashMap<String, String> hm) {
        this.values = hm;
        return this;
    }

    public HashMap<String, String> getValues() {
        return values;
    }
}
