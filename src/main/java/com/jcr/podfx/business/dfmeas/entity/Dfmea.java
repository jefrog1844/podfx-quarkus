package com.jcr.podfx.business.dfmeas.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.blocks.entity.Block;
import com.jcr.podfx.business.factors.entity.Factor;
import com.jcr.podfx.business.funktions.entity.Funktion;

@Entity
public class Dfmea extends PodfxEntity {

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
    private Set<Factor> factors = new HashSet<>();

    @OneToMany(
            mappedBy = "dfmea",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Block> blocks = new HashSet<>();

    @OneToMany(
            mappedBy = "dfmea",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Funktion> funktions = new HashSet<>();

    
    
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
    public Set<Block> getBlocks() {
        return blocks;
    }

    @JsonbTransient
    public void setBlocks(Set<Block> blocks) {
        this.blocks = blocks;
    }

    @JsonbTransient
    public Set<Factor> getFactors() {
        return factors;
    }

    @JsonbTransient
    public void setFactors(Set<Factor> factors) {
        this.factors = factors;
    }
    
    public void addFunktion(Funktion funktion) {
        if(funktion != null) {
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
    
}
