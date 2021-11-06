package com.mxxy.game.config;

import java.util.List;

import com.mxxy.game.domain.Profile;

/**
 * 存档管理
 * 
 * @author dell
 */
public interface IProfileManager {
	/**
	 * 保存
	 * 
	 * @param filename
	 */
	void save(Profile filename);

	/**
	 * 加载存档
	 * 
	 * @param filename
	 */
	Profile load(String filename);

	/**
	 * 删除存档
	 * 
	 * @param filename
	 */
	void romeve(String filename);

	/**
	 * 获取存档List
	 * 
	 * @return
	 */
	List<Profile> listProfiles();
}
