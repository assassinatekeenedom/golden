package action.command.html.project.anon;

import action.command.html.project.anon.cluster.Cluster;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import action.Service;
import action.command.HTML;

@Path("git")
public class Git extends HTML {

    private final Cluster handler = Cluster.Register.getInstance(Git.class);

    public Git() {
        super(Service.GIT);
    }

    @Path("index.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String start() {
        return handler.getGit().start();
    }
}
