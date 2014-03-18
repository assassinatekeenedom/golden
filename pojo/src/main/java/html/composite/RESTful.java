package html.composite;

import html.Gateway;
import html.Master;
import html.Node;
import html.Visitor;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

@Path("rest")
public class RESTful<a, b, c, d> implements Node<String, String, String>, Visitor<Logger, String>, Gateway<RESTful>, Master<String> {

    public static final RESTful rest = new RESTful("", "", "", "");

    private String name;
    private String content;
    private String attributes;
    private String message;

    public RESTful(String a, String b, String c, String d) {
        this.name = a;
        this.content = b;
        this.attributes = c;
        this.message = d;
    }

    @Path("{new}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String setName(@PathParam("new") String name) {
        this.name = name;
        return this.name;
    }

    @Path("js/{script}.js")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String setAttributes(@PathParam("script") Object content) {
        this.attributes = content.toString();
        return this.attributes;
    }

    @Path("jsonp")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String appendContent(@QueryParam("jsonp") Object content) {
        this.content += content.toString();
        return this.content;
    }

    @Path("jsonp")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String toString(@PathParam("script") Logger log) {
        String temp = this.toString();
        log.debug(temp);
        return temp;
    }

    @Override
    public String toString() {
        return this.message;
    }

    @Path("index.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public String commit() {
        if (this.name.length() > 0) {
            this.message = "<" + this.name + " " + this.attributes + ">" + this.content + "</" + this.name + ">";
            return message;
        }
        this.message = this.content;
        return message;
    }

    @Path("restart.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public RESTful getInstance() {
        return new RESTful(name, content, attributes, message);
    }
}
