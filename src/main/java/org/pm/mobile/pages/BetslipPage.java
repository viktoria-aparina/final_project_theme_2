package org.pm.mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class BetslipPage extends BasePage {

    @AndroidFindBy(xpath = "(//*[contains(@resource-id, 'content')]/child::*//android.widget.TextView[@content-desc, 'quickBetPlaceButton']/parent::android.view.View)[4]")
    private MobileElement placeBetButton;

    public void clickPlaceBetButton() {
        waitForExpectedElement(placeBetButton).click();
    }
}
