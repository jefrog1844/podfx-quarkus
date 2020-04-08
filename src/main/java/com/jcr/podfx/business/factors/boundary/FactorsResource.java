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
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotFoundException;
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
    public List<FactorDetail> find(@PathParam("dfmeaId") Long dfmeaId) {
        List<Factor> factors = Factor.find("DFMEA_ID", dfmeaId).list();
        return factors.stream().map(f -> new FactorDetail(f.id, f.name, f.type, f.category, f.getDfmea().id))
                .collect(Collectors.toList());
    }

    @Path("{factorId}")
    @GET
    @RolesAllowed("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Factor get(@PathParam("dfmeaId") Long dfmeaId, @PathParam("factorId") Long factorId) {
        Optional<Factor> optional = Factor.findByIdOptional(factorId);
        return optional.orElseThrow(() -> new NotFoundException());
    }

    @POST
    @Transactional
    @RolesAllowed("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void save(@PathParam("dfmeaId") Long dfmeaId, FactorDetail input) {
        fc.save(dfmeaId, input);
    }

    @Path("{factorId}")
    @PUT
    @Transactional
    @RolesAllowed("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(@PathParam("dfmeaId") Long dfmeaId, @PathParam("factorId") Long factorId,
            FactorDetail input) {
        Factor.update("name =?1,type=?2,category=?3 where id=?4", input.getName(), input.getType(), input.getCategory(),
                input.getId());
    }

    @Path("{factorId}")
    @DELETE
    @Transactional
    @RolesAllowed("delete")
    public void delete(@PathParam("dfmeaId") Long dfmeaId, @PathParam("factorId") Long factorId) {
        fc.delete(dfmeaId, factorId);
    }

}
