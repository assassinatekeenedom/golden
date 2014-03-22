package action.command.html.project.anon;

import action.command.html.project.anon.cluster.Cluster;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import action.command.HTML;
import action.Service;

@Path("selenium")
public class Selenium extends HTML {

    private final Cluster handler = Cluster.Register.getInstance(Selenium.class);

    public Selenium() {
        super(Service.SELENIUM);
    }

    @Path("index.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String start() {
        return handler.getSelenium().start();
    }

}
