package action.command.html.project.anon;

import action.command.html.project.anon.cluster.Cluster;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import action.command.HTML;
import action.Service;

@Path("fitnesse")
public class Fitnesse extends HTML {

    private final Cluster handler = Cluster.Register.getInstance(Fitnesse.class);

    public Fitnesse() {
        super(Service.FITNESSE);
    }

    @Path("index.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String start() {
        handler.getFitnesse().start();
        return super.start();
    }

}
