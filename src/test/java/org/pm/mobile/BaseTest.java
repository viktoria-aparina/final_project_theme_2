package org.pm.mobile;

import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.sql.DriverManager;

import static java.sql.DriverManager.*;
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

    @AfterSuite(alwaysRun = true)
    @Parameters("UDID")
    public void closeSession(@Optional String udid) {
        terminateDriver();
        terminateAppium();
//        DriverManager.terminateEmulator(udid);
    }

    @AfterMethod(alwaysRun = true)
    public void resetApp() {
        getDriver().resetApp();
    }
}
