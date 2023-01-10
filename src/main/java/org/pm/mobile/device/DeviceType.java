package org.pm.mobile.device;

public enum DeviceType {
    IOS("ios"),
    ANDROID("android");

    private final String type;

    DeviceType(String type) {
        this.type = type;
    }

    public static DeviceType valueOfIgnoringCase(String type) {
        return DeviceType.valueOf(type.toUpperCase());
    }

    public boolean isIos() {
        return type.equals(IOS.toString());
    }

    public boolean isAndroid() {
        return type.equals(ANDROID.toString());
    }

    @Override
    public String toString() {
        return type;
    }
}