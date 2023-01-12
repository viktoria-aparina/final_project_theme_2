package org.pm.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

  private int count = 0;
  private static final int MAX_TRY = 2;

  @Override
  public boolean retry(ITestResult iTestResult) {
    if (!iTestResult.isSuccess()) {
      if (count < MAX_TRY) {
        count++;
        iTestResult.setStatus(ITestResult.FAILURE);
        return true;
      } else {
        iTestResult.setStatus(ITestResult.FAILURE);
      }
    } else {
      iTestResult.setStatus(ITestResult.SUCCESS);
    }
    return false;
  }
}
