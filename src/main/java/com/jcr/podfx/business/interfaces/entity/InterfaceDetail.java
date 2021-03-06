/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.interfaces.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author 019535
 */
public class InterfaceDetail implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private boolean enabled;
    private String physicalConnection;
    private String energyTransfer;
    private String dataExchange;
    private String materialExchange;

    public InterfaceDetail() {

    }

    public InterfaceDetail(Long id, boolean enabled, String physicalConnection,
            String energyTranfer, String materialExchange, String dataExchange) {
        this.id = id;
        this.enabled = enabled;
        this.physicalConnection = physicalConnection;
        this.energyTransfer = energyTranfer;
        this.materialExchange = materialExchange;
        this.dataExchange = dataExchange;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final InterfaceDetail other = (InterfaceDetail) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getPhysicalConnection() {
        return physicalConnection;
    }

    public void setPhysicalConnection(String physicalConnection) {
        this.physicalConnection = physicalConnection;
    }

    public String getEnergyTransfer() {
        return energyTransfer;
    }

    public void setEnergyTransfer(String energyTransfer) {
        this.energyTransfer = energyTransfer;
    }

    public String getMaterialExchange() {
        return materialExchange;
    }

    public void setMaterialExchange(String materialExchange) {
        this.materialExchange = materialExchange;
    }

    public String getDataExchange() {
        return dataExchange;
    }

    public void setDataExchange(String dataExchange) {
        this.dataExchange = dataExchange;
    }

}
