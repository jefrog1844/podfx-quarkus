/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.causes.entity;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.actions.entity.Action;
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
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CAUSE_ID")
    private List<Action> actions = new ArrayList<>();

    public List<Control> getControls() {
        return controls;
    }

    public void setControls(List<Control> controls) {
        this.controls = controls;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
    
    
}
