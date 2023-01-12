package org.pm.api;

import api.clients.RegistrationApiClient;
import api.dto.response.RegistrationResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {
  RegistrationApiClient registrationApiClient = new RegistrationApiClient();

  public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
    assertThat(actualStatusCode).as("Status code doesn't match with expected")
        .isEqualTo(expectedStatusCode);
  }

  public void assertTokenIsNotNull(RegistrationResponse actualResponse) {
    assertThat(actualResponse.getToken()).as("Token in response is null").isNotNull();
  }
}
