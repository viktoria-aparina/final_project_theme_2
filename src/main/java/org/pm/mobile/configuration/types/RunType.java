package org.pm.mobile.configuration.types;

public enum RunType {
    CLOUD("cloud"),
    LOCAL("local");

    private final String type;

    RunType(String type) {
        this.type = type;
    }

    public static RunType valueOfIgnoringCase(String type) {
        return RunType.valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return type;
    }
}
