package local.instance.real.composite;

import local.Agent;
import local.Branch;
import local.Leaf;
import local.instance.real.Apache;
import local.instance.real.Fitnesse;
import local.instance.real.Git;
import local.instance.real.Log4j;
import local.instance.real.Selenium;

public class Project {

    public static void main(String[] args) {
        Branch branch = new Branch();
        {
            branch.append(Apache.class);
            branch.append(Log4j.class);
            branch.append(Selenium.class);
            branch.append(Fitnesse.class);
            branch.append(Git.class);
        }
        Agent agent = new Agent();
        Leaf leaf = agent.trial(branch);
        leaf.RESTful();
    }
}
