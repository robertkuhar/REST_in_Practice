package com.restbucks.ordering.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.restbucks.ordering.activities.*;
import com.restbucks.ordering.domain.*;
import com.restbucks.ordering.representations.*;

@Path("/payment/{paymentId}")
public class PaymentResource {

    private @Context
    UriInfo uriInfo;

    public PaymentResource() {
    }

    /**
     * Used in test cases only to allow the injection of a mock UriInfo.
     * 
     * @param uriInfo
     */
    public PaymentResource( UriInfo uriInfo ) {
        this.uriInfo = uriInfo;
    }

    @PUT
    @Consumes("application/vnd.restbucks+xml")
    @Produces("application/vnd.restbucks+xml")
    public Response pay( PaymentRepresentation paymentRepresentation ) {
        try {
            return Response
                    .created( uriInfo.getRequestUri() )
                    .entity(
                            new PaymentActivity().pay( paymentRepresentation.getPayment(), new RestbucksUri(
                                    uriInfo.getRequestUri() ) ) ).build();
        } catch ( NoSuchOrderException nsoe ) {
            return Response.status( Status.NOT_FOUND ).build();
        } catch ( UpdateException ue ) {
            Identifier identifier = new RestbucksUri( uriInfo.getRequestUri() ).getId();
            Link link = new Link( Representation.SELF_REL_VALUE, new RestbucksUri( uriInfo.getBaseUri()
                    .toString() + "order/" + identifier ) );
            return Response.status( Status.FORBIDDEN ).entity( link ).build();
        } catch ( InvalidPaymentException ipe ) {
            return Response.status( Status.BAD_REQUEST ).build();
        } catch ( Exception e ) {
            return Response.serverError().build();
        }
    }
}
