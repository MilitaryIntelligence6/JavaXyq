package com.mxxy.game.handler;

import com.mxxy.game.event.BattleEvent;
import com.mxxy.game.listener.IBattleListener;
import com.mxxy.game.utils.UIHelp;

/**
 * 战争回合处理结果
 * 
 * @author dell
 *
 */
final public class BattleRoundHandler implements IBattleListener {

	public UIHelp uiHelp;
	

	public BattleRoundHandler(UIHelp uiHelp) {
		this.uiHelp = uiHelp;
	}

	@Override
	public void battleWin(BattleEvent e) {
		
	}

	@Override
	public void battleDefeated(BattleEvent e) {

	}

	@Override
	public void battleTimeout(BattleEvent e) {

	}

	@Override
	public void battleBreak(BattleEvent e) {
		uiHelp.prompt(null, "战斗结束", 1000);
	}

}
