package com.mxxy.game.xml;

import com.mxxy.game.base.Panel;

public interface IGameBuilder {
	/**
	 * 实例化JPanel
	 * 
	 * @param id
	 * @param res
	 * @return
	 */
	Panel createPanel(String id, String res);
}
