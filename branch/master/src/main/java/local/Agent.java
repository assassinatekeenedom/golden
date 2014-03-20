package local;

public class Agent {

    private static final Master project = new Master();

    public Agent() {
    }

    public Leaf trial(Branch branch) {
        return new Leaf(branch, project.getDomain(), project.getPort());
    }

}
