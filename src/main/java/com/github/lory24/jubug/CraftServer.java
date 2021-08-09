package com.github.lory24.jubug;

public abstract class CraftServer {
    private final Logger logger;

    public CraftServer() {
        this.logger = new Logger(null, System.out);
    }

    protected void runServer() {
        getLogger().info("Starting NET server...");
    }

    public Logger getLogger() {
        return this.logger;
    }
}
