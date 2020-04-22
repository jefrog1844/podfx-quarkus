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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;

@ApplicationScoped
public class BlockController {

    public List<Block> listAll(Long dfmeaId) {
        return Block.find("DFMEA_ID", dfmeaId).list();
    }

    public Block findById(Long blockId) {
        Optional<Block> optional = Block.findByIdOptional(blockId);
        return optional.orElseThrow(() -> new NotFoundException());
    }

    @Transactional
    public Block persist(Long dfmeaId, BlockDetail input) {
        Block block = null;
        if (input != null) {
            //create new Block
            block = new Block(input.getName(), input.getType());

            //set parent
            Block parent = null;
            if (input.getParentId() != null && !input.getParentId().equals(Long.valueOf(0))) {
                Optional<Block> optional = Block.findByIdOptional(input.getParentId());
                parent = optional.orElseThrow(() -> new NotFoundException());
            }
            block.setParent(parent);

            //set Dfmea
            Dfmea dfmea = Dfmea.findById(dfmeaId);
            block.setDfmea(dfmea);
            block.persist();
        }
        return block;
    }

    @Transactional
    public void update(BlockDetail input) {
        Block.update("name = ?1, type= ?2, PARENT_BLOCK_ID=?3 where id =?4", input.getName(), input.getType(), input.getParentId(), input.getId());
    }

    @Transactional
    public void delete(Long blockId) {
        Block.delete("PARENT_BLOCK_ID", blockId);
        Block.delete("id", blockId);
    }

    public List<Block> blockDiagram(Long dfmeaId) {
        List<Block> blocks = Block.find("DFMEA_ID", dfmeaId).list();
        List<Block> diagram = new ArrayList<>(1);
        if (blocks != null && !blocks.isEmpty()) {
            Block root = buildBlockDiagram(blocks);
            diagram.add(root);
        }
        return diagram;

    }

    private Block buildBlockDiagram(List<Block> blocks) {
        Map<Long,Block> blockMap = new HashMap<>();
        Block root = null;
        for (Block b : blocks) {
            if (b.getParent() == null) {
                root = b;
            }
            blockMap.put(b.id, b);
        }
        
        for(Block current : blocks) {
            if(current.getParent() != null) {
                Block parent = blockMap.get(current.getParent().id);
                parent.addChild(current);
            }
        }
        return root;
    }

}
