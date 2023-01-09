package org.pm.ui.pages;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.By.xpath;

@Log4j2
public class HomePage extends BasePage {

    ElementsCollection coefficients = $$(xpath("//div[@data-id='event-card-container-event']//div[@data-id='animated-odds-value']/span"));

    @Override
    public boolean isPageOpened() {
        try {
            $(xpath("//div[@data-id='user-box-balance']")).shouldBe(visible);
            return true;
        } catch (TimeoutException exception) {
            log.error("The HomePage wasn't open because of error {}", exception.getCause());
            return false;
        }
    }

    public static HomePage open() {
        System.setProperty("chromeoptions.args", "--user-agent=ParimatchTechAcademy/89870edc1aaea008bd3a519c");
        Selenide.open("https://pari-match-betting.com/en/");
        getWebDriver().manage().window().maximize();
        log.info("Chrome Driver was opened successfully");
        return new HomePage();
    }

    public LoginPage clickOnLoginButton() {
        Configuration.timeout = 10000;
        $(xpath("//button[@data-id='header-login']")).shouldBe(visible).click();
        log.info("Click on button \"Log in\" was successful");
        return new LoginPage();
    }

    public SportPage selectSport(String sportName) {
        $(xpath("//div[@data-id='sport-navigation-item-" + sportName + "']")).shouldBe(visible).click();
        $(xpath("//a[@data-id='line-tab-prematch']")).click();
        return new SportPage();
    }

    public void chooseBetsForBetslip(int... numberOfCoefficient) {
        for (int number : numberOfCoefficient) {
            coefficients.get(number).shouldBe(visible).click();
            log.info("Click on coefficient was successful");
        }
    }

    public ArrayList<Double> getCoefficient(int... numberOfCoefficient) {
        ArrayList<Double> result = new ArrayList<Double>();
        for (int number : numberOfCoefficient) {
            result.add(Double.parseDouble(coefficients.get(number).getText()));
        }
        return result;
    }

    public HomePage clearBetslip() {
        $(By.xpath("//div[@data-id='betslip-header-delete-button-icon']")).shouldBe(visible).click();
        $(By.xpath("//span[text()='Yes']")).shouldBe(visible).click();
        return this;
    }

    public void close() {
        Selenide.closeWebDriver();
    }
}
