package structure.command.user.story.load;

import structure.command.user.Session;

public class LoadBlack {

    private final Session session;

    public LoadBlack(String name) {
        this.session = new Session();
        session.setNamed(name);
        session.setSubject("window");
        session.setBody("<!-- loadBlack -->");
        session.setAction("document.body");
        session.setWhen("load");
        session.setScript("index.js");
        session.setSetting("style.backgroundColor");
        session.setOption("black");
    }

    public Session getSession() {
        return session;
    }

}
