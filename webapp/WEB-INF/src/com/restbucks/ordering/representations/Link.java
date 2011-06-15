package com.restbucks.ordering.representations;

import java.net.*;

import javax.xml.bind.annotation.*;

@XmlRootElement(namespace = Representation.DAP_NAMESPACE)
public class Link {
    @XmlAttribute(name = "rel")
    private String rel;
    @XmlAttribute(name = "uri")
    private String uri;

    @XmlAttribute(name = "mediaType")
    private String mediaType;

    /**
     * For JAXB :-(
     */
    Link() {
    }

    public Link( String name, RestbucksUri uri, String mediaType ) {
        this.rel = name;
        this.uri = uri.getFullUri().toString();
        this.mediaType = mediaType;

    }

    public Link( String name, RestbucksUri uri ) {
        this( name, uri, Representation.RESTBUCKS_MEDIA_TYPE );
    }

    public String getRelValue() {
        return rel;
    }

    public URI getUri() {
        try {
            return new URI( uri );
        } catch ( URISyntaxException e ) {
            throw new RuntimeException( e );
        }
    }

    public String getMediaType() {
        return mediaType;
    }
}
