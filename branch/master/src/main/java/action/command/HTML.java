package action.command;

import action.Command;
import action.Gateway;
import action.Gateway.Status;
import action.Service;

public class HTML implements Command {

    private Service service;
    private Gateway gateway;

    public HTML(Service name) {
        this.service = name;
    }

    public void setService(Service action) {
        this.service = action;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    public Service getService() {
        return service;
    }

    public Gateway getGateway() {
        return gateway;
    }

    @Override
    public String start() {
        if (Status.START.equals(this.gateway.startup(this.service))) {
            return "start";
        }
        return "error";
    }

}
