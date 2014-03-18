package html.composite.master.agent.artifact;

import html.composite.master.Service;

public class Apache extends Service {

    private final ProcessBuilder apache = new ProcessBuilder("c:/golden/conf/apache/startup.bat");
    private Process running;

    public static void main(String[] args) {
        Service service = Service.service;
        service.appendContent(Apache.class);
        service.commit();
    }
}
