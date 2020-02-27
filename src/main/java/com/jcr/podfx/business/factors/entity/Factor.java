package com.jcr.podfx.business.factors.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.dfmeas.entity.Dfmea;

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
    private Dfmea dfmea;

    public String type;
    public String name;
    public String category;

    public Factor() {
        
    }
    
    public Factor(String id, String type, String name, String category) {
        this.id = id;
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
