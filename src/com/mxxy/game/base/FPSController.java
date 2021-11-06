package com.mxxy.game.base;

import com.mxxy.game.resources.Constant;

import java.text.DecimalFormat;

/**
 * FPS控制器
 *
 * @author dell
 */
public class FPSController {

    public static final long PERIOD = (long) (1.0 / Constant.FPS * 1000000000L);

    public static long MAX_FPS_INTERVAL = 1000000000L;

    private double nowFPS;

    private long calcInterval = 0L;

    private long time;

    private long frameCount = 0;

    private DecimalFormat df = new DecimalFormat("0.0");

    /**
     * 生成FPS
     */
    public void makeFPS() {
        frameCount++;
        calcInterval += PERIOD;
        if (calcInterval >= MAX_FPS_INTERVAL) {
            long timeNow = System.nanoTime();
            long realTime = timeNow - time;
            nowFPS = ((double) frameCount / realTime) * MAX_FPS_INTERVAL;
            frameCount = 0L;
            calcInterval = 0L;
            time = timeNow;
        }
    }

    public long getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(long frameCount) {
        this.frameCount = frameCount;
    }

    public double getNowFPS() {
        return nowFPS;
    }

    public void setNowFPS(double nowFPS) {
        this.nowFPS = nowFPS;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getFPS() {
        return df.format(nowFPS);
    }
}
