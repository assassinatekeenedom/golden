package rest;

import html.Page;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class Service extends Application {

    private final Set<Class<?>> classes;

    public Service() {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(Page.class);
        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

}
