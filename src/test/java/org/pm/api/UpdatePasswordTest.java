package org.pm.api;

import static org.hamcrest.Matchers.notNullValue;

import lombok.var;
import org.apache.commons.lang3.RandomStringUtils;

import org.pm.api.controllers.UpdatePasswordController;
import org.pm.api.model.AccessToken;
import org.pm.api.model.UpdatePasswordRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdatePasswordTest extends BaseTest {

    UpdatePasswordController updatePasswordController = new UpdatePasswordController();

    @Test
    public void updatePasswordTest() {
        String generatedString = RandomStringUtils.randomNumeric(3);
        var phone = "+38011065" + generatedString;
        var email = "test_" + generatedString + "@gmail.com";
        var newPassword = "Qwerty!" + generatedString;

        var userForRegistration = buildUserForRegistration(
                formName, phone, email, password, defaultCurrency, selectedLanguage, isPlayerAgree);

        var actualRegistrationResponse =
                registrationController.registrationNewUser(userForRegistration);

        Assert.assertEquals(actualRegistrationResponse.extract().statusCode(), 200);
        actualRegistrationResponse.body("token", notNullValue());

        logger.info("The user registered with password " + password);

        var token = actualRegistrationResponse.extract().as(AccessToken.class).getToken();

        var requestUpdate = buildUserForUpdate(password, newPassword);

        var actualUpdateResponse = updatePasswordController.updatePassword(requestUpdate, token);

        Assert.assertEquals(actualUpdateResponse.extract().statusCode(), 200);
        logger.info("Password successfully changed to " + newPassword);
    }

    private UpdatePasswordRequest buildUserForUpdate(String oldPassword, String newPassword) {
        return UpdatePasswordRequest.builder()
                .oldPassword(oldPassword)
                .newPassword(newPassword)
                .build();
    }
}