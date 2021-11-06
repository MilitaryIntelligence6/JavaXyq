package com.mxxy.game.widget;

import java.awt.*;

/**
 * 动画帧
 *
 * @author dell
 */
public class AnimationFrame extends AbstractCanvas {
    private Image image;

    private long endTime;

    private int centerX;

    private int centerY;

    public AnimationFrame(Image image, long endTime) {
        this.image = image;
        this.endTime = endTime;
    }

    public AnimationFrame(Image image, long endTime, int centerX, int centerY) {
        this(image, endTime);
        this.centerX = centerX;
        this.centerY = centerY;
    }

    @Override
    protected void draw(Graphics2D g, int x, int y, int width, int hight) {
        int x1 = x - this.centerX;
        int y1 = y - this.centerY;
        g.drawImage(this.image, x1, y1, x1 + width, y1 + hight, 0, 0, width, hight, null);
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void dispose() {
        this.image = null;
    }
}
