package com.jcr.podfx.business.blocks.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Transient;

@Entity
public class Block extends PodfxEntity implements Comparable<Block> {

    public static final String SYSTEM = "System";
    public static final String HIGH_LEVEL_SUBSYSTEM = "High-level subsystem";
    public static final String SUBSYSTEM = "Subsystem";
    public static final String COMPONENT = "Component";
    public static final String PART = "Part";

    public String name;
    public String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_BLOCK_ID", nullable = true)
    private Block parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DFMEA_ID")
    private Dfmea dfmea;
    
    @Transient
    private List<Block> children;

    public Block() {
        children = new ArrayList<>();
    }

    public Block(String name, String type) {
        super();
        this.name = name;
        this.type = type;
    }

    public Dfmea getDfmea() {
        return dfmea;
    }

    public void setDfmea(Dfmea dfmea) {
        this.dfmea = dfmea;
    }

    public Long getDfmeaId() {
        return dfmea.id;
    }
    
    @JsonbTransient
    public Block getParent() {
        return parent;
    }

    public void setParent(Block parent) {
        this.parent = parent;
    }

    public Long getParentId() {
        return parent == null ? Long.valueOf(0) : parent.id;
    }
    public void addChild(Block block) {
        children.add(block);
        //block.setParent(this);
    }
    
    public List<Block> getChildren() {
        return children;
    }
    
    @Override
    public int compareTo(Block o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.type);
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
        final Block other = (Block) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

}
