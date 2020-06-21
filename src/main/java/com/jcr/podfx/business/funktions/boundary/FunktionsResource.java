/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.funktions.boundary;

import com.jcr.podfx.business.funktions.control.FunktionController;
import com.jcr.podfx.business.funktions.entity.Funktion;
import com.jcr.podfx.business.funktions.entity.FunktionDetail;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;

@ApplicationScoped
@Path("/dfmeas/{dfmeaId}/funktions")
public class FunktionsResource {

    @Inject
    FunktionController fc;

    @GET
    @RolesAllowed("read")
    @Path("generateFunktions")
    @Produces(MediaType.APPLICATION_JSON)
    public int generateFunktions(@PathParam("dfmeaId") Long dfmeaId) {
        return fc.generateFunktions(dfmeaId);
    }
    
    @GET
    @RolesAllowed("read")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Funktion> listAll(@PathParam("dfmeaId") Long dfmeaId) {
        return fc.listAll(dfmeaId);
    }
    
    @Path("{funktionId}")
    @GET
    @RolesAllowed("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Funktion findById(@PathParam("funktionId") Long funktionId) {
        return fc.findById(funktionId);
    }
    
    @Path("{funktionId}")
    @PUT
    @RolesAllowed("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(FunktionDetail input) {
        fc.update(input);
    }
    
    @Path("{funktionId}")
    @DELETE
    @RolesAllowed("delete")
    public void delete(@PathParam("funktionId") Long funktionId) {
        fc.delete(funktionId);
    }
}
