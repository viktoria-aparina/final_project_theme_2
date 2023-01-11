package org.pm.api;

import api.clients.LoginApiClient;
import api.dto.LoginRequest;
import api.dto.UserRegistrationRequest;
import api.providers.UserRegistrationProvider;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    LoginApiClient loginApiClient = new LoginApiClient();

    @Test
    public void loginTest() {
        UserRegistrationRequest newUser = new UserRegistrationProvider().getNewUser();
        registrationApiClient.postUser(newUser, HttpStatus.SC_OK);

        LoginRequest loginRequest = new LoginRequest(newUser.getPhone(), newUser.getPassword());
        Response actualResponse = loginApiClient.postLogin(loginRequest);
        assertEquals(actualResponse.then().extract().statusCode(), HttpStatus.SC_OK);
    }
}