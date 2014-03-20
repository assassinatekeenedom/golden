package local.instance.real;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import local.instance.Local;
import local.Service;

@Path("selenium")
public class Selenium extends Local {

    public Selenium() {
        super(Service.SELENIUM);
    }

    @Path("index.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String start() {
        return super.start();
    }

    @Path("js/selenium.js")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String success() {
        return super.success();
    }

    @Path("js/no-selenium.js")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String failure() {
        return super.failure();
    }
}
