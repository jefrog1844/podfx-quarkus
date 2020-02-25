/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.interfaces.entity;

import com.jcr.podfx.business.factors.entity.Factor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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
    private List<Interface> interfaces = new ArrayList<>();
    
    public Matrix() {
        
    }
    
    public Matrix (Factor inputFactor, List<Interface> interfaces) {
        this.inputFactor = inputFactor;
        this.interfaces = interfaces;
    }

    public Factor getInputFactor() {
        return inputFactor;
    }

    public void setInputFactor(Factor inputFactor) {
        this.inputFactor = inputFactor;
    }

    public List<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Interface> interfaces) {
        this.interfaces = interfaces;
    }
    
}
