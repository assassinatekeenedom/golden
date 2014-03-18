package session.host.page;

import session.host.Callback;
import org.apache.log4j.Logger;

public class IndexJSONP {

    private static final Callback callback = Callback.getInstance();

    static {

        callback.appendContent("var load = document.createElement('script');");
        callback.appendContent("\nload.type = 'text/javascript';");
        callback.appendContent("\nload.src = 'js/callback.js';");
        callback.appendContent("\ndocument.head.appendChild(load);");
        callback.appendContent("\ndocument.head.removeChild(load);");
        callback.commit();
        callback.toString(Logger.getLogger(IndexJSONP.class));
    }

    private IndexJSONP() {
    }

    public static String getJSONP() {
        return callback.toString();
    }

}
