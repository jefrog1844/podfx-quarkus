package com.jcr.podfx.business.interfaces.entity;

import com.jcr.podfx.business.PodfxEntity;
import com.jcr.podfx.business.factors.entity.Factor;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Interface extends PodfxEntity {

    public static final String IDENTITY = "identity";
    public static final String MIRROR = "mirror";
    public static final String NONE = "none";
    public static final String EXISTS = "exists";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OUTPUT_FACTOR_ID", nullable = true)
    private Factor outputFactor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INPUT_FACTOR_ID")
    private Factor inputFactor;

    public boolean enabled;
    public String physicalConnection;
    public String energyTransfer;
    public String dataExchange;
    public String materialExchange;

    public Interface() {
        
    }
    
    public Interface(Factor inputFactor, Factor outputFactor, boolean enabled) {
        this.inputFactor = inputFactor;
        this.outputFactor = outputFactor;
        this.enabled = enabled;
                
    }
    public boolean isInternal() {
        return Boolean.logicalAnd(inputFactor.isInternal(), outputFactor.isInternal());
    }

    public boolean containsFactor(Factor factor) {
        boolean result = false;
        if (inputFactor.equals(factor) || outputFactor.equals(factor)) {
            result = true;
        }
        return result;
    }

    public Factor getOutputFactor() {
        return outputFactor;
    }

    public void setOutputFactor(Factor outputFactor) {
        this.outputFactor = outputFactor;
    }

    public Factor getInputFactor() {
        return inputFactor;
    }

    public void setInputFactor(Factor inputFactor) {
        this.inputFactor = inputFactor;
    }
    
    public void update(InterfaceDetail input) {
        this.enabled = input.isEnabled();
        this.physicalConnection = input.getPhysicalConnection();
        this.energyTransfer = input.getEnergyTransfer();
        this.materialExchange = input.getMaterialExchange();
        this.dataExchange = input.getDataExchange();
    }

}
