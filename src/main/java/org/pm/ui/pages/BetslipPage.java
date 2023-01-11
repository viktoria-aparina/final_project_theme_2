package org.pm.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
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

    public BetslipPage enterValueBet(String valueBet) {
        valueBetLocator.clear();
        valueBetLocator.setValue(valueBet).pressTab();
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


    public String getSuccessAlert() {
        return $(xpath("//button[@data-id='betslip-success-betslip-button']//div[contains(@class, 'SuccessBetslip__')]")).getText();
    }

    public String getEmptyAlert() {
        return $(xpath("//div[@data-id='empty-betslip-wrapper']//span[1]")).getText();
    }

    public static double getValueBet() {
        return Double.parseDouble(valueBetLocator.getValue());
    }

    public static double getPossibleWinningAmountBySite() {
        return Double.parseDouble($(xpath("//span[@data-id='betslip-place-bet-button-amount']")).getText());
    }

    public static double calculatePossibleWinningAmount(double valueBet, ArrayList<Double> coefficients) {
        double result = valueBet;
        for (Double coefficient : coefficients) {
            result = result * coefficient;
        }
        return Math.round(result * 100.) / 100.;
    }

    public List<Double> getOddsInBetslip() {
        List<Double> oddValues = new ArrayList<>();

        for (SelenideElement outcome : $(By.xpath("//div[@data-id='betslip-container']"))
                .findAll(By.xpath(".//div[contains(@class, 'Outcome__wrapper') and not(contains(@class, 'Recommended'))]"))) {
            SelenideElement odd = outcome.find(By.xpath(".//div[@data-id='animated-odds-value']"));
            oddValues.add(Double.parseDouble(odd.getText()));
        }
        return oddValues;
    }

    public LoginPage clickLoginOnBetslip() {
        $(xpath("//button[@data-id='betslip-place-bet-button']")).shouldBe(enabled).click();
        return new LoginPage();
    }

    public SportPage clickPlaceBetButtonOnBetslip() {
        $(xpath("//button[@data-id='betslip-place-bet-button']")).shouldBe(enabled).click();
        log.info("Click on button \"Place bet\" in notification message was successful");
        return new SportPage();
    }

    public boolean isPlaceBetButtonEnabled() {
        return $(By.xpath("//button[@data-id='betslip-place-bet-button']")).isEnabled();
    }

    public double getPossibleWin() {
        String possibleWinText = $(By.xpath("//span[@data-id='betslip-place-bet-button-amount']")).getText();
        return Double.parseDouble(possibleWinText.replace('\n', ' ').split(" ")[0]);
    }

    public BetslipPage clickRemoveButton() {
        $(xpath("//button[@data-id='betslip-header-delete-button']")).shouldBe(enabled).click();
        log.info("Click on button \"Remove\" in notification message was successful");
        return this;
    }

    public BetslipPage clickYesInNotificationMessage() {
        $(xpath("//div[contains(@class, 'Dialog__actions')]//button[2]")).shouldBe(enabled).click();
        log.info("Click on button \"YES\" in notification message was successful");
        return this;
    }

    public ArrayList<Double> getCoefficientsFromBetslip() {
        ElementsCollection coefficients = $$(xpath("//div[@data-id='betslip-outcome-block']//div[@data-id='animated-odds-value']//span"));
        ArrayList<Double> coefficientsFromBetslip = new ArrayList<>();
        for (SelenideElement coefficient : coefficients) {
            coefficientsFromBetslip.add(Double.valueOf(coefficient.getText()));
        }
        return coefficientsFromBetslip;
    }

    public void clickSystemButton() {
        $(xpath("//div[@data-id ='system']")).shouldBe(enabled).click();
    }

    public static double calculatePossibleSystemWinningAmount(double valueBet, ArrayList<Double> coefficients) {
        double result = 0;
        for (int i = 0; i < coefficients.size() - 1; i++) {
            for (int j = i + 1; j < coefficients.size(); j++) {
                double temp = coefficients.get(i) * coefficients.get(j);
                result += temp;
            }
        }
        return Math.round((result * valueBet / coefficients.size()) *100.)/100.;
    }
}