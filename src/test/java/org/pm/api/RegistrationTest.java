package org.pm.api;

import api.dto.UserRegistrationRequest;
import api.dto.response.RegistrationResponse;
import api.providers.UserRegistrationProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.apache.http.HttpStatus;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class RegistrationTest extends BaseTest {

    @TmsLink("23")
    @Story("6-write-ui-autotests")
    @Description("API: Registration with all required fields and valid data")
    @Test(groups = { "Volosiuk API tests"})
    public void registrationTest() {
        UserRegistrationRequest newUser = new UserRegistrationProvider().getNewUser();

        RegistrationResponse actualResponse = registrationApiClient.postUser(newUser, HttpStatus.SC_OK);

        assertThat(actualResponse.getToken()).as("Token in response is null").isNotNull();
    }
}
