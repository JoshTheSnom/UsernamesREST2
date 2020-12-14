package cz.educanet.webik;

import sun.rmi.runtime.Log;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Inject;


@Path("users") //localhost:8080/webik/api/users
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserManager userManager;

    @Inject
    private LoginManager loginManager;

    @POST
    public Response register(

        @FormParam("firstName") String firstName,
        @FormParam("lastName") String lastName,
        @FormParam("username") String username,
        @FormParam("password") String password,
        @FormParam("email") String email
    ) {
        User tempUser = new User(firstName, lastName, username, password, email);

        if(userManager.existByUsername(username)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Týpeček existuje").build();
        }
        else {
            userManager.userAdd(tempUser);
            return Response.ok("týpeček byl vytvořen").build();

        }
    }
    public Response login(
        @FormParam("username") String username,
        @FormParam("password") String password
    ) {
        User tempUser = userManager.getUserByUsernameAndPass(username, password);
        if (tempUser == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("týpeček neexistuje").build();
        } else {
            loginManager.loggers = tempUser;
            return Response.ok().build();
        }
    }
    @GET
    public Response getLoggedUser(){
        if(loginManager.loggers != null) {
            return Response.ok(loginManager.loggers.getUsername()).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).entity("týpeček není zaregistrován nebo neexistuje").build();
        }
    }
    @DELETE
    public Response logout() {
        loginManager.loggers = null;
        return Response.ok().build();
    }

}
