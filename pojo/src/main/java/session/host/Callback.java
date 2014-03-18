package session.host;

import html.Named;

public class Callback extends Named {

    private Callback() {
    }

    public static Callback getInstance() {
        return new Callback();
    }

}
