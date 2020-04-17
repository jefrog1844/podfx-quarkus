/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr;

import com.jcr.podfx.business.blocks.entity.Block;
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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author 019535
 */
@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlocksResourceTest {

    @Test
    @Order(1)
    public void testFindByDfmeaId() throws Exception {

        given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .when()
                .get("dfmeas/{dfmeaId}/blocks", 1)
                .then()
                .statusCode(200)
                .body("size()", is(15));
    }

    @Test
    @Order(2)
    public void testPersistBlock() throws Exception {
        JSONObject body = new JSONObject();
        body.put("name", "Cotter Pin");
        body.put("type", "Part");
        body.put("dfmeaId", 1);
        body.put("parentId", 12);
        body.put("id", 0);
        given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .contentType("application/json")
                .when()
                .body(body.toString())
                .post("dfmeas/{dfmeaId}/blocks", 1)
                .then()
                .statusCode(204);
    }

    @Test
    @Order(2)
    public void testBlockGetByid() throws Exception {
        given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .when()
                .get("dfmeas/{dfmeaId}/blocks/{blockId}", 1, 100)
                .then()
                .body("id", equalTo(100))
                .body("name", equalTo("Cotter Pin"))
                .body("type", equalTo("Part"))
                .statusCode(200);
    }
    
    @Test
    @Order(3)
    public void testUpdateEndPoint() throws Exception {
        JSONObject block = given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .get("dfmeas/{dfmeaId}/blocks/{blockId}", 1,100)
                .then()
                .statusCode(200)
                .body("id", equalTo(100))
                .contentType(ContentType.JSON)
                .extract()
                .as(JSONObject.class);
        
        JSONObject body = new JSONObject();
        body.put("id", block.get("id"));
        body.put("name", block.get("name")+" (2X)");
        body.put("type", block.get("type"));
        body.put("dfmeaId", 1);
        body.put("parentId",12);
        
        
        given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .contentType("application/json")
                .when()
                .body(body)
                .put("dfmeas/{dfmeaId}/blocks/{blockId}", 1,100)
                .then()
                .statusCode(204);
        
        given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .when()
                .get("dfmeas/{dfmeaId}/blocks/{blockId}", 1, 100)
                .then()
                .body("id", equalTo(100))
                .body("name", equalTo(block.get("name")+" (2X)"))
                .statusCode(200);
    }
    
    @Test
    @Order(4)
    public void testDeleteEndPoint() throws Exception {

        given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .when()
                .delete("dfmeas/{dfmeaId}/blocks/{blockId}", 1, 100)
                .then()
                .statusCode(204);
        
        given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .when()
                .get("dfmeas/{dfmeaId}/blocks/{blockId}", 1,100)
                .then()
                .statusCode(404);
    }
    
    @Test
    @Order(5)
    public void testBlockDiagram() throws Exception {
        given()
                .header("Authorization", "Bearer " + TestUtil.mockToken())
                .when()
                .get("dfmeas/{dfmeaId}/blocks/diagram", 1)
                .then()
                .statusCode(200)
                .body("size()", is(1))
                .body("[0].id", equalTo(1))
                .body("[0].children.size", equalTo(2));
    }

}
