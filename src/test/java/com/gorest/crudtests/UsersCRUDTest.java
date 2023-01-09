package com.gorest.crudtests;

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
public class UsersCRUDTest extends TestBase {

    static String token = PropertyReader.getInstance().getProperty("token");
    static String name = "Prime" + TestUtils.getRandomValue();
    static String gender = "male";
    static String email = name + "@email.com";
    static String status = "active";
    static int userID;
    @Steps
    UsersSteps usersSteps;

    @Title("Create a new User")
    @Test
    public void test001() {
        ValidatableResponse response = usersSteps.createUser(name, gender, email, status, token);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }

    @Title("Verify that user added successfully")
    @Test
    public void test002() {
        ValidatableResponse response = usersSteps.getUserByID(userID, token);
        response.statusCode(200).body("id", equalTo(userID),
                "name", equalTo(name), "email", equalTo(email),
                "gender", equalTo(gender), "status", equalTo(status));
    }

    @Title("Update user details")
    @Test
    public void test003() {
        name = name + "_updated";
        email = name + "@email.com";
        ValidatableResponse response = usersSteps.updateUser(name, gender, userID, email, status, token).statusCode(200).log().all();
        response.statusCode(200).body("id", equalTo(userID),
                "name", equalTo(name), "email", equalTo(email),
                "gender", equalTo(gender), "status", equalTo(status));
    }

    @Title("Delete the user")
    @Test
    public void test004() {
        usersSteps.deleteUser(userID, token).statusCode(204);
        usersSteps.getUserByID(userID, token).statusCode(404);
    }
}
