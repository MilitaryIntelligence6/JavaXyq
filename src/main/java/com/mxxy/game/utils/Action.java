package com.mxxy.game.utils;

/**
 * 回调
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class Action {
    /**
     * 封装一个没有参数且没有返回值的方法。
     */
    public abstract class Void {
        /**
         * 执行方法。
         */
        public abstract void invoke();
    }

    /**
     * 封装一个具有一个参数且没有返回值的方法。
     *
     * @param <T>：此委托封装的方法的参数类型。
     */
    public abstract class One<T> {
        /**
         * 执行方法。
         *
         * @param arg ：此委托封装的方法的参数。
         */
        public abstract void invoke(T arg);
    }

    /**
     * 封装一个具有两个参数且没有返回值的方法。
     *
     * @param <T1>：此委托封装的方法的第一个参数类型。
     * @param <T2>：此委托封装的方法的第二个参数类型。
     */
    public abstract class Two<T1, T2> {
        /**
         * 执行方法。
         *
         * @param arg1 ：此委托封装的方法的第一个参数。
         * @param arg2 ：此委托封装的方法的第二个参数。
         */
        public abstract void invoke(T1 arg1, T2 arg2);
    }

    /**
     * 封装一个具有三个参数且没有返回值的方法。
     *
     * @param <T1>：此委托封装的方法的第一个参数类型。
     * @param <T2>：此委托封装的方法的第二个参数类型。
     * @param <T3>：此委托封装的方法的第三个参数类型。
     */
    public abstract class Three<T1, T2, T3> {
        /**
         * 执行方法。
         *
         * @param arg1 ：此委托封装的方法的第一个参数。
         * @param arg2 ：此委托封装的方法的第二个参数。
         * @param arg3 ：此委托封装的方法的第三个参数。
         */
        public abstract void invoke(T1 arg1, T2 arg2, T3 arg3);
    }

    /**
     * 封装一个具有四个参数且没有返回值的方法。
     *
     * @param <T1>：此委托封装的方法的第一个参数类型。
     * @param <T2>：此委托封装的方法的第二个参数类型。
     * @param <T3>：此委托封装的方法的第三个参数类型。
     * @param <T4>：此委托封装的方法的第四个参数类型。
     */
    public abstract class Four<T1, T2, T3, T4> {
        /**
         * 执行方法。
         *
         * @param arg1 ：此委托封装的方法的第一个参数。
         * @param arg2 ：此委托封装的方法的第二个参数。
         * @param arg3 ：此委托封装的方法的第三个参数。
         * @param arg4 ：此委托封装的方法的第四个参数。
         */
        public abstract void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4);
    }

    /**
     * 封装一个具有五个参数且没有返回值的方法。
     *
     * @param <T1>：此委托封装的方法的第一个参数类型。
     * @param <T2>：此委托封装的方法的第二个参数类型。
     * @param <T3>：此委托封装的方法的第三个参数类型。
     * @param <T4>：此委托封装的方法的第四个参数类型。
     * @param <T5>：此委托封装的方法的第五个参数类型。
     */
    public abstract class Five<T1, T2, T3, T4, T5> {
        /**
         * 执行方法。
         *
         * @param arg1 ：此委托封装的方法的第一个参数。
         * @param arg2 ：此委托封装的方法的第二个参数。
         * @param arg3 ：此委托封装的方法的第三个参数。
         * @param arg4 ：此委托封装的方法的第四个参数。
         * @param arg5 ：此委托封装的方法的第五个参数。
         */
        public abstract void invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5);
    }
}
