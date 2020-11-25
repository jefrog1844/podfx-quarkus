/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.funktions.control;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import com.jcr.podfx.business.funktions.entity.Funktion;
import com.jcr.podfx.business.funktions.entity.FunktionDetail;
import com.jcr.podfx.business.interfaces.entity.Interface;

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
            Funktion f = new Funktion(name);
            dfmea.getFunktions().add(f);
        }

        return funktions.size();

    }
    
    public List<Funktion> listAll (Long dfmeaId) {
        return Funktion.find("DFMEA_ID", dfmeaId).list();
    }
    
    public Funktion findById(Long funktionId) {
        Optional<Funktion> optional = Funktion.findByIdOptional(funktionId);
        return optional.orElseThrow(() -> new NotFoundException());
    }
    
    @Transactional
    public void update(FunktionDetail input) {
        Funktion f = findById(input.getId());
        f.name = input.getName();
        f.requirement = input.getRequirement();
    }
    
    @Transactional
    public void delete(Long funktionId) {
        Funktion.delete("id", funktionId);
    }

}
