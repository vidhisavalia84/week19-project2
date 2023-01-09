package com.gorest.crudtests;

import com.gorest.steps.PostsSteps;
import com.gorest.steps.UsersSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.PropertyReader;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class PostsCRUDTest extends TestBase {

    static String token = PropertyReader.getInstance().getProperty("token");
    static String title = "Acidus versus terebro claustrum modi tyrannus asdfg";
    static String body = "Curso combibo super. Voluptas demum speciosus. Enim cado alias. Candidus antea animus. Alter facilis sub. Autem creber amitto. Ambitus comptus sunt. Modi delinquo antiquus. Auxilium angustus volubilis. Aufero urbs balbus. Deorsum ultra vigor. Crux sponte synagoga. Consequatur triumphus tamisium. Vulgo avaritia cohaero. Capillus minus cattus. Aggredior ut iusto. Officiis torrens autem.";
    static int postID;
    static int userId;
    @Steps
    PostsSteps postsSteps;
    @Steps
    UsersSteps usersSteps;

    @Title("Create a new post for the user")
    @Test
    public void test001() {
        String name = "Prime" + TestUtils.getRandomValue();
        String gender = "male";
        String email = name + "@email.com";
        String status = "active";
        userId = usersSteps.createUser(name, gender, email, status, token).statusCode(201).extract().path("id");
        ValidatableResponse response = postsSteps.createPost(userId, title, body, token);
        response.log().all().statusCode(201);
        response.body("user_id", equalTo(userId));
        postID = response.log().all().extract().path("id");
        System.out.println(postID);
    }

    @Title("Verify that post was added for the user")
    @Test
    public void test002() {
        ValidatableResponse response = postsSteps.getPostByID(postID, token);
        response.log().all().statusCode(200)
                .body("id", equalTo(postID), "user_id", equalTo(userId),
                        "title", equalTo(title), "body", equalTo(body));
    }

    @Title("Update the post title")
    @Test
    public void test003() {
        title = title + "_updated";
        ValidatableResponse response = postsSteps.updatePost(postID, userId, title, body, token);
        response.log().all().statusCode(200)
                .body("id", equalTo(postID), "user_id", equalTo(userId),
                        "title", equalTo(title), "body", equalTo(body));
    }

    @Title("Delete the post")
    @Test
    public void test004() {
        postsSteps.deletePosts(postID, token).statusCode(204);
        postsSteps.getPostByID(postID, token).statusCode(404);
    }
}
