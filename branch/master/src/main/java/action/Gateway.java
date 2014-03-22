package action;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Application;

public class Gateway implements Command {

    public enum Status {

        START("start"),
        ERROR("error");
        private final String message;

        Status(String name) {
            this.message = name;
        }

        public String getMessage() {
            return message;
        }
    }

    public class Branch extends Application implements Command {

        private final Set<Class<?>> website = new HashSet<>();
        private final Gateway gateway;

        public Branch(Gateway gateway) {
            this.gateway = gateway;
        }

        public void append(Class<?> RESTful) {
            website.add(RESTful);
        }

        @Override
        public Set<Class<?>> getClasses() {
            return website;
        }

        @Override
        public String start() {
            Leaf leaf = gateway.merge(this);
            leaf.RESTful();
            return "RESTful";
        }

    }

    private int port = 8080;
    private String domain = "http://localhost/";
    private String windows = "c:";
    private String linux = "/";
    private String context = "golden/service/";
    private String startup = "/startup.bat";
    private String kill = "/kill.bat";
    private String repository = "https://rnkeene@bitbucket.org/rnkeene/golden.git";
    private Status status = Status.START;

    public Gateway() {
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPort() {
        return port;
    }

    public String getDomain() {
        return domain;
    }

    public String getWindows() {
        return windows;
    }

    public String getLinux() {
        return linux;
    }

    public String getContext() {
        return context;
    }

    public String getStartup() {
        return startup;
    }

    public String getKill() {
        return kill;
    }

    public String getRepository() {
        return repository;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }

    public void setLinux(String linux) {
        this.linux = linux;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setStartup(String startup) {
        this.startup = startup;
    }

    public void setKill(String kill) {
        this.kill = kill;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public Status getStatus() {
        return this.status;
    }

    @Override
    public String start() {
        return this.status.getMessage();
    }

    public Branch branch() {
        return new Branch(this);
    }

    public Leaf merge(Branch branch) {
        return new Leaf(branch, this.domain, this.port);
    }

    public Status startup(Service action) {
        try {
            action.start(this.windows + this.linux, this.context, this.startup);
            return Status.START;
        } catch (IOException ex) {
            Logger.getLogger(Gateway.class.getName()).log(Level.SEVERE, null, ex);
            return Status.ERROR;
        }
    }
}
