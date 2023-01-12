package org.pm.api;

import org.pm.api.clients.LoginApiClient;
import org.pm.api.dto.LoginRequest;
import org.pm.api.dto.UserRegistrationRequest;
import org.pm.api.dto.response.RegistrationResponse;
import org.pm.api.providers.UserRegistrationProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;

public class LoginTest extends BaseTest {
  LoginApiClient loginApiClient = new LoginApiClient();

  @TmsLink("24")
  @Story("6-write-ui-autotests")
  @Description("API: Login as registered user")
  @Test(groups = {"Aparina API tests"})
  public void loginAsRegisteredUserTest() {
    UserRegistrationRequest newUser = new UserRegistrationProvider().getNewUser();
    RegistrationResponse actualResponseUser =
        registrationApiClient.postUser(newUser, HttpStatus.SC_OK);

    assertTokenIsNotNull(actualResponseUser);

    LoginRequest loginRequest = new LoginRequest(newUser.getPhone(), newUser.getPassword());
    Response actualResponse = loginApiClient.postLogin(loginRequest);
    assertStatusCode(actualResponse.then().extract().statusCode(), SC_OK);
  }

  @TmsLink("26")
  @Story("6-write-ui-autotests")
  @Description("API: Login as unregistered user")
  @Test(groups = {"Aparina API tests"})
  public void loginAsUnregisteredUserTest() {
    LoginRequest newLoginUser = new UserRegistrationProvider().getNewUserLogin();

    LoginRequest loginRequest =
        new LoginRequest(newLoginUser.getLogin(), newLoginUser.getPassword());
    Response actualResponse = loginApiClient.postLogin(loginRequest);
    assertStatusCode(actualResponse.then().extract().statusCode(), SC_BAD_REQUEST);
  }
}
