package org.pm.api;

import api.clients.UpdatePasswordApiClient;
import api.dto.UpdatePasswordRequest;
import api.dto.UserRegistrationRequest;
import api.dto.response.RegistrationResponse;
import api.providers.UserRegistrationProvider;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class UpdatePasswordTest extends BaseTest {
  UpdatePasswordApiClient updatePasswordApiClient = new UpdatePasswordApiClient();
  @Test
  public void updatePasswordTest() {
    UserRegistrationRequest newUser = new UserRegistrationProvider().getNewUser();
    RegistrationResponse registrationResponse = registrationApiClient.postUser(newUser, SC_OK);

    String generatedString = RandomStringUtils.randomNumeric(3);
    String newPassword = "Qwerty!" + generatedString;

    UpdatePasswordRequest updatePasswordRequest =
        new UpdatePasswordRequest(newUser.getPassword(), newPassword);
    Response actualResponse = updatePasswordApiClient
        .postUpdatePassword(updatePasswordRequest, registrationResponse.getToken());
    assertStatusCode(actualResponse.then().extract().statusCode(), SC_OK);
  }
}