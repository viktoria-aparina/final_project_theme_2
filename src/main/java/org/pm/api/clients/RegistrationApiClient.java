package org.pm.api.clients;

import org.pm.api.dto.UserRegistrationRequest;
import org.pm.api.dto.response.RegistrationResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RegistrationApiClient extends BaseApiClients {

  public RegistrationResponse postUser(UserRegistrationRequest newUser, int httpStatusCode) {
    Response response = postRegistration("/org/pm/api/v3/registration/byform", newUser);
    return response.then()
        .statusCode(httpStatusCode)
        .extract()
        .body()
        .as(RegistrationResponse.class);
  }

  private Response postRegistration(String uri, Object body) {
    return given().spec(rqSpec)
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
