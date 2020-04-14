package com.jcr.podfx.business.interfaces.control;

import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jcr.podfx.business.factors.entity.Factor;
import com.jcr.podfx.business.funktions.boundary.FunktionFacade;
import com.jcr.podfx.business.funktions.entity.Funktion;
import com.jcr.podfx.business.interfaces.entity.Interface;
import com.jcr.podfx.business.interfaces.entity.Matrix;

@ApplicationScoped
public class InterfaceController {

    @Inject
    EntityManager em;

    @Inject
    FunktionFacade ff;

    public List<Matrix> getInterfaceMatrix(Long dfmeaId) {
        List<Factor> factors = Factor.find("type='INTERNAL' and dfmea_id=?1", dfmeaId).list();
        return factors.stream().map(f -> new Matrix(f)).collect(Collectors.toList());
    }

    //@Transactional
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
        for(String name : funktions) {
            Funktion f = new Funktion();
            f.name = name;
            dfmea.addFunktion(f);
        }
        
        return funktions.size();
        
    }

    public void deleteInterface(Factor factor) {

        List<Interface> interfaces = em
                .createQuery(
                        "select i from Interface i "
                        + "where (i.outputFactor.id = :factorId) OR (i.inputFactor.id = :factorId)",
                        Interface.class)
                .setParameter("factorId", factor.id).getResultList();
        for (Interface i : interfaces) {
            i.setInputFactor(null);
            i.setOutputFactor(null);
            em.remove(i);
        }
    }

    public void save(Factor input, Factor output) {
        Interface i = new Interface(input, output, false);
        i.persist();
    }
}
