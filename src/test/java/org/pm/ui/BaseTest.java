package org.pm.ui;

import com.codeborne.selenide.Selenide;
import org.pm.ui.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void loginWithTestCredentialsId() {
        HomePage.open()
                .clickOnLoginButton()
                .chooseLoginTypeById()
                .loginWithTestCredentialId();
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
