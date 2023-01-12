package org.pm.api;

import org.pm.api.dto.UserRegistrationRequest;
import org.pm.api.dto.response.RegistrationResponse;
import org.pm.api.providers.UserRegistrationProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.apache.http.HttpStatus;

import org.testng.annotations.Test;


public class RegistrationTest extends BaseTest {
  @TmsLink("23")
  @Story("6-write-ui-autotests")
  @Description("API: Registration with all required fields and valid data")
  @Test(groups = { "Volosiuk API tests"})
  public void registrationTest() {
    UserRegistrationRequest newUser = new UserRegistrationProvider().getNewUser();

    RegistrationResponse actualResponse = registrationApiClient.postUser(newUser, HttpStatus.SC_OK);

    assertTokenIsNotNull(actualResponse);
  }
}
