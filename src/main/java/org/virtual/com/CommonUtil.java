package org.virtual.com;

public final class CommonUtil {

    private CommonUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }


}
