package com.restbucks.ordering.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.restbucks.ordering.activities.*;
import com.restbucks.ordering.domain.*;
import com.restbucks.ordering.representations.*;

@Path("/receipt")
public class ReceiptResource {

    private @Context
    UriInfo uriInfo;

    public ReceiptResource() {
    }

    /**
     * Used in test cases only to allow the injection of a mock UriInfo.
     * 
     * @param uriInfo
     */
    public ReceiptResource( UriInfo uriInfo ) {
        this.uriInfo = uriInfo;

    }

    @GET
    @Path("/{orderId}")
    @Produces("application/vnd.restbucks+xml")
    public Response getReceipt() {
        try {
            ReceiptRepresentation responseRepresentation = new ReadReceiptActivity().read( new RestbucksUri(
                    uriInfo.getRequestUri() ) );
            return Response.ok().entity( responseRepresentation ).build();
        } catch ( OrderAlreadyCompletedException oce ) {
            return Response.status( Status.NO_CONTENT ).build();
        } catch ( OrderNotPaidException onpe ) {
            return Response.status( Status.NOT_FOUND ).build();
        } catch ( NoSuchOrderException nsoe ) {
            return Response.status( Status.NOT_FOUND ).build();
        }
    }

    @DELETE
    @Path("/{orderId}")
    public Response completeOrder( @PathParam("orderId") String identifier ) {
        try {
            OrderRepresentation finalizedOrderRepresentation = new CompleteOrderActivity()
                    .completeOrder( new Identifier( identifier ) );
            return Response.ok().entity( finalizedOrderRepresentation ).build();
        } catch ( OrderAlreadyCompletedException oce ) {
            return Response.status( Status.NO_CONTENT ).build();
        } catch ( NoSuchOrderException nsoe ) {
            return Response.status( Status.NOT_FOUND ).build();
        } catch ( OrderNotPaidException onpe ) {
            return Response.status( Status.CONFLICT ).build();
        }
    }
}
