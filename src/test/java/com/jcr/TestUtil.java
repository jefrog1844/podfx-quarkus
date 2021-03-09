/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr;

import com.jcr.podfx.business.users.entity.Role;
import com.jcr.podfx.business.users.entity.User;
import com.jcr.podfx.jwt.GenerateToken;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author 019535
 */
public class TestUtil {

    @ConfigProperty(name="com.podfx.user.test.username")
    static String username;
    
    @ConfigProperty(name="com.podfx.user.test.password")
    static String password;
    
    public static String mockToken() throws Exception {
        User u = new User();
        u.username = username;
        u.password = password;
        u.firstName = "pod";
        u.lastName = "fx";
        u.email = "user@podfx.com";
        u.tenant = "podfx1";
        u.roles.add(new Role(Long.valueOf(1),"create"));
        u.roles.add(new Role(Long.valueOf(2),"read"));
        u.roles.add(new Role(Long.valueOf(3),"update"));
        u.roles.add(new Role(Long.valueOf(4),"delete"));
        return GenerateToken.token(u);
    }
}
