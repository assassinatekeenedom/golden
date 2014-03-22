package action.command.html.project.anon;

import action.command.html.project.anon.cluster.Cluster;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import action.command.HTML;
import action.Service;

@Path("log4j")
public class Log4j extends HTML {

    private final Cluster handler = Cluster.Register.getInstance(Log4j.class);

    public Log4j() {
        super(Service.LOG4J);
    }

    @Path("index.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String start() {
        return handler.getLog4j().start();
    }

}
