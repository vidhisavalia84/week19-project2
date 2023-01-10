package com.gorest.crudtests;

import com.gorest.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class TagsTest extends TestBase {


    @Title("Provide incorrect  HTTP method(ex: used put insted of get) to access resources,and responce should be 404")
    @WithTag("gorestfeature:NEGATIVE")
    @Test
    public void invalidmethod() {
        SerenityRest.given()
                .when()
                .put("/users")
                .then()
                .statusCode(404)
                .log().all();
    }

    @WithTags({
            @WithTag("gorestfeature:POSITIVE"),
            @WithTag("gorestfeature:SMOKE")
    })
    @Title("Provided correct HTTP method to access resource and validate response code 200")
    @Test
    public void validMethod() {
        SerenityRest.given().log().all()
                .when()
                .get("/users")
                .then()
                .statusCode(200);
    }

    @WithTags({
            @WithTag("gorestfeature:NEGATIVE"),
            @WithTag("gorestfeature:SANITY")
    })
    @Title("This test will provide error code 400 when user tries to acces invalid resources")
    @Test
    public void incorrectResources() {
SerenityRest.given().log().all()
        .when()
        .get("/users123")
        .then()
        .statusCode(404);

    }
}
