package org.pm.ui.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.By.xpath;

@Log4j2
public class HomePage extends BasePage {

    @Override
    public boolean isPageOpened() {
        try {
            $(xpath("//div[@data-id='desktop-tabs-wrapper']")).shouldBe(visible);
            return true;
        } catch (TimeoutException exception) {
            log.error("The HomePage wasn't open because of error {}", exception.getCause());
            return false;
        }
    }

    public static HomePage open() {
        System.setProperty("chromeoptions.args", "--user-agent=ParimatchTechAcademy/89870edc1aaea008bd3a519c");
        Configuration.timeout = 10000;
        Selenide.open(properties.getProperty("baseUrl"));
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
        log.info("Tab " + sportName + " and \"All coming\" was opened successfully");
        return new SportPage();
    }
}
