/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr;

import com.jcr.podfx.business.blocks.control.BlockController;
import com.jcr.podfx.business.blocks.entity.Block;
import com.jcr.podfx.business.blocks.entity.BlockDetail;
import io.quarkus.test.junit.QuarkusTest;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;

/**
 *
 * @author 019535
 */
@QuarkusTest
public class BlockControllerTest {
    
    @Test
    @Transactional
    public void testPersist() {
        BlockController bc = new BlockController();
        BlockDetail bd = new BlockDetail();
        bd.setId(Long.valueOf(0));
        bd.setName("Cotter Pin");
        bd.setParentId(Long.valueOf(12));
        bd.setType(Block.PART);
        bd.setDfmeaId(Long.valueOf(1));
        bc.persist(Long.valueOf(1), bd);
    }
}
