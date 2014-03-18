package html.composite.master.agent;

import html.Gateway;
import html.Node;
import html.Visitor;
import static html.composite.Callback.callback;
import html.composite.master.Service;
import org.apache.log4j.Logger;

public class HTML implements Node<Logger, HTML, Service>, Gateway<HTML>, Visitor<Logger, HTML> {

    private static final Service service = Service.service;

    private HTML() {
    }

    @Override
    public Service commit() {
        service.commit();
        return service;
    }

    @Override
    public Logger appendContent(Object content) {
        service.appendContent(content);
        return Logger.getLogger(HTML.class);
    }

    @Override
    public HTML toString(Logger log) {
        callback.toString(Logger.getLogger(HTML.class));
        callback.toString(log);
        return this;
    }

    @Override
    public HTML setAttributes(Object attribute) {
        service.setAttributes(attribute);
        return this;
    }

    @Override
    public HTML getInstance() {
        return this;
    }

}
