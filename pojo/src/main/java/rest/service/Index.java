package rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import html.host.page.IndexHTML;
import session.host.page.IndexJSONP;
import java.io.IOException;

@Path("socket")
public class Index {

    private final String html = IndexHTML.getHTML();
    private final String jsonp = IndexJSONP.getJSONP();

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

}
