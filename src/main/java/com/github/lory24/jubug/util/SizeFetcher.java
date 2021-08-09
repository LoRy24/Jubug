package com.github.lory24.jubug.util;

import java.lang.instrument.Instrumentation;

public class SizeFetcher {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation instr) {
        instrumentation = instr;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
}
