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
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author 019535
 */
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DfmeasResourceTest {

    @Test
    @Order(1)
    public void testFindEndPoint() throws Exception {

        given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .when()
                .get("dfmeas")
                .then()
                .statusCode(200)
                .body("size()", is(5));
    }

    @Test
    @Order(2)
    public void testSaveEndpoint() throws Exception {
        JSONObject body = new JSONObject();
        body.put("number", "FM-0100");
        body.put("title", "Wrist Watch");
        body.put("type", "System");
        body.put("originator", "mrogers");
        body.put("teamMembers", "mrogers,crogers,jrogers");
        body.put("partNumber", "WW-3000");
        body.put("id", 0);
        given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .contentType("application/json")
                .when()
                .body(body.toString())
                .post("dfmeas")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(3)
    public void testGetEndPoint() throws Exception {

        given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .when()
                .get("dfmeas/{dfmeaId}", 100)
                .then()
                .body("number", equalTo("FM-0100"))
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void testUpdateEndPoint() throws Exception {
        Dfmea dfmea = given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .get("dfmeas/{dfmeaId}", 100)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(Dfmea.class);
        
        JSONObject body = new JSONObject();
        body.put("id", dfmea.id);
        body.put("number", dfmea.number);
        body.put("title", dfmea.title);
        body.put("type", dfmea.type);
        body.put("originator", dfmea.originator);
        body.put("originated", dfmea.originated.toString());
        body.put("revised", dfmea.revised.toString());
        body.put("teamMembers", dfmea.teamMembers);
        body.put("partNumber", dfmea.partNumber+"-modified");
        
        given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .contentType("application/json")
                .when()
                .body(body)
                .put("dfmeas/{dfmeaId}", 100)
                .then()
                .statusCode(200)
                .body("partNumber", equalTo(dfmea.partNumber+"-modified"));
    }

    @Test
    @Order(5)
    public void testDeleteEndPoint() throws Exception {

        given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .when()
                .delete("dfmeas/{dfmeaId}", 100)
                .then()
                .statusCode(204);
    }

}
