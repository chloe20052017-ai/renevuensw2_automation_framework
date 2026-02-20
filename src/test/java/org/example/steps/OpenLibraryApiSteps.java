package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class OpenLibraryApiSteps {
    private static final String BASE_URI = "https://openlibrary.org";
    private static final String AUTHOR_PATH = "/authors/OL1A.json";
    private Response response;

    @Given("the OpenLibrary author endpoint is available")
    public void theOpenLibraryAuthorEndpointIsAvailable() {
        response = given()
                .baseUri(BASE_URI)
                .when()
                .get(AUTHOR_PATH)
                .then()
                .extract()
                .response();
    }

    @When("I send a GET request to the author endpoint")
    public void iSendAGetRequestToTheAuthorEndpoint() {
        response = given()
                .baseUri(BASE_URI)
                .when()
                .get(AUTHOR_PATH)
                .then()
                .extract()
                .response();
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        assertThat(response.statusCode(), equalTo(statusCode));
    }

    @Then("the response content type should be JSON")
    public void theResponseContentTypeShouldBeJson() {
        String contentType = response.contentType();
        assertThat(contentType, containsString("application/json"));
    }

    @Then("the {string} field should be {string}")
    public void theFieldShouldBe(String fieldName, String expectedValue) {
        assertThat(response.jsonPath().getString(fieldName), equalTo(expectedValue));
    }

    @Then("the {string} list should contain {string}")
    public void theListShouldContain(String fieldName, String expectedValue) {
        assertThat(response.jsonPath().getList(fieldName), hasItem(expectedValue));
    }
}
