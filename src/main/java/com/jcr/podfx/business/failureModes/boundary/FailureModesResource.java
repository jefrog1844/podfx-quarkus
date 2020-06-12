package com.jcr.podfx.business.failureModes.boundary;

import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import com.jcr.podfx.business.failureModes.control.FailureModeController;
import com.jcr.podfx.business.funktions.entity.Funktion;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Optional;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
@Path("/dfmeas/{dfmeaId}/failure-modes")
public class FailureModesResource {

    @Inject
    FailureModeController fc;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Funktion> getFailureModes(@PathParam("dfmeaId") Long dfmeaId) {
        return null;
    }

}
