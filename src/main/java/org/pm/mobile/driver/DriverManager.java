package org.pm.mobile.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pm.mobile.EventLogListener;
import org.pm.mobile.configuration.capabilities.CapabilitiesConfigurator;
import org.pm.mobile.configuration.capabilities.CapabilitiesReader;
import org.pm.mobile.configuration.server.AppiumServerConfigurator;
import org.pm.mobile.configuration.types.ConfigurationType;
import org.pm.mobile.configuration.types.RunType;
import org.pm.mobile.device.DeviceType;
import org.pm.mobile.entity.Emulator;

import java.net.MalformedURLException;
import java.util.Optional;

public class DriverManager {

    private static final Logger LOG = LogManager.getRootLogger();
    private static final DeviceType DEVICE_TYPE = DeviceType.valueOfIgnoringCase(CapabilitiesReader.get().deviceType());
    private static final RunType RUN_TYPE = RunType.valueOfIgnoringCase(CapabilitiesReader.get().runType());
    private static final ConfigurationType CONFIGURATION_TYPE = ConfigurationType.getType(DEVICE_TYPE, RUN_TYPE);

    private static final ThreadLocal<AppiumDriver<MobileElement>> threadLocalDriver = new ThreadLocal<>();

    public static void createDriver(String udid, String wda, String deviceName, String platformVersion) throws MalformedURLException {
        AppiumDriver<MobileElement> driver = switch (CONFIGURATION_TYPE) {
            case ANDROID_LOCAL -> new AndroidDriver<>(AppiumServerConfigurator.getService(),
                    CapabilitiesConfigurator.getAndroidLocalCapabilities(udid, deviceName, platformVersion));
            case IOS_LOCAL -> new IOSDriver<>(AppiumServerConfigurator.getService(),
                    CapabilitiesConfigurator.getIosLocalCapabilities(deviceName, wda, udid));
            default -> throw new IllegalStateException("Unexpected value: " + CONFIGURATION_TYPE);
        };
        driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new EventLogListener());
        threadLocalDriver.set(driver);
        LOG.info("Driver is created");
        LOG.info("Device type is {}", DEVICE_TYPE);
        LOG.info("Run type is {}", RUN_TYPE);
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return threadLocalDriver.get();
    }

    public static void terminateDriver() {
        Optional.ofNullable(getDriver()).ifPresent((AppiumDriver<MobileElement> driver) -> {
            driver.quit();
            threadLocalDriver.remove();
            LOG.info("Driver is closed");
        });
    }

    public static void terminateAppium() {
        AppiumServerConfigurator.stopServer();
    }

    public static void terminateEmulator(String udid) {
        new Emulator(udid).close();
    }
}