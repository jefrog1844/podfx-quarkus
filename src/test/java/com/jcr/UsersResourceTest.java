/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import org.eclipse.microprofile.config.ConfigProvider;
import static org.hamcrest.Matchers.equalTo;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.jupiter.api.Tag;

/**
 *
 * @author 019535
 */
@QuarkusTest
@Tag("integration")
public class UsersResourceTest {

    private static final String USERNAME = ConfigProvider.getConfig().getValue("com.podfx.user.test.username", String.class);
    private static final String PASSWORD = ConfigProvider.getConfig().getValue("com.podfx.user.test.password", String.class);

    @Test
    public void testSignInEndpoint() {
        JSONObject body = new JSONObject();
        body.put("username", USERNAME);
        body.put("password", PASSWORD);
        given()
                .contentType("application/json")
                .body(body.toString())
                .post("/users/signIn")
                .then()
                .body("username", equalTo(USERNAME))
                .statusCode(200);
    }

}
