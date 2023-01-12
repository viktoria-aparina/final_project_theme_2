package org.pm.api;

import org.pm.api.clients.UpdatePasswordApiClient;
import org.pm.api.dto.UpdatePasswordRequest;
import org.pm.api.dto.UserRegistrationRequest;
import org.pm.api.dto.response.RegistrationResponse;
import org.pm.api.providers.UserRegistrationProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class UpdatePasswordTest extends BaseTest {
  UpdatePasswordApiClient updatePasswordApiClient = new UpdatePasswordApiClient();

  @TmsLink("25")
  @Story("6-write-ui-autotests")
  @Description("API: Update password as registered user")
  @Test(groups = { "Riabtseva UI tests" })
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