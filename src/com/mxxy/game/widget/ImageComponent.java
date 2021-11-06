package com.mxxy.game.widget;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import com.mxxy.game.utils.SpriteFactory;

public class ImageComponent extends AbstractCanvas {
	
	private Image image;
	
	public Rectangle rect;
	
	public String path;

	public ImageComponent(String path, int x, int y, Point p) {
		this.path = path;
		this.image = SpriteFactory.loadImage("res/componentsRes/" + path + ".png");
		if (p != null)
			this.rect = new Rectangle(x, y, p.x, p.y); // 构造矩形对象
		super.setLocation(x, y);
	}

	public ImageComponent(String path, int x, int y) {
		this(path, x, y, null);
	}

	@Override
	public void dispose() {

	}

	@Override
	protected void draw(Graphics2D g, int x, int y, int width, int hight) {
		g.drawImage(image, getX(), getY(), null);
	}

	@Override
	public String toString() {
		return path;
	}

}
