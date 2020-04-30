/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.funktions.control;

import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jcr.podfx.business.funktions.entity.Funktion;
import com.jcr.podfx.business.interfaces.entity.Interface;
import javax.transaction.Transactional;

@ApplicationScoped
public class FunktionController {

    @Inject
    EntityManager em;

    @Transactional
    public int generateFunktions(Long dfmeaId) {
        List<Interface> interfaces = em.createQuery(
                "select i from Interface i " + "JOIN Factor fInput on fInput.id = i.inputFactor.id "
                + "JOIN Factor fOutput on fOutput.id = i.outputFactor.id " + "where fInput.dfmea.id = :dfmeaId",
                Interface.class).setParameter("dfmeaId", dfmeaId).getResultList();

        List<String> funktions = interfaces.stream()
                .filter(i -> i.enabled)
                .flatMap(i -> Stream.of(i.physicalConnection, i.energyTransfer, i.materialExchange, i.dataExchange))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Dfmea dfmea = Dfmea.findById(dfmeaId);

        //need some way to determine if the function name already exists on an existing function and then not add it
        for (String name : funktions) {
            Funktion f = new Funktion();
            f.name = name;
            f.setDfmea(dfmea);
            f.persist();
        }

        return funktions.size();

    }

}
