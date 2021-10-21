package com.jordanschnur.deathmessagesplus.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Level;

import com.jordanschnur.deathmessagesplus.DeathMessagesPlusMain;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class DMPLogger {
    private DeathMessagesPlusMain plugin;
    private File dataFolder;
    private File logFile;
    private FileWriter fileWriter;
    private PrintWriter printWriter;
    private LoggingContext context;

    public DMPLogger(DeathMessagesPlusMain plugin) throws IOException {
        this.plugin = plugin;

        this.dataFolder = this.plugin.getDataFolder();
        if (!this.dataFolder.exists()) {
            this.dataFolder.mkdir();
        }

        this.logFile = new File(this.dataFolder, "log.txt");
        if (!this.logFile.exists()) {
            try {
                this.logFile.createNewFile();
            } catch (Throwable e) {
                plugin.getLogger().logp(
                        Level.SEVERE,
                        DMPLogger.class.toString(),
                        "DMPLogger",
                        "Failed to create log file.",
                        e
                );

                throw e;
            }
        }

        this.fileWriter = new FileWriter(this.logFile, true);
        this.printWriter = new PrintWriter(this.fileWriter);
    }

    public void logToFile(String message) {
        StringBuilder output = this.formatMessage(message);

        if (this.context != null) {
            output
                    .append('\n')
                    .append(this.context.formatContext());
        }

        if (plugin.isDebugMode()) {
            DeathMessagesPlusMain.getPluginLogger().info(output.toString());
        }

        this.printWriter.println(output.toString());
        this.printWriter.flush();
    }

    public void logToFile(String message, LoggingContext customContext) {
        StringBuilder output = this.formatMessage(message);
        output
                .append('\n')
                .append(customContext.formatContext());

        if (plugin.isDebugMode()) {
            DeathMessagesPlusMain.getPluginLogger().info(output.toString());
        }

        this.printWriter.println(output.toString());
        this.printWriter.flush();
    }

    private StringBuilder formatMessage(String message) {
        StringBuilder output = new StringBuilder();
        output
                .append("[")
                .append(new Timestamp(System.currentTimeMillis()))
                .append("] ")
                .append(message);

        return output;
    }

    /**
     * Must be called to close the FileWriter.
     */
    public void close() {
        printWriter.close();
    }

    public LoggingContext getContext() {
        return context;
    }

    public void setContext(LoggingContext context) {
        this.context = context;
    }
}
