package org.pm.api.controllers;

import static io.restassured.RestAssured.given;

import io.restassured.response.ValidatableResponse;
import org.pm.api.model.LoginRequest;


public class LoginController extends BaseController {

  String postLoginUrl = "/api/v2/routinglogin";

  public ValidatableResponse login(LoginRequest user) {
    return given()
        .spec(requestSpec)
        .body(user)
        .when()
        .post(postLoginUrl)
        .then();
  }
}
