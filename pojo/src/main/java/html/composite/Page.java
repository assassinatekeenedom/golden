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
 * The root provides a mechanism for indeterminate HTML modeling.
 * 
 * e.g. <HTML></HTML> Templates.
 *
 * @author nate
 * @param <a>
 * @param <b>
 * @param <c>
 * @param <d>
 */
@Path("")
public class Page<a, b, c, d> implements Node<a, b, c>, Visitor<Logger, d>, Gateway<Page> {

    public static final Page page = Page.page;
    private final RESTful html = new RESTful("head", "title", "script", "body");

    private final a A;
    private final b B;
    private final c C;
    private final d D;

    protected Page(a A, b B, c C, d D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }

    public RESTful getHead() {
        return html.getInstance();
    }

    public RESTful getTitle() {
        return html.getInstance();
    }

    public RESTful getScript() {
        return html.getInstance();
    }

    public RESTful getBody() {
        return html.getInstance();
    }

    @Path("index.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public c commit() {
        this.html.setName("html");
        this.html.appendContent(A.toString());
        this.html.appendContent(B.toString());
        this.html.appendContent(C.toString());
        this.html.appendContent(D.toString());
        this.html.commit();
        return (c) this.C;
    }

    @Path("js/script.js")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public b setAttributes(Object attribute) {
        this.html.setAttributes(attribute);
        return (b) this.B;
    }

    @Path("head")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public a appendContent(Object content) {
        this.html.appendContent(content);
        return (a) this.A;
    }

    @Path("body")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public d toString(Logger log) {
        log.debug(this);
        return (d) this.D;
    }

    @Path("restart.html")
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Override
    public Page getInstance() {
        return page;
    }

    @Override
    public String toString() {
        return this.html.toString();
    }
}
