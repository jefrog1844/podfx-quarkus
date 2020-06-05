/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.failureModes.control;

import com.jcr.podfx.business.failureModes.entity.FailureMode;
import java.util.List;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jcr.podfx.business.funktions.entity.Funktion;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;

@ApplicationScoped
public class FailureModeController {

    @Inject
    EntityManager em;

    public Map<Funktion, List<FailureMode>> getMatrix(Long dfmeaId) {

        Stream<FailureMode> fmodes = em.createQuery("select fm from FailureMode fm "
                + "JOIN Funktion f on f.id = fm.funktion.id "
                + "where f.dfmea.id = :dfmeaId", FailureMode.class).
                setParameter("dfmeaId", dfmeaId).
                getResultList().stream();

        Map<Funktion, List<FailureMode>> matrix = fmodes
                .collect(groupingBy(FailureMode::getFunktion));
        return matrix;
    }

}
