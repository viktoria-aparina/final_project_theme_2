package org.pm.mobile;

import io.appium.java_client.events.api.general.AppiumWebDriverEventListener;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
@Log4j2
public class EventLogListener implements AppiumWebDriverEventListener {

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {
        // No need to log a message
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {
        log.info("Type operation has been finished");
    }

    @Override
    public void beforeAlertAccept(WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void afterAlertAccept(WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        log.info("Click operation has been finished");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        // No need to log a message
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        // No need to log a message
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void afterScript(String script, WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        // No need to log a message
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
        // No need to log a message
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
        // No need to log a message
    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        // No need to log a message
    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        log.info("Get text operation has been finished");
    }
}
