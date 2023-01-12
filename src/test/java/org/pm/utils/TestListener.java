package org.pm.utils;

import com.codeborne.selenide.SelenideDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchSessionException;
import org.pm.ui.utils.AllureUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNG;

@Log4j2
public class TestListener implements ITestListener {

  private static int count  = 0;
  private final static int MAX_TRY = 2;

  private byte[] takeScreenshot(ITestResult iTestResult) {
    ITestContext context = iTestResult.getTestContext();
    try {
      SelenideDriver driver = (SelenideDriver) context.getAttribute("driver");
      if (driver != null) {
        return AllureUtils.takeScreenshot(driver);
      } else {
        return new byte[]{};
      }
    } catch (NoSuchSessionException | IllegalStateException ex) {
      return new byte[]{};
    }
  }

  @Override
  public void onTestSuccess(ITestResult iTestResult) {
    log.info("Testcase " + iTestResult.getName() + " was succeed");
  }

  @Override
  public void onTestFailure(ITestResult iTestResult) {
    log.info("Testcase " + iTestResult.getName() + " was failed");
    takeScreenshot(iTestResult);
    if (count < MAX_TRY) {
      count++;
      TestNG tng = new TestNG();
      tng.setDefaultTestName("RETRY TEST");
      Class[] classes1 = { iTestResult.getTestClass().getRealClass() };
      tng.setTestClasses(classes1);
      tng.addListener(new TestListener());
      tng.run();
    }
  }

  @Override
  public void onTestSkipped(ITestResult iTestResult) {
    log.info("Testcase " + iTestResult.getName() + " was skipped");
    takeScreenshot(iTestResult);
  }
}
