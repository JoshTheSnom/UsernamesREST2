package cz.educanet.webik;

import sun.rmi.runtime.Log;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Inject;


@Path("user") //localhost:8080/webik/api/ ||users||
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    private LoginManager loginManager;

    @GET
    public Response getLoggedUser(){
        if(loginManager.loggers != null) {
            return Response.ok(loginManager.loggers.getUsername()).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("týpeček není příhlášen").build();
        }
    }


}
