package dxxi3d.simple_durability_tooltip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ModLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(SharedConstants.MOD_NAME);

    private ModLogger() {
    }

    public static void sendInitialized() {
        LOGGER.info("Mod initialized!");
    }

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void warn(String message) {
        LOGGER.warn(message);
    }

    public static void error(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }
}
