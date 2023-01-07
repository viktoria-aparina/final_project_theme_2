package org.pm.ui.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class BetslipPage extends BasePage {
    @Override
    public boolean isPageOpened() {
        return false;
    }

    static SelenideElement valueBetLocator = $(xpath("//input[@data-id='betslip-stake-input']"));

    public BetslipPage enterValueBet(CharSequence valueBet) {
        valueBetLocator.clear();
        valueBetLocator.sendKeys(valueBet);
        return this;
    }

    public static double getValueBet() {
        return Double.parseDouble(valueBetLocator.getValue());
    }

    public static double getPossibleWinningAmount() { //calc by site
        return Double.parseDouble($(xpath("//span[@data-id='betslip-place-bet-button-amount']")).getText());
    }

    public static double calculatePossibleWinningAmount(double valueBet, ArrayList<Double> coefficients) { //calc by me
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
}
