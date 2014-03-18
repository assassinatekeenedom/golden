package session.host.page;

import session.host.Callback;
import org.apache.log4j.Logger;

public class IndexJSONP {

    private static final Callback callback = Callback.getInstance();

    static {
        callback.appendContent("console.log('Uber Program!')");
        callback.commit();
        callback.toString(Logger.getLogger(IndexJSONP.class));
    }

    private IndexJSONP() {
    }

    public static String getJSONP() {
        return callback.toString();
    }

}
