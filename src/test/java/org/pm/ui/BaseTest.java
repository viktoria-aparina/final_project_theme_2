package org.pm.ui;

import org.pm.ui.pages.HomePage;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void loginWithTestCredentialsId() {
        HomePage.open()
                .clickOnLoginButton()
                .chooseLoginTypeById()
                .loginWithTestCredentialId();
    }
}
