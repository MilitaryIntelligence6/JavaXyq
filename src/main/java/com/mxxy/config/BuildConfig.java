package com.mxxy.config;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName BuildConfig
 * @Description TODO
 * @CreateTime 2021年11月06日 23:54:00
 */
public final class BuildConfig {

    private BuildConfig() {
        throw new UnsupportedOperationException(String.format("here are no %s instance for you", getClass().getName()));
    }

    public static final boolean DEBUG = true;

    public static final boolean RELEASE = false;

    public static final boolean UN_IMPL_WAR_ONLY_CAN_RUN_AWAY = false;
}
