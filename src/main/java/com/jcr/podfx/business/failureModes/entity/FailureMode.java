package com.jcr.podfx.business.failureModes.entity;

import com.jcr.podfx.business.funktions.entity.*;
import com.jcr.podfx.business.PodfxEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FailureMode extends PodfxEntity {

    public static final String ABSENT = "Absent";
    public static final String PARTIAL = "Incomplete or partial";
    public static final String INTERMITTENT = "Intermittent";
    public static final String EXCESS = "Excell";
    public static final String DECAYED = "Dcayed";
    public static final String TOO_SOON = "Occurs too soon";
    public static final String TOO_LATE = "Occurs too late";
    public static final String INCORRECT = "Incorrect";

    public String name;
    public String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNKTION_ID")
    private Funktion funktion;

    public FailureMode() {

    }

    public Funktion getFunktion() {
        return funktion;
    }

    public void setFunktion(Funktion funktion) {
        this.funktion = funktion;
    }

}
