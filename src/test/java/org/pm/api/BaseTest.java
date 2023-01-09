package org.pm.api;


import org.pm.api.controllers.RegistrationController;
import org.pm.api.model.RegistrationRequest;
import org.testng.log4testng.Logger;


public class BaseTest {

  protected String formName = "REGISTRATIONBYPHONE";
  protected String password = "Qwerty123!";
  protected String defaultCurrency = "XTS";
  protected String selectedLanguage = "en";
  protected String isPlayerAgree = "true";
  public static final Logger logger = Logger.getLogger(BaseTest.class);
  RegistrationController registrationController = new RegistrationController();

  protected RegistrationRequest buildUserForRegistration(
      String formName, String phone, String email, String password,
      String defaultCurrency, String selectedLanguage, String isPlayerAgree
  ) {
    return RegistrationRequest.builder()
        .formName(formName)
        .phone(phone)
        .email(email)
        .password(password)
        .defaultCurrency(defaultCurrency)
        .selectedLanguage(selectedLanguage)
        .isPlayerAgree(isPlayerAgree)
        .build();
  }
}
