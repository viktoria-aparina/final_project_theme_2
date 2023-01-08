package org.pm.api;

import static org.hamcrest.Matchers.notNullValue;

import org.apache.commons.lang3.RandomStringUtils;
import org.pm.api.model.AccessToken;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

  @Test
  public void registrationWithValidDataTest() {

    String generatedString = RandomStringUtils.randomNumeric(3);
    var phone = "+38011065" + generatedString;
    var email = "test_" + generatedString + "@gmail.com";

    var user = buildUserForRegistration(
        formName, phone, email, password, defaultCurrency, selectedLanguage, isPlayerAgree);

    var actualResponse = registrationController.registrationNewUser(user);

    Assert.assertEquals(actualResponse.extract().statusCode(), 200);
    actualResponse.body("token", notNullValue());

    logger.info("User successfully registered with valid data. Registration token " + actualResponse
        .extract().as(AccessToken.class).getToken());
  }
}
