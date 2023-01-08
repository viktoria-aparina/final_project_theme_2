package org.pm.ui.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Parimatch {
    private static final String SITE_URL = "https://parimatch.com/uk/";

    public Parimatch open() {
        Selenide.open(SITE_URL);
        return new Parimatch();
    }

    public boolean checkIfLoggedIn() {
        return $$(By.xpath("//div[@data-id='header-user-box']")).size() > 0;
    }

    public Parimatch enterCredentials() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Configuration.timeout = 10000L;
        $(By.name("phone")).shouldBe(visible).setValue("666888377");
        $(By.id("password")).shouldBe(visible).setValue("Valeriya156_");
        $(By.xpath("//button[@data-id='login-button']")).shouldBe(visible).click();
        return this;
    }

    public Parimatch loginFromStartPage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Configuration.timeout = 10000L;
        $(By.xpath("//button[@data-id='header-login']")).shouldBe(visible).click();
        this.enterCredentials();
        return this;

    }

    public Parimatch selectSport(String sportName) {
        Configuration.timeout = 10000L;
        $(By.xpath("//div[@data-id='sport-navigation-item-" + sportName + "']")).shouldBe(visible).click();
        $(By.id("line-holder")).shouldBe(visible);
        $(By.xpath("//a[@data-id='line-tab-prematch']")).click();
        return this;
    }

    private SelenideElement getOutcome(int eventIndex, int outcomeIndex) {
        return $(By.xpath(String.format("//div[contains(@data-onboarding, \"event-card\")][%d]//div[@data-id=\"outcome\"][%d]", eventIndex, outcomeIndex)));
    }

    public Parimatch clickOutcome(int eventIndex, int outcomeIndex) {
        Configuration.timeout = 10000L;
        this.getOutcome(eventIndex, outcomeIndex).shouldBe(visible).click();
        return this;
    }

    public double getOutcomeCoefficient(int eventIndex, int outcomeIndex) {
        return Double.parseDouble(this.getOutcome(eventIndex, outcomeIndex).find(By.xpath(".//div[@data-id=\"animated-odds-value\"]//span")).getText());
    }

    public Parimatch editSumOfBet(String sumOfBet) {
        Configuration.timeout = 10000L;
        $(By.xpath("//input[@data-id='betslip-stake-input']")).setValue(sumOfBet).pressTab();
        return this;
    }

    public double getTotalOdd() {
        String totalOdd = $(By.xpath("//div[@data-id='betslip-total-odds-value']")).getText();
        return Double.parseDouble(totalOdd);
    }

    public List<Double> getIndividualOdds() {
        List<Double> oddValues = new ArrayList<>();

        for (SelenideElement outcome : $(By.xpath("//div[@data-id='betslip-container']"))
                .findAll(By.xpath(".//div[contains(@class, 'Outcome__wrapper') and not(contains(@class, 'Recommended'))]"))) {
            SelenideElement odd = outcome.find(By.xpath(".//div[@data-id='animated-odds-value']"));
            oddValues.add(Double.parseDouble(odd.getText()));
        }
        return oddValues;
    }

    public double getPossibleWin() {
        String possibleWinText = $(By.xpath("//span[@data-id='betslip-place-bet-button-amount']")).getText();
        possibleWinText = possibleWinText.replace('\n', ' ');
        return Double.parseDouble(possibleWinText.split(" ")[0]);
    }

    public String getBetSlipInputValue() {
        return $(By.xpath("//input[@data-id='betslip-stake-input']")).getValue();
    }

    public boolean isPlaceBetButtonEnabled() {
        return $(By.xpath("//button[@data-id='betslip-place-bet-button']")).isEnabled();
    }

    public Parimatch clickPlaceBetButton() {
        $(By.xpath("//button[@data-id='betslip-place-bet-button']")).shouldBe(visible).click();
        return this;
    }

    public Parimatch clearBetslip() {
        $(By.xpath("//div[@data-id='betslip-header-delete-button-icon']")).shouldBe(visible).click();
        $(By.xpath("//span[text()='Yes']")).shouldBe(visible).click();
        return this;
    }

    public void close() {
        Selenide.closeWebDriver();
    }
}
