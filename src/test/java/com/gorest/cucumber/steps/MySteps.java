package com.gorest.cucumber.steps;

import com.gorest.steps.UsersSteps;
import com.gorest.utils.PropertyReader;
import com.gorest.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class MySteps {
  // static String name;
//    static String gender;
//    static String email;
//    static String status;

    static int userId;
    static ValidatableResponse response;


    static String token = PropertyReader.getInstance().getProperty("token");
    @Steps
    UsersSteps usersSteps;

    @When("^User send post request with name \"([^\"]*)\" gender \"([^\"]*)\" email \"([^\"]*)\" status \"([^\"]*)\"$")
    public void userSendPostRequestWithNameGenderEmailStatus(String name, String gender, String email, String status) {
        email = TestUtils.getRandomValue() + email;
        name = name + TestUtils.getRandomValue();
        response = usersSteps.createUser(name, gender, email, status, token);
    }

    @Then("^User should be  created and validate response code 200$")
    public void userShouldBeCreatedAndValidateResponseCode() {
        response.statusCode(201);
        userId = (int) response.extract().body().path("id");
        System.out.println(userId);

    }


    @When("^User send get request$")
    public void userSendGetRequest() {
        response = usersSteps.getUserByID(userId, token);
    }

    @Then("^validate response code 201$")
    public void validateResponseCode() {
        response.statusCode(200);
    }

    //    @Then("^validate response codeers (\\d+)$")
//    public void validateResponseCode() {
//       // response.statusCode(201);
//    }
    @When("^User send update request with name \"([^\"]*)\" gender \"([^\"]*)\"  email \"([^\"]*)\" status \"([^\"]*)\"$")
    public void userSendUpdateRequestWithNameGenderEmailStatus(String name, String gender, String email, String status) {
        email = TestUtils.getRandomValue() + email;
        name = name + "_update";
        response = usersSteps.updateUser(name, gender, userId, email, status, token);
    }


    @Then("^User should be updated and validate status code 200$")
    public void userShouldBeUpdatedAndValidateStatusCode() {
        response.statusCode(200);
    }


    @When("^User send delete request$")
    public void userSendDeleteRequest() {
        response = usersSteps.deleteUser(userId, token);
    }


    @Then("^User should be deleted and validate response code 204$")
    public void userShouldBeDeletedAndValidateResponseCode() {
        response.statusCode(204);
    }
}
