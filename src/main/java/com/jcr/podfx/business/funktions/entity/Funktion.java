package com.jcr.podfx.business.funktions.entity;

import com.jcr.podfx.business.PodfxEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jcr.podfx.business.dfmeas.entity.Dfmea;

@Entity
public class Funktion extends PodfxEntity {

    public String name;
    public String requirement;

    // need to add cascade annotation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DFMEA_ID")
    private Dfmea dfmea;

    public Funktion() {

    }


    @JsonbTransient
    public Dfmea getDfmea() {
        return dfmea;
    }

    public void setDfmea(Dfmea dfmea) {
        this.dfmea = dfmea;
    }

}
