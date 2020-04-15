/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.blocks.control;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import com.jcr.podfx.business.blocks.entity.Block;
import com.jcr.podfx.business.blocks.entity.BlockDetail;
import com.jcr.podfx.business.dfmeas.entity.Dfmea;

@ApplicationScoped
public class BlockController {

    public void persist(Long dfmeaId, BlockDetail input) {
        if (input != null) {
            Block parent = null;
            Dfmea dfmea = Dfmea.findById(dfmeaId);
            if (input.getParentId() != null && !input.getParentId().equals(Long.valueOf(0))) {
                Optional<Block> optional = Block.findByIdOptional(input.getParentId());
                parent = optional.orElseThrow(() -> new NotFoundException());
            }

            Block block = new Block(input.getName(), input.getType());
            if (parent != null) {
                parent.addChild(block);
            }
            dfmea.addBlock(block);
        }
    }

    public void updateParent(Block block, BlockDetail input) {
        if (input.getParentId() != null) {
            if (!input.getParentId().equals(block.getParentId())) {
                Optional<Block> optional = Block.findByIdOptional(input.getParentId());
                Block newParent = optional.orElseThrow(() -> new NotFoundException());
                newParent.addChild(block);
            }
        }
    }

}
