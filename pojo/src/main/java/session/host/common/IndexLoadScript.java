package session.host.common;

import session.host.page.*;
import session.host.Callback;
import org.apache.log4j.Logger;

public class IndexLoadScript {

    private static final Callback callback = Callback.getInstance();

    static {

        callback.appendContent("alert('you found me!');");
        callback.commit();
        callback.toString(Logger.getLogger(IndexJSONP.class));
    }

    private IndexLoadScript() {
    }

    public static String getJSONP() {
        return callback.toString();
    }

}
