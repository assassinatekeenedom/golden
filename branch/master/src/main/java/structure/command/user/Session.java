package structure.command.user;

public class Session {

    private String action;
    private String subject;
    private String named;
    private String script;
    private String body;
    private String option;
    private String setting;
    private String when;

    public Session() {
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setNamed(String named) {
        this.named = named;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNamed() {
        return named;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getBody() {
        return body;
    }

}
