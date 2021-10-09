package StepDefs;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utilities.BaseUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CityBikesStepDefs extends BaseUtils {

    RequestSpecification req;
    Response response;
    ValidatableResponse lastRes;

    @Given("As a user I set api endpoint params")
    public void as_a_user_i_set_api_endpoint_params() throws IOException {
        req = given().spec(requestSpecification());

    }

    @When("get http method is sent")
    public void get_http_method_is_sent() throws IOException {
        response = req.when().get(getGlobalValue("base_uri"));
        // System.out.println("response: " + response.prettyPrint());

    }

    @Then("status code is {string}")
    public void status_code_is(String statusCode) {
        lastRes = response.then().spec(responseSpecification(statusCode));
        String   expectedValue= String.valueOf(lastRes.extract().statusCode());
        Assert.assertEquals(statusCode,expectedValue);
      }

    @Then("Assert the responses includes the following {string} and {string}")
    public void assert_the_responses_includes_the_following_and(String key, String value) {
        int count = 0;

        while (lastRes.extract().path(key) == value) {
            Assert.assertEquals(response.then().extract().path(key), value);
            count++;
        }
    }

}
