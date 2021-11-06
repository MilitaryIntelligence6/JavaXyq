package com.mxxy.game.astar;

import java.util.List;

public class SceneJump {

	private List<SceneJumpBean> SceneJump;

	public List<SceneJumpBean> getSceneJump() {
		return SceneJump;
	}

	public static class SceneJumpBean {
		/**
		 * originId : 1104 goalId : 1092 startPoint : 58 42 endPoint : 59 35
		 */
		private int originId;
		private int goalId;
		private String startPoint;
		private String endPoint;

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
}
