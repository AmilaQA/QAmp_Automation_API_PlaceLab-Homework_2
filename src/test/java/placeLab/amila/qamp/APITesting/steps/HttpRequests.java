package placeLab.amila.qamp.APITesting.steps;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static placeLab.amila.qamp.APITesting.utilities.GlobalValues.HOST;

public class HttpRequests {
    public static <T> Response sendPostRequest(final String endpoint, final T requestBody) {
        return given().contentType("application/json")
                .body(requestBody)
                .baseUri(HOST)
                .post(endpoint);
    }
}
