package action.command.html.project.anon;

import action.command.html.project.anon.cluster.Cluster;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import action.command.HTML;
import action.Service;
import action.command.html.Apache;
import structure.Callback;
import structure.command.user.story.Story;
import structure.command.user.story.load.LoadBlack;

@Path("apache")
public class RESTApache extends HTML implements Callback {

    private final Cluster handler = Cluster.Register.getInstance(RESTApache.class);
    private final Apache apache = new Apache();
    private final LoadBlack index = new LoadBlack(Service.APACHE.getService());
    private final Story story;

    public RESTApache() {
        super(Service.APACHE);
        apache.setGateway(handler.getGateway());
        apache.setService(Service.APACHE);
        story = new Story(index.getSession());
    }

    @Path("index.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String start() {
        apache.start();
        return story.start();
    }

    @Path("index.js")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String callback() {
        return story.callback();
    }

    @Path("jsonp.js")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String action() {
        return story.action();
    }
}
