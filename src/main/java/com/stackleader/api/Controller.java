package com.stackleader.api;

import com.stackleader.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/v1/")
public class Controller {

    private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

    @Path("/kafka/test")
    @Consumes(MediaType.TEXT_PLAIN)
    @POST
    public Response postKafkaMessage (String message) {
        try {
            LOG.info(message);
            MessageService.sendMessageToTopic(message);
            return Response.status(Response.Status.ACCEPTED).build();
        }
        catch (IllegalArgumentException | NullPointerException e) {
            LOG.error(e.toString());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
