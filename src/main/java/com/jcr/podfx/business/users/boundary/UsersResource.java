package com.jcr.podfx.business.users.boundary;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jcr.podfx.business.users.entity.Credentials;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

@ApplicationScoped
@Path("/users")
public class UsersResource {

    @Inject
    UserFacade uf;

    @POST
    @Path("/signIn")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Credentials signIn(Credentials input) {
        return uf.signIn(input);
    }

}
