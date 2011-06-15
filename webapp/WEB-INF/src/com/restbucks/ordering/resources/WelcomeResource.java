package com.restbucks.ordering.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/")
public class WelcomeResource {

    private @Context
    UriInfo uriInfo;

    public WelcomeResource() {
    }

    @GET
    @Produces("text/html")
    public Response welcomeCustomer() {
        return Response
                .ok()
                .entity(
                        String.format(
                                "<html><body><h1>Welcome to Restbucks</h1><p>You can place your order by POSTing to %s</p></body></html>",
                                uriInfo.getAbsolutePath() + "order" ) ).build();
    }

}
