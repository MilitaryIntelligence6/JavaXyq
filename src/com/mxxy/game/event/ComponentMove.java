package com.mxxy.game.event;

import java.awt.Component;
import java.awt.event.MouseEvent;

import com.mxxy.game.listener.AbstractBaseEventListener;

/**
 * 面板移动事件
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
@SuppressWarnings("rawtypes")
public class ComponentMove extends AbstractBaseEventListener {

	// 这两组x和y为鼠标点下时在屏幕的位置和拖动时所在的位置
	int newX, newY, oldX, oldY;

	// 这两个坐标为组件当前的坐标
	int startX, startY;

	@Override
	public void mouseDragged(MouseEvent e) {
		// 此为得到事件源组件
		Component cp = (Component) e.getSource();
		// 当鼠标点下的时候记录组件当前的坐标与鼠标当前在屏幕的位置
		newX = e.getXOnScreen();

		newY = e.getYOnScreen();
		// 设置bounds,将点下时记录的组件开始坐标与鼠标拖动的距离相加
		cp.setBounds(startX + (newX - oldX), startY + (newY - oldY), cp.getWidth(), cp.getHeight());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Component cp = (Component) e.getSource();
		// 当鼠标点下的时候记录组件当前的坐标与鼠标当前在屏幕的位置
		startX = cp.getX();

		startY = cp.getY();

		oldX = e.getXOnScreen();

		oldY = e.getYOnScreen();
	}
}
