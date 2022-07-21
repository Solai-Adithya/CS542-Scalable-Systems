package org.acme.microprofile.faulttolerance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/audio")
public class AudioService {
    
    @GET
    public String Main() {
        return "Hello Audio";
    }
}
