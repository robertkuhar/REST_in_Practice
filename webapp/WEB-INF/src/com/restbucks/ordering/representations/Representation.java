package com.restbucks.ordering.representations;

import java.util.*;

import javax.xml.bind.annotation.*;

public abstract class Representation {
    public static final String RELATIONS_URI = "http://relations.restbucks.com/";
    public static final String RESTBUCKS_NAMESPACE = "http://schemas.restbucks.com";
    public static final String DAP_NAMESPACE = RESTBUCKS_NAMESPACE + "/dap";
    public static final String RESTBUCKS_MEDIA_TYPE = "application/vnd.restbucks+xml";
    public static final String SELF_REL_VALUE = "self";

    @XmlElement(name = "link", namespace = DAP_NAMESPACE)
    protected List< Link> links;

    protected Link getLinkByName( String uriName ) {
        if ( links == null ) {
            return null;
        }

        for ( Link l : links ) {
            if ( l.getRelValue().toLowerCase().equals( uriName.toLowerCase() ) ) {
                return l;
            }
        }
        return null;
    }
}
