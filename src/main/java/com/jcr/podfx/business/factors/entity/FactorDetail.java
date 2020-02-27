/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.factors.entity;

import java.io.Serializable;

public class FactorDetail implements Serializable {

	private static final long serialVersionUID = 8595328742127426385L;
	private String id;
	private String type;
	private String category;
	private String name;
	private String dfmeaId;

	public FactorDetail() {

	}

	public FactorDetail(String id, String name, String type, String category, String dfmeaId) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.category = category;
		this.dfmeaId = dfmeaId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDfmeaId() {
		return dfmeaId;
	}

	public void setDfmeaId(String dfmeaId) {
		this.dfmeaId = dfmeaId;
	}

}
