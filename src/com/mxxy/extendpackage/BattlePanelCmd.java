package com.mxxy.extendpackage;

import java.awt.event.ActionEvent;

import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.ui.BattlePanel;

/**
 * 
 * @author ZAB
 * 战斗指令
 * 2018年5月29日
 */
final public class BattlePanelCmd extends AbstractPanelHandler {

	private BattlePanel battlepanel;
	
	@Override
	public void init(PanelEvent evt) {
		super.init(evt);
		if(super.iWindows.getPanel() instanceof BattlePanel) {
			this.battlepanel = (BattlePanel) super.iWindows.getPanel();
		}
	}

	@Override
	protected void initView() {

	}
	/**
	 * 法术
	 * @param e
	 */
	public void warmagic(ActionEvent e) {
		battlepanel.selectWarmagic();
	}
	/**
	 * 道具
	 */
	public void waritem(ActionEvent e) {
		battlepanel.selectProp();
	}
	/**
	 * 防御
	 */
	public void wardefend(ActionEvent e) {
		
	}
	/**
	 * 召唤
	 */
	public void warcall(ActionEvent e) {
		
	}
	/**
	 * 保护
	 */
	public void warprotect(ActionEvent e) {
		
	}
	/**
	 * 捕捉
	 */
	public void warcatch(ActionEvent e) {
		
	}
	/**
	 * 逃跑
	 */
	public void warrunaway(ActionEvent e) {
		battlepanel.runaway(player, true);
	}
}
