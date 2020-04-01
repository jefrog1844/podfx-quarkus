/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr;

import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import org.jose4j.json.internal.json_simple.JSONObject;

/**
 *
 * @author 019535
 */
@QuarkusTest
public class UsersResourceTest {

    @Test
    public void testSignInEndpoint() {
        JSONObject body = new JSONObject();
        body.put("username", "user");
        body.put("password", "123");
       given()
                .contentType("application/json")
                .body(body.toString())
                .post("/users/signIn")
                .then()
                .body("username", equalTo("user"));
        
        
    }

}
