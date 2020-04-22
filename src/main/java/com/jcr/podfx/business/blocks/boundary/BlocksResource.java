package com.jcr.podfx.business.blocks.boundary;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jcr.podfx.business.blocks.control.BlockController;
import com.jcr.podfx.business.blocks.entity.Block;
import com.jcr.podfx.business.blocks.entity.BlockDetail;

@ApplicationScoped
@Path("/dfmeas/{dfmeaId}/blocks")
public class BlocksResource {

    @Inject
    BlockController bc;

    /**
     *
     * @param dfmeaId
     * @return List of Block objects
     */
    @GET
    @RolesAllowed("read")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Block> listAll(@PathParam("dfmeaId") Long dfmeaId) {
        return bc.listAll(dfmeaId);
    }

    @Path("{blockId}")
    @GET
    @RolesAllowed("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Block findById(@PathParam("blockId") Long blockId) {
        return bc.findById(blockId);
    }

    @POST
    @RolesAllowed("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Block create(@PathParam("dfmeaId") Long dfmeaId, BlockDetail input) {
        return bc.persist(dfmeaId, input);
    }

    @Path("{blockId}")
    @PUT
    @RolesAllowed("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(@PathParam("dfmeaId") Long dfmeaId,
            @PathParam("blockId") Long blockId,
            BlockDetail input) {
        bc.update(input);
    }

    @Path("{blockId}")
    @DELETE
    @RolesAllowed("delete")
    public void delete(@PathParam("dfmeaId") Long dfmeaId,
            @PathParam("blockId") Long blockId) {
        bc.delete(blockId);
    }

    /**
     *
     * @param dfmeaId
     * @return
     */
    @GET
    @RolesAllowed("read")
    @Path("diagram")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Block> blockDiagram(@PathParam("dfmeaId") Long dfmeaId) {
        return bc.blockDiagram(dfmeaId);
    }

}
