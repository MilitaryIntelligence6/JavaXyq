package com.mxxy.game.widget;

import java.awt.*;

abstract public class AbstractCanvas implements AnimationDraw {
    private float alpha = 1.0f;// 图形的透明度
    private int width;
    private int height;
    private int x;// 动画X位置
    private int y;// 动画Y位置

    public void drawBitmap(Graphics g) {
        this.drawBitmap(g, x, y);
    }

    @Override
    public void drawBitmap(Graphics g, int x, int y) {
        this.drawBitmap(g, x, y, width, height);
    }

    @Override
    public void drawBitmap(Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        draw(g2, x, y, width, height);
        g2.dispose();
    }

    /**
     * 子类绘制
     *
     * @param g
     * @param x
     * @param y
     * @param width
     * @param hight
     */
    protected abstract void draw(Graphics2D g, int x, int y, int width, int hight);

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void fadeIn(long paramLong) {

    }

    @Override
    public void fadeOut(long paramLong) {
        long duration = paramLong;
        long interval = paramLong / 10L;
        AnimatorThread thread = new AnimatorThread(duration, interval);
        thread.start();
    }

    public void setLocation(int x, int y) {// 设置动画显示位置
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean contains(int paramInt1, int paramInt2) {
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    /**
     * 动画线程
     *
     * @author dell
     */
    private class AnimatorThread extends Thread {
        private long interval;  //间隔
        private long duration; //时长
        private long passTime;

        /**
         * @param duration 持续时间
         * @param interval 间隔
         */
        public AnimatorThread(long duration, long interval) {
            this.duration = duration;
            this.interval = interval;
            setName("animator");
        }

        public void run() {
            while (this.passTime < this.duration) {
                this.passTime += this.interval;
                AbstractCanvas.this.alpha = (1.0F - 1.0F * (float) this.passTime / (float) this.duration);
                if (AbstractCanvas.this.alpha < 0.0F) {
                    AbstractCanvas.this.alpha = 0.0F;
                }
                try {
                    Thread.sleep(this.interval);
                } catch (InterruptedException localInterruptedException) {

                }
            }
        }
    }
}
