package html.composite.master.agent;

import html.composite.master.Service;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.IOException;

@Path("")
public final class ApacheService extends Service {
    
    private final ProcessBuilder apache = new ProcessBuilder("c:/golden/conf/apache/startup.bat");
    private Process running;
    
    public ApacheService() {
        super();
    }
    
    @Path("start")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String html() throws IOException {
        if (running == null) {
            running = apache.start();
            return "Successfully started Apache!";
        }
        return "Apache is already running.";
    }
    
    public static void main(String[] args) {
        ApacheService service = new ApacheService();
        service.appendContent(ApacheService.class);
        service.commit();
    }
}
