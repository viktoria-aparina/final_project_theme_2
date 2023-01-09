package org.pm.ui;

import org.aspectj.lang.annotation.After;
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

    @AfterMethod
    public void clearBetslipAndClose() {
        new HomePage().clearBetslip().close();
    }
}
