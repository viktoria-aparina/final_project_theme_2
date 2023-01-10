package org.pm.api.controllers;

import static io.restassured.RestAssured.given;

import io.restassured.response.ValidatableResponse;
import org.pm.api.model.RegistrationRequest;

public class RegistrationController extends BaseController {

  String postRegisterUrl = "/api/v3/registration/byform";

  public ValidatableResponse registrationNewUser(RegistrationRequest user) {
    return given()
        .spec(requestSpec)
        .body(user)
        .when()
        .post(postRegisterUrl)
        .then();
  }
}
