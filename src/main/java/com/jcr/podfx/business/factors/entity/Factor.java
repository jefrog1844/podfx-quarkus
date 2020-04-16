package com.jcr.podfx.business.factors.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import com.jcr.podfx.business.interfaces.entity.Interface;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Factor extends PodfxEntity {

    public static final String CATEGORY_ENVIRONMENTAL_INTERACTION = "Environmental";
    public static final String CATEGORY_SYSTEM_INTERACTION = "System";
    public static final String CATEGORY_CUSTOMER_USAGE = "Customer";
    public static final String CATEGORY_VARIATION = "Variation";
    public static final String CATEGORY_CHANGES_TIME = "Changes";
    public static final String CATEGORY_SIGNAL = "Signal";
    public static final String CATEGORY_IDEAL = "Ideal";
    public static final String CATEGORY_ERROR = "Error";
    public static final String CATEGORY_CONTROL = "Control";
    public static final String CATEGORY_INTERNAL_INTERACTION = "Internal";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DFMEA_ID")
    @JsonbTransient
    private Dfmea dfmea;

    @OneToMany(
            mappedBy = "outputFactor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonbTransient
    public List<Interface> inputs = new ArrayList<>();

    @OneToMany(
            mappedBy = "inputFactor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonbTransient
    public List<Interface> outputs = new ArrayList<>();

    public String name;
    public String category;

    public Factor() {

    }

    public Factor(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Dfmea getDfmea() {
        return dfmea;
    }

    public void setDfmea(Dfmea dfmea) {
        this.dfmea = dfmea;
    }

    public boolean isInternal() {
        return category.equals(CATEGORY_INTERNAL_INTERACTION);
    }

    public boolean isExternal() {
        return !isInternal();
    }

}
