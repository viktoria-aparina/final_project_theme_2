package org.pm.mobile.configuration.capabilities;

import org.aeonbits.owner.ConfigCache;
import org.pm.mobile.configuration.TestData;

public final class TestDataReader {

    private TestDataReader() {
    }

    public static TestData get() {
        return ConfigCache.getOrCreate(TestData.class, System.getProperties());
    }
}
