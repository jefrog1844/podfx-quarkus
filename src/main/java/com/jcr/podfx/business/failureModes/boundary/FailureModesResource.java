package com.jcr.podfx.business.failureModes.boundary;

import com.jcr.podfx.business.failureModes.control.FailureModeController;
import com.jcr.podfx.business.failureModes.entity.UpdateDTO;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;

@ApplicationScoped
@Path("/funktions/{funktionId}/failure-modes")
public class FailureModesResource {

    @Inject
    FailureModeController fc;

    @PUT
    @RolesAllowed("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateFailureModes(UpdateDTO input) {
        fc.update(input);
    }

}
