package org.pm.api;

import api.clients.LoginApiClient;
import api.dto.LoginRequest;
import api.dto.UserRegistrationRequest;
import api.providers.UserRegistrationProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    LoginApiClient loginApiClient = new LoginApiClient();

    @TmsLink("24")
    @Story("6-write-ui-autotests")
    @Description("API: Login as registered user")
    @Test(groups = { "Aparina API tests" })
    public void loginTest() {
        UserRegistrationRequest newUser = new UserRegistrationProvider().getNewUser();
        registrationApiClient.postUser(newUser, HttpStatus.SC_OK);

        LoginRequest loginRequest = new LoginRequest(newUser.getPhone(), newUser.getPassword());
        Response actualResponse = loginApiClient.postLogin(loginRequest);
        assertEquals(actualResponse.then().extract().statusCode(), HttpStatus.SC_OK);
    }
}