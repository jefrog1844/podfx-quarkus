/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.failureModes.entity;

import com.jcr.podfx.business.funktions.entity.Funktion;
import java.util.Map;

/**
 *
 * @author jeffrogers
 */
public class UpdateDTO {
    private Long id;
    private String absent;
    private String partial;
    private String intermittent;
    private String excess;
    private String decay;
    private String soon;
    private String late;
    private String incorrect;
    private Map<String,FailureMode> modes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbsent() {
        return absent;
    }

    public void setAbsent(String absent) {
        this.absent = absent;
    }

    public String getPartial() {
        return partial;
    }

    public void setPartial(String partial) {
        this.partial = partial;
    }

    public String getIntermittent() {
        return intermittent;
    }

    public void setIntermittent(String intermittent) {
        this.intermittent = intermittent;
    }

    public String getExcess() {
        return excess;
    }

    public void setExcess(String excess) {
        this.excess = excess;
    }

    public String getDecay() {
        return decay;
    }

    public void setDecay(String decay) {
        this.decay = decay;
    }

    public String getSoon() {
        return soon;
    }

    public void setSoon(String soon) {
        this.soon = soon;
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }

    public String getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(String incorrect) {
        this.incorrect = incorrect;
    }

    @Override
    public String toString() {
        return "UpdateDTO{" + "id=" + id + ", absent=" + absent + ", partial=" + partial + ", intermittent=" + intermittent + ", excess=" + excess + ", decay=" + decay + ", soon=" + soon + ", late=" + late + ", incorrect=" + incorrect + '}';
    }
    
    public void applyUpdates(Funktion f) {
        this.modes = f.getFailureModes();
        
        updateMode(FailureMode.ABSENT, absent);
        updateMode(FailureMode.DECAYED, decay);
        updateMode(FailureMode.EXCESS, excess);
        updateMode(FailureMode.INCORRECT, incorrect);
        updateMode(FailureMode.INTERMITTENT, intermittent);
        updateMode(FailureMode.PARTIAL, partial);
        updateMode(FailureMode.TOO_LATE, late);
        updateMode(FailureMode.TOO_SOON, soon);
      
    }
    
    private void removeMode(String type) {
        this.modes.remove(type);
    }
    
    private void addMode(String type, String name) {
        FailureMode mode = new FailureMode(name, type);
        modes.put(type, mode);
    }
    
    private void updateMode(String type, String name) {
        if(name.equals(null) || name.isEmpty()) {
            removeMode(type);
        } else if(!modes.containsKey(type)) { //changed key from id to type jdr 11-24
            addMode(type, name);
        } else {
            FailureMode mode = modes.get(type);
            mode.name = name;
        }
    }
    
}
