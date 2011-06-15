package com.restbucks.ordering.representations;

import java.net.*;

import com.restbucks.ordering.domain.*;

public class RestbucksUri {
    private URI uri;

    public RestbucksUri( String uri ) {
        try {
            this.uri = new URI( uri );
        } catch ( URISyntaxException e ) {
            throw new RuntimeException( e );
        }
    }

    public RestbucksUri( URI uri ) {
        this( uri.toString() );
    }

    public RestbucksUri( URI uri, Identifier identifier ) {
        this( uri.toString() + "/" + identifier.toString() );
    }

    public Identifier getId() {
        String path = uri.getPath();
        return new Identifier( path.substring( path.lastIndexOf( "/" ) + 1, path.length() ) );
    }

    public URI getFullUri() {
        return uri;
    }

    public String toString() {
        return uri.toString();
    }

    public boolean equals( Object obj ) {
        if ( obj instanceof RestbucksUri ) {
            return ( (RestbucksUri) obj ).uri.equals( uri );
        }
        return false;
    }

    public String getBaseUri() {
        String port = "";
        if ( uri.getPort() != 80 && uri.getPort() != -1 ) {
            port = ":" + String.valueOf( uri.getPort() );
        }

        return uri.getScheme() + "://" + uri.getHost() + port;
    }
}
