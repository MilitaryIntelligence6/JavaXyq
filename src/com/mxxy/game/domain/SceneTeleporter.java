package com.mxxy.game.domain;

/**
 * 地图传送点
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class SceneTeleporter {
	private int originId;
	private int goalId;
	private String startPoint;
	private String endPoint;

	/**
	 * 
	 * @param originId
	 *            //起始场景
	 * @param goalId//目标场景
	 * @param startPoint
	 * @param endPoint
	 * @param description
	 */
	public SceneTeleporter(int originId, int goalId, String startPoint, String endPoint) {
		this.originId = originId;
		this.goalId = goalId;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public int getOriginId() {
		return originId;
	}

	public void setOriginId(int originId) {
		this.originId = originId;
	}

	public int getGoalId() {
		return goalId;
	}

	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
}
