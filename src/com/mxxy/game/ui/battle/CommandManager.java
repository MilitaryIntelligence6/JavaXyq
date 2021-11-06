package com.mxxy.game.ui.battle;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.mxxy.game.event.BattleEvent;
import com.mxxy.game.sprite.Players;
import com.mxxy.game.ui.BattlePanel;

/**
 * 指令管理
 * 
 * @author dell
 */
public class CommandManager {

	private BattlePanel battlePanel;

	private CommandInterpreter interpretor;

	private Queue<Command> cmdQueue;

	public CommandManager(BattlePanel battlePanel) {
		this.battlePanel = battlePanel;
		cmdQueue = new LinkedList<Command>();
		interpretor = new CommandInterpreter(battlePanel);
	}

	/**
	 * 生成NPC的指令
	 */
	protected void turnBegin() {
		List<Players> t1 = battlePanel.getHostileTeam();
		List<Players> t2 = battlePanel.getOwnsideTeam(); // 自己
		for (int i = 0; i < t1.size(); i++) {
			Players elf = t1.get(i);
			Players target = t2.get(0);
			cmdQueue.offer(new Command("attack", elf, target));
		}
	}

	/** 战斗开始 */
	public void turnBattle() {
		battlePanel.fireBattleEvent(new BattleEvent(battlePanel,BattleEvent.BATTLE_WIN));
		battlePanel.getTimerManager().cleanCountDown();
		turnBegin();
		for (Command command : cmdQueue) {
			this.interpretor.exce(command);
		}
		turnEnd();
	}

	public void turnEnd() {
		cmdQueue.clear();
		battlePanel.roundStartNew();
		battlePanel.getTimerManager().countDown();
	}

	/**
	 * 添加指令
	 * 
	 * @param cmd
	 */
	synchronized public void addCmd(Command cmd) {
		cmdQueue.offer(cmd);
	}
}
