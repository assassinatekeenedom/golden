package html.composite.master;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import html.Gateway;
import html.Node;
import html.Visitor;
import html.composite.Callback;
import html.composite.Page;
import html.composite.RESTful;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import org.apache.log4j.Logger;

public class Service extends Application implements Node<Application, Page, RESTful>, Visitor<Object, Logger>, Gateway<Service> {

    public static final Callback callback = Callback.callback;
    public static final Page page = Page.page;
    public static final RESTful rest = RESTful.rest;

    public static final Service service = new Service();

    private static final String domain = "http://localhost/";
    private static final int port = 8080;
    private static final URI REST = UriBuilder.fromUri(domain).port(port).build();
    private static final HashSet<Class<?>> urls = new HashSet<>();
    private static Set<Class<?>> classes;

    public Service() {
    }

    public void appendContent(Class content) {
        urls.add(content);
    }

    @Override
    public RESTful commit() {
        classes = Collections.unmodifiableSet(urls);
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(service, HttpHandler.class);
            server.createContext(REST.getPath(), handler);
            server.start();
        } catch (IOException ex) {
        }
        return rest;
    }

    @Override
    public Page setAttributes(Object attribute) {
        urls.add(attribute.getClass());
        return page;
    }

    @Override
    public Application appendContent(Object content) {
        urls.add(content.getClass());
        return this;
    }

    @Override
    public Logger toString(Object log) {
        urls.add(log.getClass());
        return Logger.getLogger(this.getClass());
    }

    @Override
    public Service getInstance() {
        return this;
    }
}
