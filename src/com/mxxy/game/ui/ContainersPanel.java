package com.mxxy.game.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
abstract public class ContainersPanel extends JPanel {

	private long lastTime;

	public ContainersPanel(int x, int y, int width, int height) {
		super(null);
		setBounds(x, y, width, height);
		setOpaque(false);
		setFocusable(false);
		setRequestFocusEnabled(false);
	}

	@Override
	public void paint(Graphics g) {
		long currTime = System.currentTimeMillis();// 程序现在时间
		if (lastTime == 0L)
			lastTime = currTime;// 如果lastTime=0.则它等于程序第一次编译时间
		long elapsedTime = (currTime - this.lastTime);// elapsedTime前后两次绘制时间差
		this.lastTime = currTime;// 使用完毕后把现在时间等于持续时间，方便上一步计算前后两次绘制中间时间差
		draw((Graphics2D) g, elapsedTime);
	}

	protected abstract void draw(Graphics2D g, long elapsedTime);

	@Override
	public void paintImmediately(int x, int y, int w, int h) {}
}
