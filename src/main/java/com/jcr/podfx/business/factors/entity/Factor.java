package com.jcr.podfx.business.factors.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import java.util.Objects;

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
    private Dfmea dfmea;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Factor other = (Factor) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }

   

}
