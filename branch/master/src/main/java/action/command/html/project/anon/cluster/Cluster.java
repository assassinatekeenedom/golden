package action.command.html.project.anon.cluster;

import action.Gateway.Branch;
import action.command.html.project.Project;
import action.command.html.project.anon.RESTApache;

public class Cluster extends Project {

    public static final Cluster Register = new Cluster();

    private Cluster() {
        super();
    }

    @Override
    public String start() {
        String message = super.start();
        Branch branch = this.getGateway().branch();
        branch.append(RESTApache.class);
//        branch.append(Log4j.class);
//        branch.append(Selenium.class);
//        branch.append(Fitnesse.class);
//        branch.append(Git.class);
//        branch.append(Maven.class);
        branch.start();
        this.setBranch(branch);
        return message;
    }

    public Cluster getInstance(Class handler) {
        this.getBranch().append(handler);
        return Register;
    }

    public static void main(String[] args) {
        Register.start();
    }
}
