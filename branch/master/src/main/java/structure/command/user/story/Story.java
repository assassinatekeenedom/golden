package structure.command.user.story;

import action.Command;
import structure.Callback;
import structure.command.HTML;
import structure.command.user.Session;

public class Story implements Callback, Command {

    private final Session user;
    private String declare;
    private String callback;
    private String start;

    public Story(Session user) {
        this.user = user;
    }

    public String getDeclare() {
        return declare;
    }

    public String getCallback() {
        return callback;
    }

    public String getStart() {
        return start;
    }

    @Override
    public String callback() {
        callback = this.getCallback() == null ? user.getSubject() + ".addEventListener('" + user.getWhen() + "',function(){" + this.action() + "});" : this.getCallback();
        return callback;
    }

    @Override
    public String action() {
        declare = this.getDeclare() == null ? user.getAction() + "." + user.getSetting() + "='" + user.getOption() + "';" : this.getDeclare();
        return declare;
    }

    @Override
    public String start() {
        if (start == null) {
            StringBuilder builder = new StringBuilder();
            HTML.TITLE.next(builder, user.getNamed()).next(builder, user.getScript()).next(builder, user.getBody()).next(builder, "");
            start = builder.toString();
        }
        return start;
    }

}
