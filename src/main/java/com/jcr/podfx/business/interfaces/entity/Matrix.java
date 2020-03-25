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
	private Factor inputFactor;

	public Matrix() {

	}

	public Matrix(Factor inputFactor) {
		this.inputFactor = inputFactor;
	}

	public FactorDetail getInputFactor() {
		return new FactorDetail(inputFactor.id, inputFactor.name, inputFactor.type, inputFactor.category,
				inputFactor.getDfmea().id);
	}

	public Set<InterfaceDetail> getInternalInterfaces() {
		Set<InterfaceDetail> interfaces = new HashSet<>();
		for (Interface i : inputFactor.outputs) {
			
			if (i.isInternal()) {
				System.out.println(i.getInputFactor().name+"--->"+i.getOutputFactor().name);
				InterfaceDetail detail = new InterfaceDetail(i.id, i.getInputFactor().id, i.getOutputFactor().id,
						i.enabled, i.physicalConnection, i.energyTransfer, i.materialExchange, i.dataExchange);
				interfaces.add(detail);
			}
		}

		return interfaces;
	}

	public Set<InterfaceDetail> getExternalInterfaces() {
		Set<InterfaceDetail> interfaces = new HashSet<>();
		for (Interface i : inputFactor.outputs) {
			
			if (!i.isInternal()) {
				System.out.println(i.getInputFactor().name+"--->"+i.getOutputFactor().name);
				InterfaceDetail detail = new InterfaceDetail(i.id, i.getInputFactor().id, i.getOutputFactor().id,
						i.enabled, i.physicalConnection, i.energyTransfer, i.materialExchange, i.dataExchange);
				interfaces.add(detail);
			}
		}

		return interfaces;
	}

}
