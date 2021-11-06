package com.mxxy.game.listener;

import java.util.EventListener;

import com.mxxy.game.event.BattleEvent;

public interface IBattleListener extends EventListener {
	/**
	 * 战斗胜利
	 * @param e
	 */
	void battleWin(BattleEvent e);
	/**
	 * 战斗失败
	 * @param e
	 */
	void battleDefeated(BattleEvent e);
	/**
	 * 战斗超时
	 * @param e
	 */
	void battleTimeout(BattleEvent e);
	/**
	 * 战斗被打断
	 * @param e
	 */
	void battleBreak(BattleEvent e);
	
}