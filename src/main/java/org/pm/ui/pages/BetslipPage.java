package org.pm.ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

@Log4j2
public class BetslipPage extends BasePage {
    @Override
    public boolean isPageOpened() {
        try {
            $(xpath("//div[@data-id='betslip-container']")).shouldBe(visible);
            log.info("The BetslipPage was opened successfully");
            return true;
        } catch (TimeoutException exception) {
            log.error("The BetslipPage wasn't open because of error {}", exception.getCause());
            return false;
        }
    }

    static SelenideElement valueBetLocator = $(xpath("//input[@data-id='betslip-stake-input']"));

    public BetslipPage enterValueBet(Double valueBet) {
        valueBetLocator.clear();
        valueBetLocator.setValue(String.valueOf(valueBet)).pressTab();
        return this;
    }

    public BetslipPage chooseProposedAmountForBet() {
        $(xpath("//div[@data-id='keyboard-betslip-set-stake-button'][1]")).should(enabled).click();
        return this;
    }

    public BetslipPage clickMyBetslipTab() {
        $(xpath("//div[@data-id='betsHistory']")).should(enabled).click();
        return this;
    }

    public String getAlert() {
        return $(xpath("//div[@data-id='betslip-success-betslip-content']/span")).getText();
    }

    public static double getValueBet() {
        return Double.parseDouble(valueBetLocator.getValue());
    }

    public static double getPossibleWinningAmountBySite() {
        return Double.parseDouble($(xpath("//span[@data-id='betslip-place-bet-button-amount']")).getText());
    }

    public static double calculatePossibleWinningAmount(double valueBet, ArrayList<Double> coefficients) {
        double result = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            result = valueBet * coefficients.get(i);
        }
        return result;
    }

    public LoginPage clickLoginOnBetslip() {
        $(xpath("//button[@data-id='betslip-place-bet-button']")).shouldBe(enabled).click();
        return new LoginPage();
    }

    public SportPage clickPlaceBetButtonOnBetslip() {
        $(xpath("//button[@data-id='betslip-place-bet-button']")).shouldBe(enabled).click();
        return new SportPage();
    }
}
