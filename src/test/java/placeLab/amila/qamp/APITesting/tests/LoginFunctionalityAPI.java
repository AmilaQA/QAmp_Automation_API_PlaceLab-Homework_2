package placeLab.amila.qamp.APITesting.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import placeLab.amila.qamp.APITesting.steps.HttpRequests;
import placeLab.amila.qamp.APITesting.utilities.AuthenticationRequest;
import placeLab.amila.qamp.APITesting.utilities.GlobalValues;

import java.util.logging.Logger;

public class LoginFunctionalityAPI {
    @BeforeTest(alwaysRun = true, groups = {"Positive", "Negative"})
    public void setup() {
        // Set the base URL for the API
        RestAssured.baseURI = "https://demo.placelab.com/api/v2/";
    }

    private static final Logger LOGGER = Logger.getLogger("Login functionality API tests");

    @Test(priority = 1, description = "Validate user is able to successfully login with valid credentials.",
            testName = "TC 01 - Successful Login - valid credentials.", groups = {"validCredentials", "Positive"})
    @Parameters({"email", "password"})

    public void testLoginAPI_validCredentials(String email, String password) {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(email, password);

        Response response = HttpRequests.sendPostRequest("/api/v2/sessions", authenticationRequest.requestBody.toString());
        LOGGER.info("Submit authentication POST request");

        authenticationRequest.validateResponseStatusCode(response.statusCode(), 201);
        authenticationRequest.validateResponseHeaders(response.contentType());

        // Verify the response
        int statusCode = response.getStatusCode();
        Assert.assertEquals(201, statusCode);

        // Check for the presence of a token in the response body
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("api_token"));
    }

    @Test(priority = 2, description = "Validate user is not able to login with invalid email.",
            testName = "TC 02 Invalid email - error message response", groups = {"invalidCredentials", "Negative"})
    @Parameters({"invalidEmail", "password"})
    public void testLoginAPI_invalidEmail(String invalidEmail, String validPassword) {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(invalidEmail, validPassword);

        Response response = HttpRequests.sendPostRequest("/api/v2/sessions", authenticationRequest.requestBody.toString());
        LOGGER.info("Submit authentication POST request");

        authenticationRequest.validateResponseStatusCode(response.statusCode(), 401);
        authenticationRequest.validateResponseHeaders(response.contentType());

        // Verify the response
        int statusCode = response.getStatusCode();
        Assert.assertEquals(401, statusCode);

    }

    @Test(priority = 2, description = "Validate user is not able to login with invalid password.",
            testName = "TC 03 Invalid password - error message response", groups = {"invalidCredentials", "Negative"})
    @Parameters({"email", "invalidPassword"})

    public void testLoginAPI_invalidPassword(String validEmail, String invalidPassword) {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(validEmail, invalidPassword);

        Response response = HttpRequests.sendPostRequest("/api/v2/sessions", authenticationRequest.requestBody.toString());
        LOGGER.info("Submit authentication POST request");

        authenticationRequest.validateResponseStatusCode(response.statusCode(), 401);
        authenticationRequest.validateResponseHeaders(response.contentType());

        // Verify the response
        int statusCode = response.getStatusCode();
        Assert.assertEquals(401, statusCode);

    }

    @Test(priority = 3, description = "Validate user is not able to login with leaving the credentials fields empty.",
            testName = "TC 04 Empty fields - error message.", groups = {"emptyFields", "Negative"})

    public void testEmptyFields() {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(null, null);
        Response response = HttpRequests.sendPostRequest("/api/v2/sessions", authenticationRequest.requestBody.toString());
        LOGGER.info("Submit authentication POST request");

        authenticationRequest.validateResponseStatusCode(response.statusCode(), 400);
        authenticationRequest.validateResponseHeaders(response.contentType());


        // Verify the response
        int statusCode = response.getStatusCode();
        Assert.assertEquals(400, statusCode);

    }

}


