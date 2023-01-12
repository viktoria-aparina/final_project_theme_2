package org.pm.ui.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

@Log4j2
public class LoginPage extends BasePage {
  @Override
  public boolean isPageOpened() {
    try {
      $(xpath("//button[@data-id='login-button']")).shouldBe(visible);
      log.info("The LoginPage was opened successfully");
      return true;
    } catch (TimeoutException exception) {
      log.error("The HomePage wasn't open because of error {}", exception.getCause());
      return false;
    }
  }

  public LoginPage chooseLoginTypeById() {
    $(xpath("//form[@data-id='login-form']/div/div[@data-id='list']")).shouldBe(visible).click();
    $(xpath("//form[@data-id='login-form']/div/div//*[@value='id']")).shouldBe(visible).click();
    log.info("Type login was changed successfully");
    return this;
  }

  public HomePage loginWithTestCredentialId() {
    $(id("id")).sendKeys(properties.getProperty("login"));
    log.info("ID was entered");
    $(id("password")).sendKeys(properties.getProperty("password"));
    log.info("Password was entered");
    $(xpath("//button[@data-id='login-button']")).shouldBe(enabled).click();
    return new HomePage();
  }
}
