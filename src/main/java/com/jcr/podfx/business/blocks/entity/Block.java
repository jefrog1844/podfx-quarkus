package com.jcr.podfx.business.blocks.entity;

import java.util.Set;
import java.util.TreeSet;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.dfmeas.entity.Dfmea;

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

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Block> children = new TreeSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DFMEA_ID")
	private Dfmea dfmea;

	public Block() {

	}

	public Block(String id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
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

	public void removeChild(Block block) {
		children.remove(block);
	}

	@JsonbTransient
	public Block getParent() {
		return parent;
	}

	public void setParent(Block parent) {
		this.parent = parent;
	}

	public String getParentId() {
		return parent == null ? "" : parent.id;
	}

	public String getDfmeaId() {
		return dfmea == null ? "" : dfmea.id;
	}

	@Override
	public int compareTo(Block o) {
		return this.id.compareTo(o.id);
	}
}
