package com.jcr.podfx.business.funktions.boundary;


import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class FunktionFacade {

    @PersistenceContext
    EntityManager em;

   public int generateFunctions(List<String> funktions) {
        for (String f : funktions) {

        }
        return funktions != null ? funktions.size() : 0;
    }
}
