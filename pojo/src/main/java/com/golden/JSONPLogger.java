package com.golden;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Map;
import org.apache.log4j.Logger;

public enum JSONPLogger {

    APACHE("startup.bat", "C:/golden/bin/httpd/bin/", "apache-local-gold.log");

    static Logger logger = Logger.getLogger("com.golden.JSONPLogger");

    private final String script;
    private final String directory;
    private final String output;
    private Process record;

    JSONPLogger(String script, String directory, String output) {
        this.script = script;
        this.directory = directory;
        this.output = output;
        
    }

    public Process start(Map environment) {
        logger.debug("JSONPLogger.start ENUM");
        if (record == null) {
            this.record = JSONPLogger.start(this.directory, this.script, this.output, environment);
        }
        return record;
    }

    public Process restart(Map environment) {
        record = null;
        return this.start(environment);
    }

    public static Process start(String directory, String name, String output, Map env) {
        try {
            ProcessBuilder pb = new ProcessBuilder(directory + name);
            pb.environment().clear();
            if (pb.environment().isEmpty()) {
                System.out.println("Clean Env-Variables");
                pb.environment().putAll(env);
                pb.redirectErrorStream(true);
                pb.redirectOutput(Redirect.appendTo(new File(directory + output)));
                return pb.start();
            }

        } catch (IOException ex) {
            System.out.println("Startup Failed!");
        }
        return null;
    }
}
