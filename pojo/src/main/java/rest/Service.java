package rest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
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

    
}
