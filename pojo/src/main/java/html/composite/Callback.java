package html.composite;

import html.Gateway;
import html.Node;
import html.Visitor;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 *
 * The root of this lies in the <script src=''> Asynchronous import of scripts.
 *
 * @author nate
 * @param <a>
 * @param <b>
 * @param <c>
 * @param <d>
 */
@Path("js")
public class Callback<a, b, c, d> implements Node<a, b, c>, Visitor<Logger, d>, Gateway<Callback> {

    public static final Callback callback = Callback.callback;
    private final RESTful jsonp = new RESTful("head", "title", "script", "body");

    private final a A;
    private final b B;
    private final c C;
    private final d D;

    protected Callback(a A, b B, c C, d D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }

    /**
     * @return refreshed <script> (required) request
     */
    @Path("restart.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public Callback getInstance() {
        return this;
    }

    /**
     * @return c <script> (required) request
     */
    @Path("logger.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public c commit() {
        this.jsonp.commit();
        return (c) this.C;
    }

    @Path("js/script.js")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public b setAttributes(Object attribute) {
        this.jsonp.setAttributes(attribute);
        return (b) this.B;
    }

    @Path("jsonp")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public a appendContent(Object content) {
        this.jsonp.appendContent(content);
        return (a) this.A;
    }

    @Override
    public d toString(Logger log) {
        log.debug(this);
        return (d) this.D;
    }

    @Override
    public String toString() {
        return this.jsonp.toString();
    }

}
