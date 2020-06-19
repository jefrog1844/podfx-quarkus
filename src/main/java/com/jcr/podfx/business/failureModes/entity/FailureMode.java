package com.jcr.podfx.business.failureModes.entity;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.actions.entity.Action;
import com.jcr.podfx.business.causes.entity.Cause;
import com.jcr.podfx.business.effects.entity.Effect;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class FailureMode extends PodfxEntity {

    public static final String ABSENT = "ABSENT";
    public static final String PARTIAL = "PARTIAL";
    public static final String INTERMITTENT = "INTERMITTENT";
    public static final String EXCESS = "EXCESS";
    public static final String DECAYED = "DECAY";
    public static final String TOO_SOON = "SOON";
    public static final String TOO_LATE = "LATE";
    public static final String INCORRECT = "INCORRECT";

    public String name;
    public String type;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "FAILUREMODE_ID")
    private List<Cause> causes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "FAILUREMODE_ID")
    private List<Effect> effects = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "FAILUREMODE_ID")
    private List<Action> actions = new ArrayList<>();

    public FailureMode() {

    }

    public FailureMode(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public List<Cause> getCauses() {
        return causes;
    }

    public void setCauses(List<Cause> causes) {
        this.causes = causes;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.type);
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
        final FailureMode other = (FailureMode) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

}
