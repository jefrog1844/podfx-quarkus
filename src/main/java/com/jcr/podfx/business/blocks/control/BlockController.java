/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.blocks.control;

import com.jcr.podfx.business.blocks.entity.Block;
import com.jcr.podfx.business.blocks.entity.BlockDetail;
import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import io.quarkus.panache.common.Sort;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class BlockController {

    @Inject
    EntityManager em;

    public void save(Long dfmeaId, BlockDetail input) {
        if (input != null) {
            Block parent = null;
            Dfmea dfmea = Dfmea.findById(dfmeaId);
            if (input.getParentId() != null & input.getParentId() != 0) {
                Optional<Block> optional = Block.findByIdOptional(input.getParentId());
                parent = optional.orElseThrow(() -> new NotFoundException());
            }

            Block block = new Block(input.getName(), input.getType(), parent, dfmea);
            //if (parent != null) {
                //parent.addChild(block);
            //}
            //dfmea.getBlocks().add(block);
            //block.setDfmea(dfmea);
            block.persist();
            
        }
    }

    public void updateParent(Block block, BlockDetail input) {
        if (input.getParentId() != null & input.getParentId() != 0) {
            if (input.getParentId() != block.getParent().id) { //check if parent has changed
                Optional<Block> optional = Block.findByIdOptional(input.getParentId());
                Block newParent = optional.orElseThrow(() -> new NotFoundException());
                block.setParent(newParent);
            }
        }
    }

}
