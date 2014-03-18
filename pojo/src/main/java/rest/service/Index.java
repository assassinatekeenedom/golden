package rest.service;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import html.host.page.IndexHTML;
import session.host.page.IndexJSONP;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import rest.Service;
import session.host.common.IndexLoadScript;

@Path("socket")
public class Index {

    private final String html = IndexHTML.getHTML();
    private final String jsonp = IndexJSONP.getJSONP();
    private final String discover = IndexLoadScript.getJSONP();

    @Path("index.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String html() throws IOException {
        return html;
    }

    @Path("js/jsonp.js")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String script() throws IOException {
        return jsonp;
    }

    @Path("load/{named}.js")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String jsonp(@PathParam("named") String name) throws IOException {
        return "console.warn('this example requires apache to host a script');require('js/" + name + ".js')";
    }

    public static HttpServer host(String domain, int port) {
        try {
            URI uri = UriBuilder.fromUri("http://" + domain + "/").port(port).build();
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new Service(Index.class), HttpHandler.class);
            server.createContext(uri.getPath(), handler);
            server.start();
            return server;
        } catch (IOException ex) {
        }
        return null;
    }

    public static void main(String[] args) {
        Index.host("localhost", 8080);
    }
}
