package local.instance;

import local.Service;
import local.Master;
import java.io.IOException;
import local.Command;

public class Local implements Command {

    public enum HTML {

        CREATE(),
        BEFORE(),
        DONE();

        HTML() {
        }

        public HTML append(StringBuilder order, String join) {
            switch (this) {
                case CREATE:
                    order.append("<html><head><title>");
                    order.append(join);
                    order.append("</title>");
                    return BEFORE;
                case BEFORE:
                    order.append("<script type='text/javascript'>");
                    order.append("\nvar require=document.createElement('script');\n");
                    order.append("require.type='text/javascript';\n");
                    order.append("require.src='js/");
                    order.append(join);
                    order.append(".js';\n");
                    order.append("document.head.appendChild(require);\n");
                    order.append("document.head.removeChild(require);\n");
                    order.append("require=null,delete require;\n");
                    order.append("</script></head><body></body></html>");
                    return DONE;
            }
            return CREATE;
        }
    }
    private static final Master project = new Master();
    private final Service action;
    private HTML state;

    public Local(Service name) {
        this.action = name;
        this.state = HTML.CREATE;
    }

    public Service getAction() {
        return action;
    }

    public HTML getState() {
        return state;
    }

    @Override
    public String start() {
        StringBuilder message = new StringBuilder();
        try {
            action.start(project.getDrive(), project.getFolder(), project.getStartup());
            state = state.append(message, "Started " + action.getService());
            state = state.append(message, action.getService());
        } catch (IOException ex) {
            state = state.append(message, "Failed to start " + action.getService());
            state = state.append(message, "no-" + action.getService());
        }
        state = state.append(message, "");
        return message.toString();
    }

    @Override
    public String success() {
        StringBuilder script = new StringBuilder();
        script.append("console.info('this is the callback - Success!');");
        return script.toString();
    }

    @Override
    public String failure() {
        StringBuilder script = new StringBuilder();
        script.append("console.info('this is the callback - Failure!');");
        return script.toString();
    }

}
