package com.jcr.podfx.business.funktions.entity;

import com.jcr.podfx.business.PodfxEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import com.jcr.podfx.business.failureModes.entity.FailureMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Funktion extends PodfxEntity {

    public String name;
    public String requirement;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "FUNKTION_ID")
    @MapKey(name = "type")
    private Map<String, FailureMode> failureModes = new HashMap<>();

    public Funktion() {

    }

    public Funktion(String name) {
        this.name = name;
    }

    public Map<String, FailureMode> getFailureModes() {
        return failureModes;
    }

    public void setFailureModes(Map<String, FailureMode> failureModes) {
        this.failureModes = failureModes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
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
        final Funktion other = (Funktion) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
