package session.host;

import html.Named;
import html.Visitor;
import org.apache.log4j.Logger;

public class Callback extends Named {

    private Callback() {
    }

    public static Callback getInstance() {
        return new Callback();
    }

}
