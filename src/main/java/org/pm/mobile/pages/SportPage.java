package org.pm.mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SportPage extends BasePage {

    @AndroidFindBy(accessibility = "sport-tab")
    private MobileElement sportTab;

    @AndroidFindBy(xpath = "(//*[contains(@content-desc, 'eventCard')])[1]")
    private MobileElement firstGame;

    @AndroidFindBy(xpath = "(//*[contains(@content-desc, 'eventCard')])[1]/child::*//android.widget.LinearLayout[contains(@content-desc, 'outcome_1')]")
    private MobileElement betP1FirstGame;

    public void clickSportTabDisplayed() { waitForExpectedElement(sportTab).click();
    }

    public boolean isSportTabOpened() {
        return waitForExpectedElement(firstGame).isDisplayed();
    }

    public void clickP1BetFirstGame() {
        waitForExpectedElement(betP1FirstGame).click();
    }
}
