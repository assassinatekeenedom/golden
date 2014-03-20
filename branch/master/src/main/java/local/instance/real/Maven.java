package local.instance.real;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import local.instance.Local;
import local.Service;

@Path("maven")
public class Maven extends Local {

    public Maven() {
        super(Service.MAVEN);
    }

    @Path("index.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String start() {
        return super.start();
    }

    @Path("js/apache.js")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String success() {
        return super.success();
    }

    @Path("js/no-apache.js")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String failure() {
        return super.failure();
    }
}
