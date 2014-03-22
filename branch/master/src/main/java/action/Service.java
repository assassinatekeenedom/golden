package action;

import java.io.IOException;

public enum Service {

    APACHE("apache"),
    SELENIUM("selenium"),
    LOG4J("log4j"),
    GIT("git"),
    FITNESSE("fitnesse"),
    MAVEN("mvn"),
    ALL("all");

    private final String service;

    Service(String project) {
        this.service = project;
    }

    public String getService() {
        return service;
    }

    public Process start(String drive, String folder, String startup) throws IOException {
        return new ProcessBuilder(drive + folder + this.service + startup).start();
    }

}
