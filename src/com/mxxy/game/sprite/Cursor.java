package com.mxxy.game.sprite;

import java.awt.Graphics2D;
import java.awt.Point;

import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.SpriteImage;

/**
 * 鼠标样式
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class Cursor {
	public static final String DEFAULT_CURSOR = "默认";
	public static final String TEXT_CURSOR = "文本";
	public static final String FORBID_CURSOR = "禁止";
	public static final String SELECT_CURSOR = "施法";
	public static final String GIVE_CURSOR = "给予";
	public static final String ATTACK_CURSOR = "攻击";
	public static final String CATCH_CURSOR = "捕捉";
	public static final String FRIENDS_CURSOR = "好友";

	private SpriteImage pointer;

	private SpriteImage effect; // 水波纹图片

	private int x;

	private int y;

	/** 点击的场景坐标 */
	private int clickX;

	private int clickY;

	/** x偏移量(相对于clickX,为了精确显示点击的效果) */
	private int offsetX;

	private int offsetY;

	private String type;

	public Cursor(String type, boolean effect) {
		this.type = type;
		this.pointer = new SpriteImage(SpriteFactory.loadSprite("res/wzife/cursor/" + type + ".tcp"));
		if (effect) {
			this.effect = new SpriteImage(SpriteFactory.loadSprite("res/wzife/cursor/水波.tcp"));
			this.effect.setVisible(false);
		}
	}

	public SpriteImage getPointer() {
		return pointer;
	}

	public SpriteImage getEffect() {
		return effect;
	}

	public void setClick(int x, int y) {
		this.clickX = x;
		this.clickY = y;
		this.effect.setVisible(true);
	}

	public int getClickX() {
		return clickX;
	}

	public int getClickY() {
		return clickY;
	}

	public Point getClickPosition() {
		return new Point(this.clickX, this.clickY);
	}

	public void setOffset(int x, int y) {
		this.offsetX = x;
		this.offsetY = y;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * 设置指针在屏幕的位置
	 * 
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics2D g) {
		this.pointer.drawBitmap(g, x, y);
	}

	public void update(long elapsedTime) {
		this.pointer.update(elapsedTime);
	}

	public String getType() {
		return type;
	}
}
