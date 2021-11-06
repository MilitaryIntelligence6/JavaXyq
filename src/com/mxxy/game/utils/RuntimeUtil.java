package com.mxxy.game.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Cmd 指令
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public final class RuntimeUtil {

    public static String exec(String command) {
        System.err.println(command + "command");
        StringBuilder sb = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            process.getOutputStream().close();
            reader.close();
            process.destroy();
        } catch (Exception e) {
            System.out.println(e);
        }
        return sb.toString();
    }

    public static String getJarExecPath(@SuppressWarnings("rawtypes") Class clazz) {
        String path = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
        return path.substring(1);
    }
}
