package api.clients;

import api.dto.UpdatePasswordRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UpdatePasswordApiClient extends BaseApiClients {

    public Response postUpdatePassword(UpdatePasswordRequest updatePasswordRequest, String token) {
        return updatePassword("/api/user/updatepassword", updatePasswordRequest, token);
    }

    private Response updatePassword(String uri, Object body, String token) {
        return given().spec(rqSpec)
                .header("Authorization", "Token " + token)
                .body(body)
                .log().ifValidationFails()
                .when()
                .post(uri)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();
    }
}