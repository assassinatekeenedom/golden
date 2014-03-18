package html.composite.master.agent;

import html.Gateway;
import html.Node;
import html.Visitor;
import html.composite.Callback;
import org.apache.log4j.Logger;

public class JSONP implements Node, Visitor<Logger>, Gateway<JSONP> {

    private static final Callback callback = Callback.callback;

    public static String getJSONP() {
        return callback.toString();
    }

    @Override
    public void commit() {
        callback.commit();
    }

    @Override
    public void setAttributes(Object attribute) {
        callback.setAttributes(attribute);
    }

    @Override
    public void appendContent(Object content) {
        callback.appendContent("var require=function(src){");
        callback.appendContent("var load = document.createElement('script');");
        callback.appendContent("load.type = 'text/javascript';");
        callback.appendContent("load.src = src;");
        callback.appendContent("document.head.appendChild(load);");
        callback.appendContent("document.head.removeChild(load);");
        callback.appendContent("};");
        callback.appendContent("window.addEventListener('load',function(){require('load/discover.js');});");
    }

    @Override
    public void toString(Logger log) {
        callback.toString(log);
    }

    @Override
    public JSONP getInstance() {
        return this;
    }

}
