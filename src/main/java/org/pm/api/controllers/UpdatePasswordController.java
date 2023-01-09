package org.pm.api.controllers;

import static io.restassured.RestAssured.given;

import io.restassured.response.ValidatableResponse;
import org.pm.api.model.UpdatePasswordRequest;

public class UpdatePasswordController extends BaseController {

  String postUpdatePasswordUrl = "/api/user/updatepassword";

  public ValidatableResponse updatePassword(UpdatePasswordRequest user, String token) {
    return given()
        .spec(requestSpec)
        .headers("Authorization", "Token " + token)
        .body(user)
        .when()
        .post(postUpdatePasswordUrl)
        .then();
  }
}
