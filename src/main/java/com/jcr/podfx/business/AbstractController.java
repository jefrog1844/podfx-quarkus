/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business;

import io.quarkus.arc.Arc;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.hibernate.Filter;
import org.hibernate.Session;

/**
 *
 * @author 019535
 */
public abstract class AbstractController {
    
    @Claim (standard = Claims.iss)
    protected String tenant;
    
    @PostConstruct
    public void hibernateFilter() {
        Session session = Arc.container().instance(EntityManager.class).get().unwrap(Session.class);
        Filter filter = session.enableFilter("tenantFilter");
        filter.setParameter("tenant", tenant);
        filter.validate();
    }
}
