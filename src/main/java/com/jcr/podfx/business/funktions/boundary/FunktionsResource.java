/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.funktions.boundary;

import com.jcr.podfx.business.funktions.control.FunktionController;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Path("/dfmeas/{dfmeaId}/funktions")
public class FunktionsResource {

    @Inject
    FunktionController fc;

    @GET
    @Transactional
    @RolesAllowed("read")
    @Path("generateFunktions")
    @Produces(MediaType.APPLICATION_JSON)
    public int generateFunktions(@PathParam("dfmeaId") Long dfmeaId) {
        return fc.generateFunktions(dfmeaId);
    }

};
