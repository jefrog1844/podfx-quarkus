/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.actions.entity;

import com.jcr.podfx.business.PodfxEntity;
import javax.persistence.Entity;

/**
 *
 * @author jeffrogers
 */
@Entity
public class Action extends PodfxEntity {
    public String name;
    public String type;
    public String status;
}
