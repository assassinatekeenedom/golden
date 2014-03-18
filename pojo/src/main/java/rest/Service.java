package rest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class Service extends Application {

    private final Set<Class<?>> classes;

    public Service(Class origin) {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(origin);
        classes = Collections.unmodifiableSet(c);
    }
    
    public void init(){
        
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    
}
