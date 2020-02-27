/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.blocks.entity;

import java.io.Serializable;

/**
 *
 * @author jeffrogers
 */
public class BlockDetail implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4961011122899551814L;
	private String id;
	private String name;
	private String type;
	private String dfmeaId;
	private String parentId;

	public BlockDetail() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDfmeaId() {
		return dfmeaId;
	}

	public void setDfmeaId(String dfmeaId) {
		this.dfmeaId = dfmeaId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
