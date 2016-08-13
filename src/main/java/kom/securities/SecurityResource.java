package kom.securities;

import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("sec")
public class SecurityResource {

    private static final Logger LOGGER = Logger.getLogger(SecurityResource.class.getName());

    @Inject
    private SecurityService securityService;

    private Security security = new Security();

    List<String> list = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{name}")
    public List<String> getData(@PathParam("name") String path) {

        LOGGER.info("Set Ticker.");
        security.setTicket(path);
        LOGGER.info("Set Ticker.");

        LOGGER.info("Set Price.");
        security.setPrice(securityService.SearchPrice(security.getTicket()));

        LOGGER.info("Add Ticker.");
        list.add(security.getTicket());

        LOGGER.info("Add Price.");
        list.add(security.getPrice());
        return new ArrayList<>(list);
    }


}
