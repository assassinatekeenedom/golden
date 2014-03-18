package session.host.page;

import session.host.Callback;
import org.apache.log4j.Logger;

public class IndexJSONP {

    private static final Logger js = Logger.getLogger(IndexJSONP.class);
    private static final Callback callback = Callback.getInstance();

    {
        callback.appendContent("console.log('Uber Program!')");
        callback.commit();
        callback.toString(js);
    }

    private IndexJSONP() {
    }

    public static String getJSONP() {
        return callback.toString();
    }

}
