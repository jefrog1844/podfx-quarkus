package com.jcr.podfx.business.factors.entity;

import java.util.HashSet;
import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.blocks.entity.Block;
import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import com.jcr.podfx.business.interfaces.entity.Interface;

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

    public static final String TYPE_INTERNAL = "INTERNAL";
    public static final String TYPE_EXTERNAL = "EXTERNAL";
    public static final String TYPE_SIGNAL = "SIGNAL";

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
    public Set<Interface> inputs = new HashSet<>();
    
    @OneToMany(
            mappedBy = "inputFactor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonbTransient
    public Set<Interface> outputs = new HashSet<>();
    

    public String type;
    public String name;
    public String category;

    public Factor() {
        
    }
    
    public Factor(String type, String name, String category) {
    	this.type = type;
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
        return type.equals(TYPE_INTERNAL);
    }

    public boolean isExternal() {
        return !isInternal();
    }

}
