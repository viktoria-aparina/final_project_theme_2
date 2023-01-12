package org.pm.ui.utils;

import com.codeborne.selenide.SelenideDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AllureUtils {
  @Attachment(value = "screenshot", type = "image/png")
  public static byte[] takeScreenshot(SelenideDriver driver) {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }
}