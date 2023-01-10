package org.pm.mobile;

import org.assertj.core.api.Assertions;
import org.pm.mobile.pages.LoginPage;
import org.pm.mobile.pages.SighUpPage;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.sql.DriverManager;

import static java.sql.DriverManager.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.pm.mobile.driver.DriverManager.*;

public class BaseTest {

    @BeforeTest(alwaysRun = true)
    @Parameters({"UDID", "WDA", "DeviceName", "PlatformVersion"})
    public void setupSession(
            @Optional String udid,
            @Optional String wda,
            @Optional String deviceName,
            @Optional String platformVersion) {
        try {
            createDriver(udid, wda, deviceName, platformVersion);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUp() {
        new SighUpPage().clickLoginButton();

        assertThat(new LoginPage().isLoginPageOpened()).as("Login Page wasn't opened")
                .isTrue();

        new LoginPage().clickSelectLoginTypeButton()
                .clickIdLoginTypeButton()
                .putIdInLoginInput()
                .putPasswordInPasswordInput()
                .clickLogInButton();
    }

    @AfterSuite(alwaysRun = true)
    @Parameters("UDID")
    public void closeSession(@Optional String udid) {
        terminateDriver();
        terminateAppium();
    }

    @AfterMethod(alwaysRun = true)
    public void resetApp() {
        getDriver().resetApp();
    }
}
