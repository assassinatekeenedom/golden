package html;

import org.apache.log4j.Logger;

public class Named implements Node, Visitor<Logger> {

    private String name = "";
    private String content = "";
    private String attributes = "";
    private String message = "";

    private Named() {
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
}
