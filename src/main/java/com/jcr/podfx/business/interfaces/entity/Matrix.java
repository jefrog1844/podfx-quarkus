/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.interfaces.entity;

import com.jcr.podfx.business.factors.entity.Factor;
import com.jcr.podfx.business.factors.entity.FactorDetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author jeffrogers
 */
public class Matrix implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private FactorDetail inputFactor;
	private Set<InterfaceDetail> interfaces = new HashSet<>();

	public Matrix() {

	}

	public Matrix(Factor inputFactor, Set<Interface> interfaces) {
		this.inputFactor = new FactorDetail(inputFactor.id, inputFactor.name, inputFactor.type, inputFactor.category,
				inputFactor.getDfmea().id);

		for (Interface i : interfaces) {
			InterfaceDetail detail = new InterfaceDetail(i.id, i.getInputFactor().id, i.getOutputFactor().id, i.enabled,
					i.physicalConnection, i.energyTransfer, i.materialExchange, i.dataExchange);
			this.interfaces.add(detail);
		}

	}

	public FactorDetail getInputFactor() {
		return inputFactor;
	}

	public Set<InterfaceDetail> getInterfaces() {
		return interfaces;
	}

}
