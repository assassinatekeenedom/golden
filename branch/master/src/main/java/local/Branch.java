package local;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class Branch extends Application {

    private final Set<Class<?>> website = new HashSet<>();

    public Branch() {
    }

    public void append(Class<?> RESTful) {
        website.add(RESTful);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return website;
    }

}
