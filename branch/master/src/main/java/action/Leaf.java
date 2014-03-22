package action;

import action.Gateway.Branch;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;

public class Leaf<I extends Branch> {

    private final I instance;
    private final URI URL;
    private final int port;

    public Leaf(I instance, String domain, int port) {
        this.instance = instance;
        this.URL = UriBuilder.fromUri(domain).port(port).build();
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String getURL() {
        return URL.getPath();
    }

    public I getInstance() {
        return instance;
    }

    public void RESTful() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(this.getPort()), 0);
            HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(this.getInstance(), HttpHandler.class);
            server.createContext(this.getURL(), handler);
            server.start();
        } catch (IOException | IllegalArgumentException | UnsupportedOperationException ex) {
        }
    }

}
