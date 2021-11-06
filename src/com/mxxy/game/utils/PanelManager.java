package com.mxxy.game.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.mxxy.game.base.Panel;
import com.mxxy.game.xml.GameBuild;
import com.mxxy.game.xml.IGameBuilder;

/**
 * 游戏面板管理
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class PanelManager {

	/**
	 * 存储每个游戏功能面板
	 */
	private static Map<String, Panel> panelMap = new HashMap<String, Panel>();

	private static Map<String, String> fileNameMap = new HashMap<String, String>();

	private static IGameBuilder iGameBuilder = new GameBuild();

	public static Panel getPanel(String string) {
		Panel panel = panelMap.get(string);
		if (panel == null) {
			panel = iGameBuilder.createPanel(string, fileNameMap.get(string));
			panelMap.put(string, panel);
		}
		return panel;
	}

	/**
	 * 隐藏面板
	 * 
	 * @return
	 */
	public static void dispose(String id, Panel dlg) {
		if (id == null && dlg != null) { // 如果ID为null就根据Panel实例删除
			Set<Entry<String, Panel>> entres = panelMap.entrySet();
			for (Entry<String, Panel> entry : entres) {
				if (dlg.equals(entry.getValue())) {
					id = entry.getKey();
					break;
				}
			}
		}
		panelMap.remove(id);
	}

	public static void putFileNameMap(String id, String filename) {
		if (StringUtils.isBlank(id) && StringUtils.isBlank(filename)) {
			return;
		}
		fileNameMap.put(id, filename);
	}
}
