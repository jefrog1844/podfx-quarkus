/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.blocks.entity;

import java.io.Serializable;

/**
 *
 * @author jeffrogers
 */
public class BlockDetail implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4961011122899551814L;
    private Long id;
    private String name;
    private String type;
    private Long dfmeaId = new Long(0);
    private Long parentId;

    public BlockDetail() {
        
    }
    
    public BlockDetail(Long id, String name, String type, Long dfmeaId, Long parentId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dfmeaId = dfmeaId;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDfmeaId() {
        return dfmeaId;
    }

    public void setDfmeaId(Long dfmeaId) {
        this.dfmeaId = dfmeaId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
