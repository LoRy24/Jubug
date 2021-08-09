package com.github.lory24.jubug;

import com.github.lory24.jubug.util.LogLevel;

import java.io.PrintStream;
import java.util.Date;

public class Logger {
    private final String loggerPrefix;
    private final PrintStream outStream;

    public Logger(String loggerPrefix, PrintStream outStream) {
        this.loggerPrefix = loggerPrefix;
        this.outStream = outStream;
    }

    // TODO add doc here
    private String getOutPrintPrefix(LogLevel level) {
        Date date = new Date();
        return String.format("%02d:%02d:%02d | %s", date.getHours(), date.getMinutes(), date.getSeconds(), level.getS());
    }

    // TODO add doc here
    public void log(Object o) {
        this.outStream.println(String.format("[%s]: ", getOutPrintPrefix(LogLevel.LOG)) + (loggerPrefix == null ? "" : loggerPrefix) + o.toString());
    }

    // TODO add doc here
    public void warn(Object o) {
        this.outStream.println(String.format("[%s]: ", getOutPrintPrefix(LogLevel.WARN)) +
                (loggerPrefix == null ? "" : loggerPrefix) + o.toString());
    }

    // TODO add doc here
    public void info(Object o) {
        this.outStream.println(String.format("[%s]: ", getOutPrintPrefix(LogLevel.INFO)) + (loggerPrefix == null ? ""
                : loggerPrefix) + o.toString());
    }

    // TODO add doc here
    public void err(Object o) {
        this.outStream.println(String.format("[%s]: ", getOutPrintPrefix(LogLevel.ERR)) + (loggerPrefix == null ? "" : loggerPrefix)
                + o.toString());
    }
}
