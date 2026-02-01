package com.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoggerUtility {

    private LoggerUtility() {
        // prevent instantiation
    }

    // Existing method (cleaned)
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    //Stackwalker class to get the current calling class and also works for static as well as non- static class (this.class) does mot wprk for static class
    public static Logger getLogger() {
        return LogManager.getLogger(
            StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                       .getCallerClass()
        );
    }
}