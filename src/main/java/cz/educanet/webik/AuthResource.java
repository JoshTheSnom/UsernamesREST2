package cz.educanet.webik;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("authentication")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    @Inject
    private UserManager userManager;

    @Inject
    private LoginManager loginManager;

    @Path("/register")
    @POST
    public Response register(

            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("email") String email
    ) {
        User tempUser = new User(firstName, lastName, username, email, password);

        if(userManager.existByUsername(username)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Týpeček existuje").build();
        }
        else {
            userManager.userAdd(tempUser);
            return Response.ok("týpeček byl vytvořen").build();

        }
    }
    @Path("/login")
    @POST
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        User tempUser = userManager.getUserByUsernameAndPass(username, password);
        if (tempUser == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("týpeček neexistuje").build();
        } else {
            loginManager.loggers = tempUser;
            return Response.ok("týpeček byl přihlášen").build();
        }
    }
    @Path("/logout")
    @DELETE
    public Response logout() {
        loginManager.loggers = null;
        return Response.ok("týpeček byl úspěšně odhlášen").build();
    }
}
