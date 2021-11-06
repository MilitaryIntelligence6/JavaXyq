package com.mxxy.game.sprite;

import java.awt.Graphics2D;

import com.mxxy.game.utils.SpriteFactory;

/**
 * 坐骑
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class Mount extends AbsSprite {

	/** 坐骑站立 */
	public static final String STATE_MOUNT_STAND = "mountstand";
	/** 坐骑移动 */
	public static final String STATE_MOUNT_WALK = "mountwalk";
	/** 坐骑标识 */
	private String mountCharacter;

	@Override
	public void draw(Graphics2D g, int x, int y) {
		Sprite mont = sprite;
		if (this.mountCharacter.equals("0200")) {
			if (mont != null)
				if (mont.getDirection() == 7) { // 右边
					mont.drawBitmap(g, x - 5, y);
				} else if (mont.getDirection() == 5) {// 左边
					mont.drawBitmap(g, x + 5, y);
				} else if (mont.getDirection() == 0) {
					mont.drawBitmap(g, x - 5, y);
				} else if (mont.getDirection() == 2) {
					mont.drawBitmap(g, x + 5, y);
				} else if (mont.getDirection() == 3) {
					mont.drawBitmap(g, x - 5, y);
				} else if (mont.getDirection() == 4) {
					mont.drawBitmap(g, x, y - 5);
				} else if (mont.getDirection() == 6) {
					mont.drawBitmap(g, x, y + 5);
				} else if (mont.getDirection() == 1) {
					mont.drawBitmap(g, x + 5, y);
				}
		} else {
			if (mont != null)
				mont.drawBitmap(g, x, y);
		}
	}

	@Override
	public Sprite createSprite(Players players) {
		if (players != null) {
			super.sprite = SpriteFactory.loadSprite("res/shape/char/" + players.getPalyVo().getRace() + "/" + this.mountCharacter
					+ "/" + players.getState() + ".tcp");
		}
		return super.sprite;
	}

	public void setMountCharacter(String mountCharacter) {
		this.mountCharacter = mountCharacter;
	}

	public String getMountCharacter() {
		return mountCharacter;
	}
}
