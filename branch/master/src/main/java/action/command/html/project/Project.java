package action.command.html.project;

import action.Gateway;
import action.Gateway.Branch;
import action.Service;
import action.command.HTML;
import action.command.html.Apache;
import action.command.html.Fitnesse;
import action.command.html.Git;
import action.command.html.Log4j;
import action.command.html.Maven;
import action.command.html.Selenium;

public class Project extends HTML {

    private Apache apache;
    private Fitnesse fitnesse;
    private Git git;
    private Log4j log4j;
    private Maven maven;
    private Selenium selenium;
    private Branch branch;

    public Project() {
        super(Service.ALL);
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public void apply(HTML service) {
        service.setGateway(this.getGateway());
    }

    public void setApache(Apache apache) {
        this.apache = apache;
        apply(this.apache);
    }

    public void setFitnesse(Fitnesse fitnesse) {
        this.fitnesse = fitnesse;
        apply(this.fitnesse);
    }

    public void setGit(Git git) {
        this.git = git;
        apply(this.git);
    }

    public void setLog4j(Log4j log4j) {
        this.log4j = log4j;
        apply(this.log4j);
    }

    public void setMaven(Maven maven) {
        this.maven = maven;
        apply(this.maven);
    }

    public void setSelenium(Selenium selenium) {
        this.selenium = selenium;
        apply(this.selenium);
    }

    @Override
    public String start() {
        Gateway gateway = new Gateway();
        this.setApache(new Apache());
        this.setFitnesse(new Fitnesse());
        this.setGit(new Git());
        this.setLog4j(new Log4j());
        this.setMaven(new Maven());
        this.setSelenium(new Selenium());
        this.setGateway(gateway);
        return "success";
    }

    public Apache getApache() {
        return apache;
    }

    public Fitnesse getFitnesse() {
        return fitnesse;
    }

    public Git getGit() {
        return git;
    }

    public Log4j getLog4j() {
        return log4j;
    }

    public Maven getMaven() {
        return maven;
    }

    public Selenium getSelenium() {
        return selenium;
    }

    public Branch getBranch() {
        return branch;
    }

}
