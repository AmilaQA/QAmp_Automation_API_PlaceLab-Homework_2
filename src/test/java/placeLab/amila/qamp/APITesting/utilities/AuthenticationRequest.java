package placeLab.amila.qamp.APITesting.utilities;

import org.json.JSONObject;
import org.testng.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AuthenticationRequest {

    private static String email;
    private static String password;
    public JSONObject requestBody;

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
        createMainBody();
    }

    private void createMainBody() {
        JSONObject data = new JSONObject();
        data.put("email", email);
        data.put("password", password);
        requestBody = data;
    }

    public void validateResponseHeaders(final String contentType) {
        assertThat(contentType, equalTo(GlobalValues.APPLICATION_JSON));
        Assert.assertEquals(GlobalValues.APPLICATION_JSON, contentType, "Wrong JSON response content-type header");
    }

    public void validateResponseStatusCode(final int statusCode, int expectedStatusCode) {

        assertThat(statusCode, equalTo(expectedStatusCode));
    }
}
