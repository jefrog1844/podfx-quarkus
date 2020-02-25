package com.jcr.podfx.business.interfaces.control;

import com.jcr.podfx.business.factors.entity.Factor;
import com.jcr.podfx.business.funktions.boundary.FunktionFacade;
import com.jcr.podfx.business.interfaces.entity.Interface;
import com.jcr.podfx.business.interfaces.entity.Matrix;

import java.util.ArrayList;
import java.util.Collection;
import static java.util.Collections.singletonList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class InterfaceController {

    @Inject
    EntityManager em;

    @Inject
    FunktionFacade ff;

    public Collection<Matrix> getInterfaceMatrix(Long dfmeaId) {
        List<Interface> results = em.createQuery(
                "select i from Interface i " + "JOIN Factor fInput on fInput.id = i.inputFactor.id "
                + "JOIN Factor fOutput on fOutput.id = i.outputFactor.id " + "where fInput.dfmea.id = :dfmeaId",
                Interface.class).setParameter("dfmeaId", dfmeaId).getResultList();

        Collection<Matrix> matrices = results.stream().sorted((i1, i2) -> i1.getOutputFactor().name.compareTo(i2.getOutputFactor().name)).collect(toMap(Interface::getInputFactor,
                i -> new Matrix(i.getInputFactor(), new ArrayList<Interface>(singletonList(i))), (i1, i2) -> {
                    i1.getInterfaces().addAll(i2.getInterfaces());
                    return i1;
                })).values();

        return matrices;
    }

    @Transactional
    public int generateFunktions(long dfmeaId) {
        List<Interface> interfaces = em.createQuery(
                "select i from Interface i " + "JOIN Factor fInput on fInput.id = i.inputFactor.id "
                + "JOIN Factor fOutput on fOutput.id = i.outputFactor.id " + "where fInput.dfmea.id = :dfmeaId",
                Interface.class).setParameter("dfmeaId", dfmeaId).getResultList();

        List<String> funktions = interfaces.stream()
                .filter(i -> i.enabled)
                .flatMap(i -> Stream.of(i.physicalConnection, i.energyTransfer, i.materialExchange, i.dataExchange))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return ff.generateFunctions(funktions);
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
}
