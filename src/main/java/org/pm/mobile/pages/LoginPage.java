package org.pm.mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "close_button")
    private MobileElement closeButton;

    @AndroidFindBy(id = "com.parimatch:id/tvButtonTitle")
    private MobileElement logInButton;

    public void clickCloseButtonDisplayed() {
        waitForExpectedElement(closeButton).click();
    }

    public boolean isLoginPageOpened() {
        return waitForExpectedElement(logInButton).isDisplayed();
    }
}
