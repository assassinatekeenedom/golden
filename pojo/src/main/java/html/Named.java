package html;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class Named extends ArrayList<Logger> implements Node, Visitor<Logger> {

    private String name = "";
    private String content = "";
    private String attributes = "";
    private String message = "";

    public Named() {
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAttributes(Object content) {
        this.attributes = content.toString();
    }

    @Override
    public void appendContent(Object content) {
        this.content += content.toString();
    }

    @Override
    public void toString(Logger log) {
        log.debug(this.toString());
        this.add(log);
    }

    @Override
    public String toString() {
        return this.message;
    }

    @Override
    public void commit() {
        if (this.name.length() > 0) {
            this.message = "<" + this.name + " " + this.attributes + ">" + this.content + "</" + this.name + ">";
            return;
        }
        this.message = this.content;
    }

    public static Named instance(String name) {
        Named create = new Named();
        create.setName(name);
        return create;
    }

    @Override
    public List<Logger> getVisitors() {
        return this;
    }
}
