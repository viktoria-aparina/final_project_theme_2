package org.pm.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class HomePage extends BasePage {

    ElementsCollection coefficients = $$(By.xpath("//div[@data-id='event-card-container-event']//div[@data-id='animated-odds-value']/span"));

    public static HomePage open() {
        Selenide.open(baseUrl);
        getWebDriver().manage().window().maximize();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=ParimatchTechAcademy/89870edc1aaea008bd3a519c");
        new ChromeDriver(options);
        log.info("Chrome Driver was opened successfully");
        return new HomePage();
    }

    public boolean isPageOpened() {
        try {
            $(By.xpath("//div[@data-id='user-box-balance']")).shouldBe(visible);
            return true;
        } catch (TimeoutException exception) {
            log.error("The HomePage wasn't open because of error {}", exception.getCause());
            return false;
        }
    }

    public LoginPage clickOnLoginButton() {
        $(By.xpath("//button[@data-id='header-login']")).click();
        log.info("Click on button \"Увийти\" was successful");
        return new LoginPage();
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
}
