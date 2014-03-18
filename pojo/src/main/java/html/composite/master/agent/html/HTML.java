package html.composite.master.agent.html;

import html.Gateway;
import html.Node;
import html.Visitor;
import html.composite.master.Service;
import org.apache.log4j.Logger;

/**
 * Selenium
 * @author nate
 */
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
        service.toString(Logger.getLogger(HTML.class));
        service.toString(log);
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
