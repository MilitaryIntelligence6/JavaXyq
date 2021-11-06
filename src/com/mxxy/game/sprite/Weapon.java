package com.mxxy.game.sprite;

import java.awt.Graphics2D;
import java.io.Serializable;

import com.mxxy.game.utils.SpriteFactory;

/**
 * 武器
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class Weapon extends AbsSprite implements Serializable {

	/*** 武器编号 */
	private String WeaponIndex;

	@Override
	public void draw(Graphics2D g, int x, int y) {
		if (super.sprite != null)
			super.sprite.drawBitmap(g, x, y);
	}

	@Override
	public Sprite createSprite(Players players) {
		if (players != null && players.getState() != null) {
			super.sprite = SpriteFactory.loadSprite(
					"res/shape/char/" + players.getPalyVo().getCharacter() + "/" + WeaponIndex + "/" + players.getState() + ".tcp");
		}
		return super.sprite;
	}

	public void setWeaponIndex(String weaponIndex) {
		this.WeaponIndex = weaponIndex;
	}

	public String getWeaponIndex() {
		return WeaponIndex;
	}
}
