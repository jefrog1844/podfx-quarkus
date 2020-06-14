/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.causes.entity;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.controls.entity.Control;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author jeffrogers
 */
@Entity 
public class Cause extends PodfxEntity {
    
    public String name;
    public Integer occurence;
    public Integer detection;
    public String classification;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CAUSE_ID")
    private List<Control> controls = new ArrayList<>();
    
}
