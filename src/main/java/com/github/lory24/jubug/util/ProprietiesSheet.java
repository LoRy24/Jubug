package com.github.lory24.jubug.util;

import lombok.Getter;

import java.util.HashMap;

public class ProprietiesSheet {
    @Getter private HashMap<String, String> values;

    public ProprietiesSheet loadValues(HashMap<String, String> hm) {
        this.values = hm;
        return this;
    }
}
