package com.jcr.podfx.business.funktions.entity;

import java.io.Serializable;



public class FunktionDetail implements Serializable {

    private static final long serialVersionUID = 6487459757818072343L;

    private Long id;
    private String name;
    private Long dfmeaId;
    
    public FunktionDetail() {
        
    }

    public FunktionDetail(Long id, String name, Long dfmeaId) {
        this.id = id;
        this.name = name;
        this.dfmeaId = dfmeaId;
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

    public Long getDfmeaId() {
        return dfmeaId;
    }

    public void setDfmeaId(Long dfmeaId) {
        this.dfmeaId = dfmeaId;
    }

    
}
