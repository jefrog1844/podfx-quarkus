package com.jcr.podfx.business.dfmeas.entity;

import java.time.LocalDate;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.blocks.entity.Block;
import com.jcr.podfx.business.factors.entity.Factor;
import com.jcr.podfx.business.funktions.entity.Funktion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;

@Entity
public class Dfmea extends PodfxEntity {

    @Column(unique = true, updatable = false, nullable = false)
    public String number;
    public String title;
    public String type;
    public String originator;
    public LocalDate originated;
    public LocalDate revised;
    public String teamMembers;
    public String partNumber;

    @OneToMany(
            mappedBy = "dfmea",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Factor> factors = new ArrayList<>();

    @OneToMany(
            mappedBy = "dfmea",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Block> blocks = new ArrayList<>();

    @OneToMany(
            mappedBy = "dfmea",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Funktion> funktions = new ArrayList<>();

    public Dfmea() {

    }

    public Dfmea(DfmeaDetail detail) {
        update(detail);
    }

    public void addBlock(Block block) {
        blocks.add(block);
        block.setDfmea(this);
    }

    public void addFactor(Factor factor) {
        factors.add(factor);
        factor.setDfmea(this);
    }

    public void removeFactor(Factor factor) {
        factors.remove(factor);
        factor.setDfmea(null);
    }

    @JsonbTransient
    public Collection<Block> getBlocks() {
        return blocks;
    }

    @JsonbTransient
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    @JsonbTransient
    public List<Factor> getFactors() {
        return factors;
    }

    @JsonbTransient
    public void setFactors(List<Factor> factors) {
        this.factors = factors;
    }

    public void addFunktion(Funktion funktion) {
        if (funktion != null) {
            funktions.add(funktion);
            funktion.setDfmea(this);
        }
    }

    public void update(DfmeaDetail input) {
        this.number = input.getNumber();
        this.title = input.getTitle();
        this.type = input.getType();
        this.teamMembers = input.getTeamMembers();
        this.partNumber = input.getPartNumber();
        this.revised = LocalDate.now();
        this.originator = input.getOriginator();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.number);
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
        final Dfmea other = (Dfmea) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        return true;
    }

}
