package com.jcr.podfx.business.dfmeas.entity;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.blocks.entity.Block;
import com.jcr.podfx.business.factors.entity.Factor;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.ws.rs.NotFoundException;

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

    public Dfmea() {
        
    }
    
    public Dfmea(DfmeaDetail detail) {
        update(detail);
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
    
    public void update(DfmeaDetail input) {
        this.number = input.getNumber();
        this.title = input.getTitle();
        this.type = input.getType();
        this.teamMembers = input.getTeamMembers();
        this.partNumber = input.getPartNumber();
        this.revised = LocalDate.now();
    }
    
}
