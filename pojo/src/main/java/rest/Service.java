package rest;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import rest.service.Index;

public class Service extends Application {

    private final Set<Class<?>> classes;

    public Service() {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(Index.class);
        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    public static HttpServer host(String domain, int port) {
        try {
            URI uri = UriBuilder.fromUri("http://" + domain + "/").port(port).build();
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new Service(), HttpHandler.class);
            server.createContext(uri.getPath(), handler);
            server.start();
            return server;
        } catch (IOException ex) {
        }
        return null;
    }

    public static void main(String[] args) {
        Service.host("localhost", 8080);
    }
}
