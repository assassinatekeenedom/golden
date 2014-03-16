package com.golden;

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

public enum Gateway {

    HTTP("localhost", 8080);

    private static class FactoryGold extends Application {

        private final Set<Class<?>> classes;

        public FactoryGold() {
            HashSet<Class<?>> c = new HashSet<>();
            c.add(RestMVC.class);
            classes = Collections.unmodifiableSet(c);
        }

        @Override
        public Set<Class<?>> getClasses() {
            return classes;
        }
    }

    private final String domain;
    private final int port;

    Gateway(String domain, int port) {
        this.domain = domain;
        this.port = port;
    }

    public void host() throws IOException {
        Gateway.host(this.domain, this.port);
    }

    public static HttpServer host(String domain, int port) {
        try {
            URI uri = UriBuilder.fromUri("http://" + domain + "/").port(port).build();
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new FactoryGold(), HttpHandler.class);
            server.createContext(uri.getPath(), handler);
            server.start();
            return server;
        } catch (IOException ex) {
            System.out.println("Failure! " + ex.getMessage());
        }
        return null;
    }

}
