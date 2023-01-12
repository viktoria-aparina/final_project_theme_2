package org.pm.api;

import api.dto.UserRegistrationRequest;
import api.dto.response.RegistrationResponse;
import api.providers.UserRegistrationProvider;
import org.apache.http.HttpStatus;

import org.testng.annotations.Test;


public class RegistrationTest extends BaseTest {
  @Test
  public void registrationTest() {
    UserRegistrationRequest newUser = new UserRegistrationProvider().getNewUser();

    RegistrationResponse actualResponse = registrationApiClient.postUser(newUser, HttpStatus.SC_OK);

    assertTokenIsNotNull(actualResponse);
  }
}
