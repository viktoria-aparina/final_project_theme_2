package org.pm.mobile.configuration.server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Range;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.pm.mobile.configuration.capabilities.TestDataReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ThreadLocalRandom;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
@Log4j2
public final class AppiumServerConfigurator {

    private static final int ATTEMPTS = 40;
    private static final String IP_ADDRESS = TestDataReader.get().ipAddress();
    private static final ThreadLocal<AppiumDriverLocalService> THREAD_LOCAL_SERVICE = new ThreadLocal<>();
    private static final ConcurrentSkipListSet<Integer> socketList = new ConcurrentSkipListSet<>();
    public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private static final Range<Integer> PORTS_RANGE = Range.between(50_000, 55_000);

    private AppiumServerConfigurator() {
    }

    public static AppiumDriverLocalService getService() {
        if (THREAD_LOCAL_SERVICE.get() == null) {
            log.info("Appium server is null. Try to create one");
            createService();
        }
        log.info("Requested appium server is present and has running status " + THREAD_LOCAL_SERVICE.get().isRunning());
        return THREAD_LOCAL_SERVICE.get();
    }

    private static void createService() {
        DesiredCapabilities cap = new DesiredCapabilities();
        int port = findOpenPortOnAllLocalInterfaces();
        log.info(port + " port for Appium. Try to build server");
        THREAD_LOCAL_SERVICE.set(new AppiumServiceBuilder()
                .withIPAddress(IP_ADDRESS)
                .usingPort(port)
                .withCapabilities(cap)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//                .withLogFile(appiumLogger.getLogFile())
                .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/")
                .withArgument(LOG_LEVEL, "error")
                .build());
        //Start the server with the builder
        log.info("Try to start Appium server");
        Optional.ofNullable(THREAD_LOCAL_SERVICE.get()).ifPresent(AppiumDriverLocalService::start);
        log.info(String.format("Appium server running status is {%s} after start command",
                THREAD_LOCAL_SERVICE.get().isRunning()));
        log.info("Appium server started on port " + port);
    }

    public static int findOpenPortOnAllLocalInterfaces() {
        ServerSocket socket = null;
        for (int i = 0; i < ATTEMPTS; i++) {
            int randomPort = RANDOM.nextInt(PORTS_RANGE.getMinimum(), PORTS_RANGE.getMaximum());
            try {
                socket = new ServerSocket(randomPort);
                socket.close();
                int socketPort = socket.getLocalPort();
                if (!socketList.contains(socketPort) && socketList.add(socketPort)) {
                    return socketPort;
                }
            } catch (IOException e) {
                log.error("Can not obtain new socket port. " + e.getMessage());
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException ioException) {
                        log.error(ioException);
                    }
                }
            }
        }
        throw new RuntimeException("Can't find free port in " + ATTEMPTS + " attempts");
    }

    public static void stopServer() {
        Optional.ofNullable(THREAD_LOCAL_SERVICE.get()).ifPresent(AppiumDriverLocalService::stop);
        log.info(String.format("Appium server running status is {%s} after stop command",
                THREAD_LOCAL_SERVICE.get().isRunning()));
        socketList.remove(THREAD_LOCAL_SERVICE.get().getUrl().getPort());
        THREAD_LOCAL_SERVICE.remove();
        log.info("Appium server has been stopped");
    }
}