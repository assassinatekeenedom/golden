package session.host.page;

import session.host.Callback;
import org.apache.log4j.Logger;

public class IndexJSONP {

    private static final Callback callback = Callback.getInstance();

    static {

        callback.appendContent("var require=function(src){");
        callback.appendContent("var load = document.createElement('script');");
        callback.appendContent("load.type = 'text/javascript';");
        callback.appendContent("load.src = src;");
        callback.appendContent("document.head.appendChild(load);");
        callback.appendContent("document.head.removeChild(load);");
        callback.appendContent("};");
        callback.appendContent("window.addEventListener('load',function(){require('load/discover.js');});");
        callback.commit();
        callback.toString(Logger.getLogger(IndexJSONP.class));
    }

    private IndexJSONP() {
    }

    public static String getJSONP() {
        return callback.toString();
    }

}
