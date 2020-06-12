package com.jcr.podfx.business.failureModes.entity;

import com.jcr.podfx.business.PodfxEntity;

import javax.persistence.Entity;

@Entity
public class FailureMode extends PodfxEntity {

    public static final String ABSENT = "ABSENT";
    public static final String PARTIAL = "PARTIAL";
    public static final String INTERMITTENT = "INTERMITTENT";
    public static final String EXCESS = "EXCESS";
    public static final String DECAYED = "DECAYED";
    public static final String TOO_SOON = "SOON";
    public static final String TOO_LATE = "LATE";
    public static final String INCORRECT = "INCORRECT";

    public String name;
    public String type;

    public FailureMode() {

    }
    
    public FailureMode(String name, String type) {
        this.name = name;
        this.type = type;
    }

}
