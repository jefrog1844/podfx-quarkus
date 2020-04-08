package com.jcr.podfx.business.interfaces.boundary;

import com.jcr.podfx.business.interfaces.control.InterfaceController;
import com.jcr.podfx.business.interfaces.entity.Interface;
import com.jcr.podfx.business.interfaces.entity.InterfaceDetail;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jcr.podfx.business.interfaces.entity.Matrix;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
@Path("/dfmeas/{dfmeaId}/interfaces")
public class InterfacesResource {

    @Inject
    InterfaceController ic;

    @Path("{interfaceId}")
    @GET
    @RolesAllowed("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Interface getInterface(@PathParam("interfaceId") Long interfaceId) {
        Optional<Interface> optional = Interface.findByIdOptional(interfaceId);
        return optional.orElseThrow(() -> new NotFoundException());
    }

    @GET
    @RolesAllowed("read")
    @Path("matrix")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Matrix> getInterfaceMatrix(@PathParam("dfmeaId") Long dfmeaId) {
        return ic.getInterfaceMatrix(dfmeaId);
    }

    @Path("{interfaceId}")
    @PUT
    @Transactional
    @RolesAllowed("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(@PathParam("dfmeaId") Long dfmeaId, @PathParam("interfaceId") Long interfaceId,
            InterfaceDetail input) {
        Interface.update(
                "enabled=?1,physicalConnection=?2,energyTransfer=?3,materialExchange=?4,dataExchange=?5 where id=?6",
                input.isEnabled(), input.getPhysicalConnection(), input.getEnergyTransfer(),
                input.getMaterialExchange(), input.getDataExchange(), input.getId());
    }

    @GET
    @Transactional
    @RolesAllowed("read")
    @Path("generateFunktions")
    @Produces(MediaType.APPLICATION_JSON)
    public int generateFunktions(@PathParam("dfmeaId") Long dfmeaId) {
        return ic.generateFunktions(dfmeaId);
    }

};
