package api.providers;

import api.dto.UserRegistrationRequest;
import org.apache.commons.lang3.RandomStringUtils;

public class UserRegistrationProvider {
    public UserRegistrationRequest getNewUser() {

        String generatedString = RandomStringUtils.randomNumeric(3);

        return UserRegistrationRequest.builder()
                .formName("REGISTRATIONBYPHONE")
                .phone("+38011065" + generatedString)
                .email("test_" + generatedString + "@gmail.com")
                .password("Qwerty123!")
                .defaultCurrency("XTS")
                .selectedLanguage("en")
                .isPlayerAgree("true").build();
    }
}
