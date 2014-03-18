package html.composite.master.agent;

import html.composite.master.Service;

public class Apache extends Service {

    public static void main(String[] args) {
        Service service = Service.service;
        service.appendContent(Apache.class);
        service.commit();
    }
}
