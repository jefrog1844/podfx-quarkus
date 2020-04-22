/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.interfaces.entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.jcr.podfx.business.factors.entity.Factor;
import com.jcr.podfx.business.factors.entity.FactorDetail;
import java.util.HashSet;

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
		return new FactorDetail(inputFactor.id, inputFactor.name, inputFactor.category,
				inputFactor.getDfmea().id);
	}

	public Set<Map<String, Object>> getInternalInterfaces() {
		return buildResponse(true);
	}

	public Set<Map<String, Object>> getExternalInterfaces() {
		return buildResponse(false);
	}

	private Set<Map<String, Object>> buildResponse(boolean internal) {
		Predicate<Interface> p = getInternalPredicate(internal);
		//Set<Interface> interfaces = inputFactor.outputs.stream().filter(p).collect(Collectors.toSet());
		return initiateInterfaceMap(new HashSet<>());
	}

	private Set<Map<String, Object>> initiateInterfaceMap(Set<Interface> interfaces) {
		Set<Map<String, Object>> interfaceMap = new TreeSet<Map<String, Object>>(comparator());
		for (Interface i : interfaces) {
			interfaceMap.add(populateMap(i));
		}
		return interfaceMap;
	}

	private Comparator<Map<String, Object>> comparator() {
		return new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return o1.get("outputFactorName").toString().compareTo(o2.get("outputFactorName").toString());
			}

		};
	}

	private Predicate<Interface> getInternalPredicate(boolean test) {
		Predicate<Interface> p = new Predicate<Interface>() {

			@Override
			public boolean test(Interface t) {
				return t.isInternal() == test;
			}
		};
		return p;
	}

	private Map<String, Object> populateMap(Interface i) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("physicalConnection", i.physicalConnection);
		attrs.put("energyTransfer", i.energyTransfer);
		attrs.put("materialExchange", i.materialExchange);
		attrs.put("dataExchange", i.dataExchange);
		attrs.put("outputFactorName", i.getOutputFactor().name);
		attrs.put("enabled", i.enabled);
		attrs.put("id", i.id);
		return attrs;
	}
}
