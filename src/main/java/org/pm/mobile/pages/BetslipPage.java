package org.pm.mobile.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.xpath;

@Log4j2
public class BetslipPage extends BasePage {

    @AndroidFindBy(accessibility = "quickBetPlaceButton")
    private MobileElement placeBetButton;

    @AndroidFindBy(xpath = "//*[contains(@content-desc, 'hotBet')][1]")
    private MobileElement smallestProposedAmount;

    @AndroidFindBy(accessibility = "tvOddCoefficient")
    private ArrayList<MobileElement> coefficients;

    @AndroidFindBy(accessibility = "placeBetSuccessText")
    private MobileElement successAlert;

    @AndroidFindBy(accessibility = "doneButtonQuickBet")
    private MobileElement doneButton;

    public void clickPlaceBetButton() {
        waitForExpectedElement(placeBetButton).click();
        log.info("Click on \"Select login type\" was successful");
    }

    public BetslipPage clickSmallestProposedAmount() {
        waitForExpectedElement(smallestProposedAmount).click();
        log.info("Click on \"Select login type\" was successful");
        return this;
    }

    public ArrayList<Double> getCoefficientsFromBetslip() {
        ArrayList<Double> coefficientsFromBetslip = new ArrayList<>();
        for (MobileElement coefficient : coefficients) {
            coefficientsFromBetslip.add(Double.valueOf(coefficient.getText()));
        }
        return coefficientsFromBetslip;
    }

    public String getSuccessAlert() {
        return successAlert.getText();
    }

    public BetslipPage clickDoneButton() {
        waitForExpectedElement(doneButton).click();
        log.info("Click on \"Done\" button was successful");
        return this;
    }
}
