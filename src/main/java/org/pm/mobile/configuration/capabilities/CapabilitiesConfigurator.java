package org.pm.mobile.configuration.capabilities;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.pm.mobile.configuration.types.RunType;
import org.pm.mobile.device.DeviceType;
import org.pm.mobile.utils.FileUtils;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.PLATFORM_NAME;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.IOSMobileCapabilityType.BUNDLE_ID;
import static io.appium.java_client.remote.IOSMobileCapabilityType.WDA_LOCAL_PORT;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.pm.mobile.configuration.types.RunType.LOCAL;
import static org.pm.mobile.device.DeviceType.ANDROID;
import static org.pm.mobile.device.DeviceType.IOS;

public final class CapabilitiesConfigurator {

    private static final String KEEP_DEVICE = "keepDevice";

    private CapabilitiesConfigurator() {
    }

    public static DesiredCapabilities getAndroidLocalCapabilities(String udid, String deviceName, String platformVersion) {
        var capabilities = new DesiredCapabilities();
        capabilities.setCapability(APP, FileUtils.getAppLocation());
        capabilities.setCapability(AVD, deviceName == null ? CapabilitiesReader.get(ANDROID, LOCAL).deviceName() : deviceName);
        setAndroidCommonCapabilities(capabilities, LOCAL);
        setCommonCapabilities(capabilities, ANDROID, LOCAL, udid, deviceName, platformVersion);
        return capabilities;
    }

    public static MutableCapabilities getAndroidCloudCapabilities(String udid, String deviceName, String platformVersion) {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("appium:deviceName","Android GoogleAPI Emulator");
        caps.setCapability("appium:deviceOrientation", "portrait");
        caps.setCapability("appium:platformVersion","12.0");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:app", "storage:filename=rls.apk");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("build", "appium-build-VV0OH");
        sauceOptions.setCapability("name", "<your test name>");
        caps.setCapability("sauce:options", sauceOptions);
        return caps;
    }

    public static DesiredCapabilities getIosLocalCapabilities(String deviceName, String wda, String udid) {
        var capabilities = new DesiredCapabilities();
        capabilities.setCapability(APP, FileUtils.getAppLocation());
        capabilities.setCapability(BUNDLE_ID, CapabilitiesReader.get(IOS, LOCAL).appPackage());
        capabilities.setCapability(DEVICE_NAME, deviceName == null ? CapabilitiesReader.get(IOS, LOCAL).deviceName() : deviceName);
        capabilities.setCapability(WDA_LOCAL_PORT, wda == null ? CapabilitiesReader.get(IOS, LOCAL).wda() : wda);
        capabilities.setCapability(AUTOMATION_NAME, CapabilitiesReader.get(IOS, LOCAL).automationName());
        capabilities.setCapability(UDID, udid == null ? CapabilitiesReader.get(IOS, LOCAL).udid() : udid);
        return capabilities;
    }

    private static void setCommonCapabilities(DesiredCapabilities capabilities, DeviceType deviceType, RunType runType, String udid, String deviceName, String platformVersion) {
        capabilities.setCapability(DEVICE_NAME, deviceName == null ? CapabilitiesReader.get(deviceType, runType).deviceName() : deviceName);
        capabilities.setCapability(PLATFORM_NAME, CapabilitiesReader.get(deviceType, runType).platformName());
        capabilities.setCapability(PLATFORM_VERSION, platformVersion == null ? CapabilitiesReader.get(deviceType, runType).platformVersion() : platformVersion);
        capabilities.setCapability(UDID, udid == null ? CapabilitiesReader.get(deviceType, runType).udid() : udid);
        capabilities.setCapability(NO_RESET, true);
        capabilities.setCapability(KEEP_DEVICE, true);
        capabilities.setCapability(NEW_COMMAND_TIMEOUT, TestDataReader.get().newCommandTimeout());
    }

    private static void setAndroidCommonCapabilities(DesiredCapabilities capabilities, RunType runType) {
        capabilities.setCapability(APP_PACKAGE, CapabilitiesReader.get(ANDROID, runType).appPackage());
//        capabilities.setCapability(APP_ACTIVITY, CapabilitiesReader.get(ANDROID, runType).appActivity());
    }
}
