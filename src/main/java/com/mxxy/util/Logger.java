package com.mxxy.util;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName Logger
 * @Description TODO
 * @CreateTime 2021年11月06日 23:39:00
 */
public class Logger {

    private Logger() {
        throw new UnsupportedOperationException(String.format("here are no %s instance for you", getClass().getName()));
    }

    public static void debug() {

    }

    public static void echoUnImpl() {
        System.err.println("un impl method");
    }
}
