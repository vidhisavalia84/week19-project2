package com.gorest.steps;

import com.gorest.constants.Endpoints;
import com.gorest.model.PostsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class PostsSteps {

    @Step("Create post for userId: {0}, title: {1} and body: {2}")
    public ValidatableResponse createPost(int userId, String title, String body, String token) {
        PostsPojo postsPojo = PostsPojo.getRequestBody(userId, title, body);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("userId", userId)
                .body(postsPojo)
                .when()
                .post(Endpoints.CREATE_POST)
                .then();
    }

    @Step("Get post by postId: {0}")
    public ValidatableResponse getPostByID(int postID, String token) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("postID", postID)
                .when()
                .get(Endpoints.GET_POST_BY_ID)
                .then();
    }

    @Step("Update post by postId: {0}")
    public ValidatableResponse updatePost(int postId, int userId, String title, String body, String token) {
        PostsPojo postsPojo = PostsPojo.getRequestBody(userId, title, body);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(postsPojo)
                .pathParam("postID", postId)
                .when()
                .patch(Endpoints.UPDATE_POST_BY_ID)
                .then();
    }

    @Step("Delete post by postId: {0}")
    public ValidatableResponse deletePosts(int userID, String token) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("userID", userID)
                .when()
                .delete(Endpoints.DELETE_POST_BY_ID)
                .then();
    }
}
