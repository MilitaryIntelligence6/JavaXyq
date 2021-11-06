package com.mxxy.game.ui.battle;

import java.awt.Point;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import com.mxxy.game.base.Application;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.sprite.Players;
import com.mxxy.game.sprite.Sprite;
import com.mxxy.game.ui.BattlePanel;
import com.mxxy.game.utils.MP3Player;

/**
 * 指令动作
 * 
 * @author ZAB
 *
 *         2018年6月26日
 */
public class CommandAction {

	private BattlePanel battlePanel;

	public CommandAction(BattlePanel battlePanel) {
		this.battlePanel = battlePanel;
	}
	
	public void attack() {
		
	}
	
	/**
	 * 逃跑
	 * @param player
	 * @param success
	 */
	public void runaway(Players player, boolean success) {
		try {
			RunawayWorker worker = new RunawayWorker(player, success, 2000);
			worker.execute();
			worker.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private class RunawayWorker extends SwingWorker<Object, Object> {
		private Players player;
		private long duration;
		private boolean success;

		public RunawayWorker(Players player, boolean success, long duration) {
			super();
			this.player = player;
			this.duration = duration;
			this.success = success;
		}

		@Override
		protected Object doInBackground() throws Exception {
			int dir = player.getDirection();
			player.setDirection(dir - 2);
			player.setState(Players.STATE_WALK);
			Thread.sleep(1000);
			if (this.success) {
				MP3Player.play("res/music/escape_ok.mp3");
				long interval = 50;
				long t = 0;
				while (t < duration) {
					Thread.sleep(interval);
					// 计算移动量
					long elapsedTime = interval;
					int distance = (int) (2 * Constant.PLAYER_RUNAWAY * elapsedTime);
					int dx = distance; // 向右下逃跑
					int dy = distance;
					if (player.getDirection() == Sprite.DIRECTION_TOP_LEFT) {// 向左上逃跑
						dx = -dx;
						dy = -dy;
					}
					player.moveBy(dx, dy);
					super.publish(new Point(dx, dy));
					t += interval;
					if (playerGoBeyone(player)) {
						Application.application.quitWar();
						break;
					}
				}
			} else {
				battlePanel.getUIHelp().prompt(battlePanel.getComponent(), "运气不济,逃跑失败 #83", 2000);
			}
			player.setState(Players.STATE_STAND);
			player.setDirection(dir);
			return null;
		}

		/**
		 * judge playerLacotion GoBeyone Screen(判断人物是否超出屏幕)
		 * 
		 * @param players
		 * @return
		 */
		private boolean playerGoBeyone(Players players) {
			return player.getX() < 0 || player.getY() < 0 || player.getX() > battlePanel.getWidth() + 28
					|| player.getY() > battlePanel.getHeight() + 28;
		}
	}
}
