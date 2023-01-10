package org.pm.mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SighUpPage extends BasePage {

    @AndroidFindBy(accessibility = "Log in button")
    private MobileElement logInButton;

    public void clickLoginButton() {
        waitForExpectedElement(logInButton).click();
    }
}
