package com.mxxy.game.sprite;

import java.awt.Graphics2D;

/**
 * 人物扩展接口，如坐骑，武器
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public interface IPlayersExtend {

	void update(long elapsedTime);

	void draw(Graphics2D g, int x, int y);

	Sprite createSprite(Players players);

	void resetFrames();

	void setDirection(int index);
}
