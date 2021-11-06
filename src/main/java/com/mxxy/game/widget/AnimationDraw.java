package com.mxxy.game.widget;

import java.awt.*;

public interface AnimationDraw {
    /**
     * 指定 x y
     *
     * @param g
     * @param x
     * @param y
     */
    public void drawBitmap(Graphics g, int x, int y);

    /**
     * 指定 x, y ,width,height
     *
     * @param g
     * @param x
     * @param y
     * @param width
     * @param hight
     */
    public void drawBitmap(Graphics g, int x, int y, int width, int hight);

    public abstract void fadeIn(long paramLong);

    public abstract void fadeOut(long paramLong);

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract boolean contains(int paramInt1, int paramInt2);

    /**
     * 设置坐标
     *
     * @param x
     * @param y
     */
    public abstract void setLocation(int x, int y);

    /**
     * 销毁
     */
    public abstract void dispose();

}
