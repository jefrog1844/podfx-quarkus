/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business;

import io.quarkus.arc.Unremovable;
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 *
 * @author jeffrogers
 */
@RequestScoped
@Unremovable
public class PodfxTenantResolver implements TenantResolver {
    @Inject
    JsonWebToken jwt;
    @Override
    public String getDefaultTenantId() {
        return "podfx";
    }

    @Override
    public String resolveTenantId() {
        //update to get tenant from jwt
        String tenant = jwt.getClaim("tenant");
        if(tenant == null) {
            return getDefaultTenantId();
        } else {
            return tenant;
        }
        
    }
    
}
