package com.mxxy.game.config;

public class MapConfig implements IConfig {

	private String id; // 地图id

	private String name;// 地图名字

	private String path;// 地图路径

	private String music;// 地图音乐

	public MapConfig(String id, String name, String path) {
		this.id = id;
		this.name = name;
		this.path = path;
	}

	public MapConfig(String id, String name, String path, String music) {
		this(id, name, path);
		this.music = music;
	}

	public String getName() {
		return this.name;
	}

	public String getPath() {
		return this.path;
	}

	public String getType() {
		return "map";
	}

	public String getId() {
		return this.id;
	}

	public String toString() {
		return "MapConfig{" + this.id + "," + this.name + "," + this.path + "}";
	}

	public String getMusic() {
		return this.music;
	}
}