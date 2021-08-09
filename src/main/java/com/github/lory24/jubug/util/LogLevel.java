package com.github.lory24.jubug.util;

public enum LogLevel {
    LOG(" LOG"),
    WARN("WARN"),
    INFO("INFO"),
    ERR(" ERR"),
    ;

    private final String s;

    LogLevel(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
