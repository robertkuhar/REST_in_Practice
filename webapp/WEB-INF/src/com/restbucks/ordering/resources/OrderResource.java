package com.restbucks.ordering.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.restbucks.ordering.activities.*;
import com.restbucks.ordering.representations.*;

@Path("/order")
public class OrderResource {

    private @Context
    UriInfo uriInfo;

    public OrderResource() {
    }

    /**
     * Used in test cases only to allow the injection of a mock UriInfo.
     * 
     * @param uriInfo
     */
    public OrderResource( UriInfo uriInfo ) {
        this.uriInfo = uriInfo;
    }

    @GET
    @Path("/{orderId}")
    @Produces("application/vnd.restbucks+xml")
    public Response getOrder() {
        try {
            RestbucksUri uri = new RestbucksUri( uriInfo.getRequestUri() );
            ReadOrderActivity activity = new ReadOrderActivity();
            OrderRepresentation responseRepresentation = activity.retrieveByUri( uri );
            return Response.ok().entity( responseRepresentation ).build();
        } catch ( NoSuchOrderException nsoe ) {
            return Response.status( Status.NOT_FOUND ).build();
        } catch ( Exception ex ) {
            return Response.serverError().build();
        }
    }

    @POST
    @Consumes("application/vnd.restbucks+xml")
    @Produces("application/vnd.restbucks+xml")
    public Response createOrder( String orderRepresentation ) {
        try {
            RestbucksUri uri = new RestbucksUri( uriInfo.getRequestUri() );
            CreateOrderActivity activity = new CreateOrderActivity();
            OrderRepresentation reqRepresentation = OrderRepresentation.fromXmlString( orderRepresentation );
            OrderRepresentation respRepresentation = activity.create( reqRepresentation.getOrder(), uri );
            return Response.created( respRepresentation.getUpdateLink().getUri() )
                    .entity( respRepresentation ).build();
        } catch ( InvalidOrderException ioe ) {
            return Response.status( Status.BAD_REQUEST ).build();
        } catch ( Exception ex ) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{orderId}")
    @Produces("application/vnd.restbucks+xml")
    public Response removeOrder() {
        try {
            RestbucksUri uri = new RestbucksUri( uriInfo.getRequestUri() );
            RemoveOrderActivity activity = new RemoveOrderActivity();
            OrderRepresentation removedOrder = activity.delete( uri );
            return Response.ok().entity( removedOrder ).build();
        } catch ( NoSuchOrderException nsoe ) {
            return Response.status( Status.NOT_FOUND ).build();
        } catch ( OrderDeletionException ode ) {
            return Response.status( 405 ).header( "Allow", "GET" ).build();
        } catch ( Exception ex ) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/{orderId}")
    @Consumes("application/vnd.restbucks+xml")
    @Produces("application/vnd.restbucks+xml")
    public Response updateOrder( String orderRepresentation ) {
        try {
            RestbucksUri uri = new RestbucksUri( uriInfo.getRequestUri() );
            UpdateOrderActivity activity = new UpdateOrderActivity();
            OrderRepresentation reqRepresentation = OrderRepresentation.fromXmlString( orderRepresentation );
            OrderRepresentation respRepresentation = activity.update( reqRepresentation.getOrder(), uri );
            return Response.ok().entity( respRepresentation ).build();
        } catch ( InvalidOrderException ioe ) {
            return Response.status( Status.BAD_REQUEST ).build();
        } catch ( NoSuchOrderException nsoe ) {
            return Response.status( Status.NOT_FOUND ).build();
        } catch ( UpdateException ue ) {
            return Response.status( Status.CONFLICT ).build();
        } catch ( Exception ex ) {
            return Response.serverError().build();
        }
    }
}
