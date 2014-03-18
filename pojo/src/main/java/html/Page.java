package html;

import org.apache.log4j.Logger;

public class Page implements Node, Visitor<Logger> {

    private final Named head = Named.instance("head");
    private final Named title = Named.instance("title");
    private final Named script = Named.instance("script");
    private final Named body = Named.instance("body");
    private final Named html = Named.instance("html");

    private Page() {

    }

    public Named getHead() {
        return head;
    }

    public Named getTitle() {
        return title;
    }

    public Named getScript() {
        return script;
    }

    public Named getBody() {
        return body;
    }

    @Override
    public void commit() {
        this.html.setName("html");
        this.title.commit();
        this.head.appendContent(this.title);
        this.script.commit();
        this.head.appendContent(this.script);
        this.head.commit();
        this.html.appendContent(this.head);
        this.body.commit();
        this.html.appendContent(this.body);
        this.html.commit();
    }

    @Override
    public void setAttributes(Object attribute) {
        this.html.setAttributes(attribute);
    }

    @Override
    public void appendContent(Object content) {
        this.body.appendContent(content);
    }

    @Override
    public void toString(Logger log) {
        log.debug(this);
    }

    @Override
    public String toString() {
        return this.html.toString();
    }

    public static Page instance() {
        return new Page();
    }
}
