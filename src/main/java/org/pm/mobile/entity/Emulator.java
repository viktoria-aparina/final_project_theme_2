package org.pm.mobile.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pm.mobile.configuration.capabilities.CapabilitiesReader;

import java.io.IOException;

import static java.lang.String.format;
import static org.pm.mobile.configuration.types.RunType.LOCAL;
import static org.pm.mobile.device.DeviceType.ANDROID;

public class Emulator {

    private static final Logger LOG = LogManager.getRootLogger();
    private static final String KILL_AVD = "adb -s %s emu kill";

    private final String udid;

    public Emulator(String udid) {
        this.udid = udid == null ? CapabilitiesReader.get(ANDROID, LOCAL).udid() : udid;
    }

    public void close() {
        try {
            Runtime.getRuntime().exec(format(KILL_AVD, udid));
            LOG.info("AVD '{}' is closed", udid);
        } catch (IOException e) {
            LOG.info("AVD '{}' was not closed", udid, e);
        }
    }
}