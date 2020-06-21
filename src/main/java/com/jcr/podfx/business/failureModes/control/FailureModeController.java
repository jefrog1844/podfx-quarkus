/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.failureModes.control;

import com.jcr.podfx.business.failureModes.entity.UpdateDTO;
import com.jcr.podfx.business.funktions.control.FunktionController;
import com.jcr.podfx.business.funktions.entity.Funktion;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class FailureModeController {

    @Inject
    FunktionController fc;

    @Transactional
    public void update(UpdateDTO input) {
        Funktion f = fc.findById(input.getId());
        System.out.print(input);
        input.applyUpdates(f);
    }

}
