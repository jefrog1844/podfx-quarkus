package com.jcr.podfx.business.funktions.entity;

import com.jcr.podfx.business.PodfxEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import com.jcr.podfx.business.failureModes.entity.FailureMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Funktion extends PodfxEntity {

    public String name;
    public String requirement;

    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "FUNKTION_ID")
    //private List<FailureMode> failureModes = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "FUNKTION_ID")
    @MapKey(name = "type")
    private Map<String, FailureMode> modeMap = new HashMap<>();

    public Funktion() {

    }
    
    public Funktion(String name) {
        this.name = name;
        //failureModes.add(new FailureMode("",FailureMode.ABSENT));
        //failureModes.add(new FailureMode("",FailureMode.DECAYED));
        //failureModes.add(new FailureMode("",FailureMode.EXCESS));
        //failureModes.add(new FailureMode("",FailureMode.INCORRECT));
        //failureModes.add(new FailureMode("",FailureMode.INTERMITTENT));
        //failureModes.add(new FailureMode("",FailureMode.PARTIAL));
        //failureModes.add(new FailureMode("",FailureMode.TOO_LATE));
        //failureModes.add(new FailureMode("",FailureMode.TOO_SOON));
        /*
        modeMap.put(FailureMode.ABSENT,new FailureMode("",FailureMode.ABSENT));
        modeMap.put(FailureMode.DECAYED,new FailureMode("",FailureMode.DECAYED));
        modeMap.put(FailureMode.EXCESS,new FailureMode("",FailureMode.EXCESS));
        modeMap.put(FailureMode.INCORRECT,new FailureMode("",FailureMode.INCORRECT));
        modeMap.put(FailureMode.INTERMITTENT,new FailureMode("",FailureMode.INTERMITTENT));
        modeMap.put(FailureMode.PARTIAL,new FailureMode("",FailureMode.PARTIAL));
        modeMap.put(FailureMode.TOO_LATE,new FailureMode("",FailureMode.TOO_LATE));
        modeMap.put(FailureMode.TOO_SOON,new FailureMode("",FailureMode.TOO_SOON));
*/
    }

    public List<FailureMode> getFailureModes() {
        //return failureModes;
        return null;
    }

    public void setFailureModes(List<FailureMode> failureModes) {
        //this.failureModes = failureModes;
    }

    public Map<String, FailureMode> getModeMap() {
        return modeMap;
    }

    public void setModeMap(Map<String, FailureMode> modeMap) {
        this.modeMap = modeMap;
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
