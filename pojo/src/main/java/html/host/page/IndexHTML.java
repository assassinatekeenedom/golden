package html.host.page;

import html.Page;
import org.apache.log4j.Logger;

public class IndexHTML {

    private static final Logger html = Logger.getLogger(IndexHTML.class);
    private static final Page page = new Page();

    {
        page.getScript().setAttributes("type='text/javascript' src='js/jsonp.js'");
        page.getTitle().appendContent("Hello Nate!");
        page.toString(html);
    }

    private IndexHTML() {
    }

    public static String getHTML() {
        return page.toString();
    }

}
