package com.gorest.steps;

import com.gorest.constants.Endpoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.vavr.collection.HashMap;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.RestRequests.given;

public class UsersSteps {

    @Step("Creating user with name: {0}, gender: {1}, status: {2}, email: {3}")
    public ValidatableResponse createUser(String name, String gender, String email, String status, String token) {
        UserPojo userPojo = UserPojo.getRequestBody(name, gender, email, status);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(userPojo)
                .when()
                .post(Endpoints.CREATE_USER)
                .then();
    }

    @Step("Update user details with name: {0}, gender: {1}, userid: {2}, email: {3}, status: {4}")
    public ValidatableResponse updateUser(String name, String gender, int userID, String email, String status, String token) {
        UserPojo userPojo = UserPojo.getRequestBody(name, gender, email, status);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("userID", userID)
                .body(userPojo)
                .when()
                .patch(Endpoints.UPDATE_USER_BY_ID)
                .then();
    }

    @Step("Get user info by userId: {0}")
    public ValidatableResponse getUserByID(int userID, String token) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("userID", userID)
                .when()
                .get(Endpoints.GET_USER_BY_ID)
                .then();
    }
    @Step("Get user info by userId: {0}")

    public HashMap<String,Object> getUserByuserID1(int userID, String token) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("userID", userID)
                .when()
                .get(Endpoints.GET_USER_BY_ID)
                .then().statusCode(201).extract().body().path("");
    }
    @Step("Delete User with userId: {0}")
    public ValidatableResponse deleteUser(int userID, String token) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("userID", userID)
                .when()
                .delete(Endpoints.DELETE_USER_BY_ID)
                .then();
    }
}
