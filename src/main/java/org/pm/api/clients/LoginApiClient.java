package org.pm.api.clients;

import org.pm.api.dto.LoginRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginApiClient extends BaseApiClients {

  public Response postLogin(LoginRequest loginRequest) {
    return login("/org/pm/api/v2/routinglogin", loginRequest);
  }

  private Response login(String uri, Object body) {
    return given().spec(rqSpec)
        .header("User-Agent", "ParimatchTechAcademy/89870edc1aaea008bd3a519c")
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