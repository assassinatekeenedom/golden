package local.instance.real;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import local.instance.Local;
import local.Service;

@Path("log4j")
public class Log4j extends Local {

    public Log4j() {
        super(Service.LOG4J);
    }

    @Path("index.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String start() {
        return super.start();
    }

    @Path("js/log4j.js")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String success() {
        return super.success();
    }

    @Path("js/no-log4j.js")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String failure() {
        return super.failure();
    }
}
