/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import com.jcr.podfx.business.users.entity.User;
import com.jcr.podfx.jwt.GenerateToken;
import static io.restassured.RestAssured.given;

/**
 *
 * @author 019535
 */
@QuarkusTest
public class DfmeasResourceTest {
    
    @Test
    public void testFindEndPoint() throws Exception {
       
       
        given()
                .header("Authorization", "Bearer "+TestUtil.mockToken())
                .when()
                .get("/dfmeas")
                .then()
                .statusCode(200);
    }
}
