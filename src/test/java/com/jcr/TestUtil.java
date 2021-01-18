/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr;

import com.jcr.podfx.business.users.entity.User;
import com.jcr.podfx.jwt.GenerateToken;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author 019535
 */
public class TestUtil {

    @ConfigProperty(name="com.podfx.user.test.username")
    static String USERNAME;
    
    @ConfigProperty(name="com.podfx.user.test.password")
    static String PASSWORD;
    
    public static String mockToken() throws Exception {
        User u = new User();
        u.username = USERNAME;
        u.password = PASSWORD;
        return GenerateToken.token(u);
    }
}
