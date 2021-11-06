package com.mxxy.game.domain;

import java.awt.Point;
import java.awt.Rectangle;

import com.mxxy.game.sprite.Sprite;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.utils.StringUtils;

/**
 * 触发跳转
 * 
 * @author ZAB
 */
public class JumpTrigger {

	private SceneTeleporter sceneTeleporter;

	private Rectangle bounds; // 矩形范围

	private Sprite sprite;

	private Point startPoint;

	private Point endPoint;

	public JumpTrigger(SceneTeleporter sceneTeleporter) {
		this.sceneTeleporter = sceneTeleporter;
		this.startPoint = parsePoint(sceneTeleporter.getStartPoint());
		this.endPoint = parsePoint(sceneTeleporter.getEndPoint());
		this.bounds = new Rectangle(startPoint.x - 2, startPoint.y - 2, 4, 4);
	}

	private static Point parsePoint(String str) {
		String[] strs = StringUtils.splits(str, " ");
		return new Point(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]));
	}

	/**
	 * 界面跳转图片
	 * 
	 * @return
	 */
	public Sprite getSprite() {
		if (sprite == null) {
			sprite = SpriteFactory.loadSprite("res/magic/jump.tcp");
		}
		return sprite;
	}

	/**
	 * 判断是否碰撞矩形
	 * 
	 * @param p
	 * @return
	 */
	public boolean hit(Point p) {
		return this.bounds.contains(p);
	}

	/**
	 * 获取到位置
	 * 
	 * @return
	 */
	public Point getLocation() {
		return new Point(bounds.x, bounds.y);
	}

	public SceneTeleporter getSceneTeleporter() {
		return sceneTeleporter;
	}

	public void setSceneTeleporter(SceneTeleporter sceneTeleporter) {
		this.sceneTeleporter = sceneTeleporter;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public void setSprite(Sprite s) {
		this.sprite = s;
	}
}
