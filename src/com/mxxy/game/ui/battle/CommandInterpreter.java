package com.mxxy.game.ui.battle;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.mxxy.game.modler.MagicModle.MagicConfig;
import com.mxxy.game.sprite.Players;
import com.mxxy.game.sprite.Sprite;
import com.mxxy.game.ui.BattlePanel;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.utils.StringUtils;
import com.mxxy.game.was.Toolkit;

public class CommandInterpreter {

	public final static String MAGICINFIG_TAG = MagicConfig.class.getSimpleName();

	private BattlePanel battlePanel;

	public CommandInterpreter(BattlePanel battlePanel) {
		this.battlePanel = battlePanel;
	}

	public void exce(Command command) {
		battlePanel.hidePanel();
		this.invokeMethod(command.getCmd(), command);
	}

	public int attackCount = 0;

	public void attack(Command command) {
		Players source = command.getSource();
		Players target = command.getTarget();
		Point location = source.getLocation();
		MagicConfig magicConfig = (MagicConfig) command.get(MAGICINFIG_TAG);
		if (magicConfig == null) {
			battlePanel.setBattleMessage("#Y" + source.getPalyVo().getName() + "进行了攻击#18");
		}

		Sprite s = SpriteFactory.loadSprite("res/shape/char/0001/attack.tcp");
		int dx = s.getWidth() - s.getCenterX();
		int dy = s.getHeight() - s.getCenterY();
		if (target.getX() > source.getX()) {
			dx = -dx;
			dy = -dy;
		}
		battlePanel.rush(source, target.getX() + dx, target.getY() + dy, Players.STATE_RUSHA);

		source.playOnce(Players.STATE_ATTACK);

		if (magicConfig != null) {
			target.playEffect(magicConfig.getName(), false, source.getPalyVo().getRace());
		}

		target.playOnce(Players.STATE_HIT);
		battlePanel.showPoints(target, -99);
		source.writFor();
		target.setState(Players.STATE_STAND);
		battlePanel.hidePoints(target);

		if (magicConfig != null) {
			if (magicConfig.getMagicId() == MagicConfig.REPEATMAGINC) {
				if (attackCount == magicConfig.getRepeatCount() - 1) {
					battlePanel.rush(source, 530, 400, Players.STATE_RUSHB);
					magicConfig = null;
					attackCount = -1;
				}
				attackCount++;
			} else {
				battlePanel.rush(source, location.x, location.y, Players.STATE_RUSHB);
			}
		} else {
			battlePanel.rush(source, location.x, location.y, Players.STATE_RUSHB);
		}

		String state = StringUtils.isNotBlank(source.getPalyVo().getRace())?Players.STATE_WRITBUTTLE: Players.STATE_STAND;
		source.setState(state);
		battlePanel.setBattleMessage("");
	}

	/**
	 * 施法指令
	 */
	public void magic(Command command) {
		Players source = command.getSource();
		Players target = command.getTarget();
		MagicConfig magicConfig = (MagicConfig) command.get(MAGICINFIG_TAG);
		battlePanel.setBattleMessage("#Y" + source.getPalyVo().getName() + "施法法术 — " + magicConfig.getName());
		switch (magicConfig.getMagicId()) {
		case MagicConfig.BIGMAGIC:
			source.playOnce(Players.STATE_MAGIC);
			groupMagic(source, target);
			break;
		case MagicConfig.SINGLEGROUPMAGIC:
			source.playOnce(Players.STATE_MAGIC);
			List<Players> hostileTeam = battlePanel.getHostileTeam();
			List<Players> magicHostileTeam = battlePanel.getMagicHostileTeam(target, hostileTeam);
			for (int i = magicHostileTeam.size() - 1; i >= 0; i--) {
				singleMagic(magicHostileTeam.get(i), magicConfig.getName(), source.getPalyVo().getRace());
			}
			break;
		case MagicConfig.SINGLEMAGIC:
			source.playOnce(Players.STATE_MAGIC);
			singleMagic(target, magicConfig.getName(), source.getPalyVo().getRace());
			break;
		case MagicConfig.REPEATMAGINC:
			for (int i = 0; i < magicConfig.getRepeatCount(); i++) {
				Command command2 = new Command(Players.STATE_ATTACK, source, target);
				command2.add(MAGICINFIG_TAG, magicConfig);
				attack(command2);
			}
			break;
		case MagicConfig.GROUPATTACK:
			List<Players> hostileTeams = battlePanel.getHostileTeam();
			List<Players> magicHostileTeams = battlePanel.getMagicHostileTeam(target, hostileTeams);
			for (int i = magicHostileTeams.size() - 1; i >= 0; i--) {
				Command command2 = new Command(Players.STATE_ATTACK, source, magicHostileTeams.get(i));
				command2.add(MAGICINFIG_TAG, magicConfig);
				attack(command2);
			}
			break;
		}
		source.writFor();
		String state = Players.STATE_WRITBUTTLE;
		source.setState(state);
		battlePanel.setBattleMessage("");
	}

	/**
	 * 群法
	 * 
	 * @param target
	 *            目标敌人
	 */
	public void groupMagic(Players source, Players target) {
		List<Players> hostileTeam = battlePanel.getHostileTeam();
		List<Players> magic = battlePanel.getMagicHostileTeam(target, hostileTeam);
		source.writFor();
		battlePanel.playOnceMusic(true);
		for (Players players : magic) {
			players.playOnce(Players.STATE_HIT);
			battlePanel.showPoints(players, -10);
		}
		Toolkit.sleep(800);
		// for (Players players : magic) {
		// players.playOnce(Players.STATE_DIE);
		// }
		for (Players players : magic) {
			players.setState(Players.STATE_STAND);
			battlePanel.hidePoints(players);
		}
	}

	/**
	 * 单法
	 * 
	 * @param target
	 * @param string
	 */
	public void singleMagic(Players target, String magicName, String string) {
		new Thread() {
			public void run() {
				target.playOnce(Players.STATE_HIT);
				battlePanel.showPoints(target, -10);
				target.playEffect(magicName, true, string);
				target.waitForEffect();
				target.setState(Players.STATE_STAND);
				battlePanel.hidePoints(target);
			};
		}.start();
	}

	public Object invokeMethod(String mName, Object arg) {
		Method method;
		try {
			method = this.getClass().getDeclaredMethod(mName, arg.getClass());
			return method.invoke(this, arg);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
