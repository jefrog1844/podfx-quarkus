package com.jcr.podfx.business.failureModes.boundary;

import com.jcr.podfx.business.failureModes.control.FailureModeController;
import com.jcr.podfx.business.failureModes.entity.FailureMode;
import com.jcr.podfx.business.funktions.entity.Funktion;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.Map;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Path("/dfmeas/{dfmeaId}/failure-modes")
public class FailureModesResource {

    @Inject
    FailureModeController fc;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Funktion, List<FailureMode>> getMatrix(@PathParam("dfmeaId") Long dfmeaId) {
        return fc.getMatrix(dfmeaId);
    }

}
