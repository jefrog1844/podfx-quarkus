package com.jcr.podfx.business.factors.boundary;

import com.jcr.podfx.business.factors.control.FactorController;
import com.jcr.podfx.business.factors.entity.Factor;
import com.jcr.podfx.business.factors.entity.FactorDetail;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

@ApplicationScoped
@Path("/dfmeas/{dfmeaId}/factors")
public class FactorsResource {

    @Inject
    FactorController fc;

    @GET
    @RolesAllowed("read")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FactorDetail> listAll(@PathParam("dfmeaId") Long dfmeaId) {
        return fc.listAll(dfmeaId);
    }

    @Path("{factorId}")
    @GET
    @RolesAllowed("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Factor findById(@PathParam("dfmeaId") Long dfmeaId, @PathParam("factorId") Long factorId) {
        return fc.findById(factorId);
    }

    @POST
    @RolesAllowed("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void create(@PathParam("dfmeaId") Long dfmeaId, FactorDetail input) {
        fc.save(dfmeaId, input);
    }

    @Path("{factorId}")
    @PUT
    @RolesAllowed("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(@PathParam("dfmeaId") Long dfmeaId, @PathParam("factorId") Long factorId,
            FactorDetail input) {
       fc.update(input);
    }

    @Path("{factorId}")
    @DELETE
    @RolesAllowed("delete")
    public void delete(@PathParam("dfmeaId") Long dfmeaId, @PathParam("factorId") Long factorId) {
        fc.delete(factorId);
    }

}
