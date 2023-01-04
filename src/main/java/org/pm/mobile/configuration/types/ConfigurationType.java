package org.pm.mobile.configuration.types;


import org.pm.mobile.device.DeviceType;

import static org.pm.mobile.configuration.types.RunType.CLOUD;
import static org.pm.mobile.configuration.types.RunType.LOCAL;
import static org.pm.mobile.device.DeviceType.ANDROID;
import static org.pm.mobile.device.DeviceType.IOS;

public enum ConfigurationType {
    ANDROID_CLOUD,
    IOS_CLOUD,
    ANDROID_LOCAL,
    IOS_LOCAL;

    public static ConfigurationType getType(DeviceType deviceType, RunType runType) {
        if (deviceType == ANDROID && runType == CLOUD) return ANDROID_CLOUD;
        else if (deviceType == IOS && runType == CLOUD) return IOS_CLOUD;
        else if (deviceType == ANDROID && runType == LOCAL) return ANDROID_LOCAL;
        else if (deviceType == IOS && runType == LOCAL) return IOS_LOCAL;
        else throw new IllegalArgumentException("Incorrect device type or run type variable specified");
    }
}