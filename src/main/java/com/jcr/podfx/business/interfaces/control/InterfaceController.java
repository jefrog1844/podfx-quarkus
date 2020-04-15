package com.jcr.podfx.business.interfaces.control;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jcr.podfx.business.factors.entity.Factor;
import com.jcr.podfx.business.interfaces.entity.Interface;
import com.jcr.podfx.business.interfaces.entity.Matrix;

@ApplicationScoped
public class InterfaceController {

    @Inject
    EntityManager em;

    public List<Matrix> getInterfaceMatrix(Long dfmeaId) {
        List<Factor> factors = Factor.find("type='INTERNAL' and dfmea_id=?1", dfmeaId).list();
        return factors.stream().map(f -> new Matrix(f)).collect(Collectors.toList());
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
