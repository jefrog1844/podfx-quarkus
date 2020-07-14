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
        
        updateMode(FailureMode.ABSENT, absent, f.tenant);
        updateMode(FailureMode.DECAYED, decay, f.tenant);
        updateMode(FailureMode.EXCESS, excess, f.tenant);
        updateMode(FailureMode.INCORRECT, incorrect, f.tenant);
        updateMode(FailureMode.INTERMITTENT, intermittent, f.tenant);
        updateMode(FailureMode.PARTIAL, partial, f.tenant);
        updateMode(FailureMode.TOO_LATE, late, f.tenant);
        updateMode(FailureMode.TOO_SOON, soon, f.tenant);
      
    }
    
    private void removeMode(String type) {
        this.modes.remove(type);
    }
    
    private void addMode(String type, String name, String tenant) {
        FailureMode mode = new FailureMode(name, type);
        mode.tenant = tenant;
        modes.put(type, mode);
    }
    
    private void updateMode(String type, String name, String tenant) {
        if(name.equals(null) || name.isEmpty()) {
            removeMode(type);
        } else if(!modes.containsKey(id)) {
            addMode(type, name, tenant);
        } else {
            FailureMode mode = modes.get(type);
            mode.name = name;
        }
    }
    
}
