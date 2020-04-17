/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr;

import com.jcr.podfx.business.users.entity.User;
import com.jcr.podfx.jwt.GenerateToken;
import com.jcr.podfx.jwt.TokenUtils;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author 019535
 */
@QuarkusTest
public class GenerateTokenTest {

    private static final String USERNAME = ConfigProvider.getConfig().getValue("com.podfx.user.test.username", String.class);
    private static final String PUBLIC_KEY = ConfigProvider.getConfig().getValue("mp.jwt.verify.publickey.location", String.class);
    private static final String ISSUER = ConfigProvider.getConfig().getValue("mp.jwt.verify.issuer", String.class);
    private static final String AUDIENCEE = ConfigProvider.getConfig().getValue("com.podfx.jwt.audience", String.class);

    @Inject
    JsonWebToken jwt;

    @Test
    public void testGenerateToken() throws Exception {
        boolean pass = false;

        User u = new User();
        u.setUsername(USERNAME);

        String jwt = GenerateToken.token(u);
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setExpectedIssuer(ISSUER) // whom the JWT needs to have been issued by
                .setExpectedAudience(AUDIENCEE) // to whom the JWT is intended for
                .setExpectedSubject(USERNAME) // username
                .setVerificationKey(TokenUtils.readPublicKey(PUBLIC_KEY)) // verify the signature with the public key
                .build(); // create the JwtConsumer instance

        try {
            //  Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            pass = true;
            Assertions.assertTrue(pass, "JWT validation succeeded! " + jwtClaims.getSubject());
        } catch (InvalidJwtException e) {
            // InvalidJwtException will be thrown, if the JWT failed processing or validation in anyway.
            // Hopefully with meaningful explanations(s) about what went wrong.
            Assertions.fail("Invalid JWT! " + e);
        }

    }
}
