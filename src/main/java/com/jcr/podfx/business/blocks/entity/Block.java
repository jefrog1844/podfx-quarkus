package com.jcr.podfx.business.blocks.entity;

import java.util.HashSet;
import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.CascadeType;

@Entity
public class Block extends PodfxEntity implements Comparable<Block> {

    public static final String SYSTEM = "System";
    public static final String HIGH_LEVEL_SUBSYSTEM = "High-level subsystem";
    public static final String SUBSYSTEM = "Subsystem";
    public static final String COMPONENT = "Component";
    public static final String PART = "Part";

    public String name;
    public String type;

    @ManyToOne
    @JoinColumn(name = "PARENT_BLOCK_ID", nullable = true)
    private Block parent;

    @OneToMany(mappedBy = "parent",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Block> children = new TreeSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DFMEA_ID")
    private Dfmea dfmea;
    
    public Block() {

    }

    public Block(String name, String type, Block parent, Dfmea dfmea) {
        super();
        this.name = name;
        this.type = type;
        this.parent = parent;
        this.dfmea = dfmea;
    }

    @JsonbTransient
    public Dfmea getDfmea() {
        return dfmea;
    }

    public void setDfmea(Dfmea dfmea) {
        this.dfmea = dfmea;
    }

    public Set<Block> getChildren() {
        return children;
    }

    public void setChildren(Set<Block> children) {
        this.children = children;
    }

    public void addChild(Block child) {
        this.children.add(child);
        child.setParent(this);
    }

    public void removeChild(long blockId) {
        Block removed = null;
        for (Block b : children) {
            if (b.id == blockId) {
                removed = b;
            }
        }
        children.remove(removed);
    }

    @JsonbTransient
    public Block getParent() {
        return parent;
    }

    public void setParent(Block parent) {
        this.parent = parent;
    }

    public void updateParent() {
        if (parent != null && parent.getChildren() != null) {
            parent.getChildren().remove(this);
            setParent(null);
        }
    }
    
    public Long getParentId() {
        return parent == null ? new Long(0) : parent.id;
    }

    public Long getDfmeaId() {
        return dfmea == null ? new Long(0) : dfmea.id;
    }
    
    public void update(BlockDetail input) {
        this.name = input.getName();
        this.type = input.getType();
    }

    @Override
    public int compareTo(Block o) {
        return this.id.compareTo(o.id);
    }
}
