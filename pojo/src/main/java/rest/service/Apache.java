package rest.service;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import rest.Service;

@Path("apache")
public class Apache {

    private final ProcessBuilder apache = new ProcessBuilder("c:/golden/conf/apache/startup.bat");
    private Process running;

    @Path("start")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String html() throws IOException {
        if (running == null) {
            running = apache.start();
            return "Successfully started Apache!";
        }
        return "Apache is already running.";

    }

    public static HttpServer host(String domain, int port) {
        try {
            URI uri = UriBuilder.fromUri("http://" + domain + "/").port(port).build();
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new Service(Apache.class), HttpHandler.class);
            server.createContext(uri.getPath(), handler);
            server.start();
            return server;
        } catch (IOException ex) {
        }
        return null;
    }

    public static void main(String[] args) {
        Apache.host("localhost", 8081);
    }
}
