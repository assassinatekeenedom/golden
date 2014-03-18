package html.composite.master.agent;

import html.composite.Page;
import html.composite.RESTful;
import html.composite.master.Service;
import javax.ws.rs.Path;

@Path("")
public class ApacheIndexHTML extends Service {

    public static final Page index = Page.page;

    private final String jsonp = HTML.getJSONP();
    private final RESTful html = new RESTful("head", "title", "script", "body");

    ApacheIndexHTML() {
    }

}
