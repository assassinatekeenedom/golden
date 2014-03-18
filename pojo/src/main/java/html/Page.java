package html;

public class Page extends Named {

    private final Named head = Named.instance("head");
    private final Named title = Named.instance("title");
    private final Named script = Named.instance("script");
    private final Named body = Named.instance("body");

    public Page() {
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
        this.setName("html");
        this.script.commit();
        this.body.commit();
        this.head.appendContent(this.title);
        this.head.appendContent(this.script);
        this.head.commit();
        this.appendContent(this.head);
        this.appendContent(this.body);
        super.commit();
    }

}
