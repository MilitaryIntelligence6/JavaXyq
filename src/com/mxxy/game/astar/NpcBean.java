package com.mxxy.game.astar;

import java.util.List;

public class NpcBean {
	public List<NpcListbean> NpcList;

	public static class NpcListbean {
		public int sceneId;
		public String characterId;
		public String name;
		public int sceneX;
		public int sceneY;
		public String state;
		public String describe;
		public int direction;

		@Override
		public String toString() {
			return "NpcListbean [sceneId=" + sceneId + ", characterId=" + characterId + ", name=" + name + ", sceneX="
					+ sceneX + ", sceneY=" + sceneY + ", state=" + state + ", describe=" + describe + ", direction="
					+ direction + "]";
		}
	}
}
