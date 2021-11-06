package com.mxxy.game.listener;

import com.mxxy.game.base.Panel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PaneListener extends AbstractBaseEventListener<Panel> {


    /**
     * 记录点下和拖动后的位置
     */
    int newX, newY, oldX, oldY;
    // 这两个坐标为组件当前的坐标
    int startX, startY;
    private boolean isReightClose;

    public PaneListener(Panel panel, boolean isReightClose) {
        mPanel = panel;
        mPanel.setListener(this);
        this.isReightClose = isReightClose;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // 此为得到事件源组件
        Component cp = (Component) e.getSource();
        // 当鼠标点下的时候记录组件当前的坐标与鼠标当前在屏幕的位置
        newX = e.getXOnScreen();

        newY = e.getYOnScreen();
        // 设置bounds,将点下时记录的组件开始坐标与鼠标拖动的距离相加
        cp.setBounds(startX + (newX - oldX), startY + (newY - oldY),
                cp.getWidth(), cp.getHeight());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component cp = (Component) e.getSource();
        // 当鼠标点下的时候记录组件当前的坐标与鼠标当前在屏幕的位置
        startX = cp.getX();

        startY = cp.getY();

        oldX = e.getXOnScreen();

        oldY = e.getYOnScreen();

        if (this.isReightClose) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                mPanel.close();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
