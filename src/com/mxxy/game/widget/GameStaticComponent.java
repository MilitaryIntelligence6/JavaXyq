package com.mxxy.game.widget;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏静态的Image 组件
 */
public class GameStaticComponent extends AbstractCanvas {

    private Image img;
    private String path;

    public GameStaticComponent(String path, int x, int y) {
        this.img = new ImageIcon("componentsRes/" + path + ".png").getImage();
        super.setLocation(x, y);
    }

    @Override
    public void dispose() {

    }

    @Override
    protected void draw(Graphics2D g, int x, int y, int width, int hight) {
        g.drawImage(img, getX(), getY(), null);
    }

    public String getPath() {
        return path;
    }

    @Override
    public int getWidth() {
        // TODO Auto-generated method stub
        return img.getWidth(null);
    }

    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return img.getHeight(null);
    }
}
