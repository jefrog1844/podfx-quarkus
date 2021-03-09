package com.jcr.podfx.business.dfmeas.boundary;

import com.jcr.podfx.business.dfmeas.control.DfmeaController;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import com.jcr.podfx.business.dfmeas.entity.DfmeaDetail;

import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;

import javax.inject.Inject;

@ApplicationScoped
@Path("/dfmeas")
@SecuritySchemes(value = {
    @SecurityScheme(securitySchemeName = "apiKey", 
                    type = SecuritySchemeType.HTTP,
                    scheme = "Bearer",
                    bearerFormat = "JWT")}
)
public class DfmeasResource {
    
    @Inject
    DfmeaController dc;
    
    @GET
    @RolesAllowed("read")
    @SecurityRequirement(name = "apiKey")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dfmea> listAll() {
        return dc.listAll();
    }
    
    @POST
    @RolesAllowed("create")
    @SecurityRequirement(name = "apiKey")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Dfmea create(DfmeaDetail input) {
        return dc.create(input);
    }
    
    @Path("{dfmeaId}")
    @GET
    @RolesAllowed("read")
    @SecurityRequirement(name = "apiKey")
    @Produces(MediaType.APPLICATION_JSON)
    public Dfmea findById(@PathParam("dfmeaId") Long id) {
        return dc.findById(id);
    }
    
    @Path("{dfmeaId}")
    @DELETE
    @RolesAllowed("delete")
    @SecurityRequirement(name = "apiKey")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("dfmeaId") Long id) {
        dc.delete(id);
    }
    
    @Path("{dfmeaId}")
    @PUT
    @RolesAllowed("update")
    @SecurityRequirement(name = "apiKey")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(@PathParam("dfmeaId") Long dfmeaId,
            DfmeaDetail input) {
        dc.update(dfmeaId, input);
    }
    
    @GET
    @Path("search")
    @RolesAllowed("read")
    @SecurityRequirement(name = "apiKey")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dfmea> search(@Context UriInfo info	) {
        String title = info.getQueryParameters().getFirst("title");
        return dc.search(title);
    }
    
}
