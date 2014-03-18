package html.composite.master.agent.jsonp;

import html.Gateway;
import html.Node;
import html.Visitor;
import html.composite.master.Service;
import org.apache.log4j.Logger;

public class JSONP implements Node<Logger, JSONP, Service>, Visitor<Logger, JSONP>, Gateway<JSONP> {

    /**
     * Verify with JSONP callbacks through the Fitnesse Wiki
     */
    private static final Service service = Service.service;

    @Override
    public JSONP getInstance() {
        return this;
    }

    @Override
    public Service commit() {
        service.commit();
        return service;
    }

    @Override
    public JSONP setAttributes(Object attribute) {
        service.setAttributes(attribute);
        return this;
    }

    @Override
    public Logger appendContent(Object content) {
        service.appendContent("var require=function(src){");
        service.appendContent("var load = document.createElement('script');");
        service.appendContent("load.type = 'text/javascript';");
        service.appendContent("load.src = src;");
        service.appendContent("document.head.appendChild(load);");
        service.appendContent("document.head.removeChild(load);");
        service.appendContent("};");
        service.appendContent("window.addEventListener('load',function(){require('load/discover.js');});");
        return Logger.getLogger(JSONP.class);
    }

    @Override
    public JSONP toString(Logger log) {
        service.toString(Logger.getLogger(JSONP.class));
        service.toString(log);
        return this;
    }

}
