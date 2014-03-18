package session.host;

import html.Named;
import html.Node;
import html.Visitor;
import org.apache.log4j.Logger;

public class Callback implements Node, Visitor<Logger> {

    private final Named head = Named.instance("");

    private Callback() {
    }

    public static Callback getInstance() {
        return new Callback();
    }

    @Override
    public void commit() {
        this.head.commit();
    }

    @Override
    public void setAttributes(Object attribute) {
        this.head.setAttributes(attribute);
    }

    @Override
    public void appendContent(Object content) {
        this.head.appendContent(content);
    }

    @Override
    public void toString(Logger log) {
        log.debug(this);
    }

    @Override
    public String toString() {
        return this.head.toString();
    }

}
