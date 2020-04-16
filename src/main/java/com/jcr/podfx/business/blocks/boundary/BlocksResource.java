package com.jcr.podfx.business.blocks.boundary;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
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
        return Block.find("DFMEA_ID", dfmeaId).list();
    }

    @Path("{blockId}")
    @GET
    @RolesAllowed("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Block findById(@PathParam("blockId") Long blockId) {
        Optional<Block> optional = Block.findByIdOptional(blockId);
        return optional.orElseThrow(() -> new NotFoundException());
    }

    @POST
    @Transactional
    @RolesAllowed("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void create(@PathParam("dfmeaId") Long dfmeaId, BlockDetail input) {
        bc.persist(dfmeaId, input);
    }

    @Path("{blockId}")
    @PUT
    @Transactional
    @RolesAllowed("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(@PathParam("dfmeaId") Long dfmeaId,
            @PathParam("blockId") Long blockId,
            BlockDetail input) {
        Block.update("name = ?1, type= ?2 where id =?3", input.getName(), input.getType(), input.getId());
        bc.updateParent(findById(blockId), input);
    }

    @Path("{blockId}")
    @DELETE
    @Transactional
    @RolesAllowed("delete")
    public void delete(@PathParam("dfmeaId") Long dfmeaId,
            @PathParam("blockId") Long blockId) {
        Block removed = Block.findById(blockId); //need to delete children also
        if (removed.isPersistent()) {
            removed.delete();
        }
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
        return Block.find("DFMEA_ID = ?1 and PARENT_BLOCK_ID IS NULL", dfmeaId).list();
    }

}
