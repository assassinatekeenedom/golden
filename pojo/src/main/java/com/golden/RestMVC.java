package com.golden;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("apache")
public class RestMVC {

    public static Map session = new HashMap();

    @Path("start")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String startApache() throws IOException {
        JSONPLogger.APACHE.start(session);
        return "<html><head><title>..//add dynamic code here</title></head><body style='background-color:white;'></body></html>";
    }

    @Path("startr")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String restartApache() throws IOException {
        JSONPLogger.APACHE.restart(session);
        return "<html><head><title>RESTART..//add dynamic code here</title></head><body style='background-color:black;'></body></html>";
    }

    public static void main(String[] args) throws Exception {
        Register.HTTP.host();
        FileAppender.APACHE.append();
    }
}
