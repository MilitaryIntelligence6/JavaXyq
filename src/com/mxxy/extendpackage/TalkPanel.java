package com.mxxy.extendpackage;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JPanel;

import com.mxxy.extendpackage.npc.INPCExtend;
import com.mxxy.game.base.Panel;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.event.PlayerEvent;
import com.mxxy.game.event.PlayerListenerAdapter;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.sprite.Players;
import com.mxxy.game.sprite.Sprite;
import com.mxxy.game.ui.ContainersPanel;
import com.mxxy.game.utils.PanelManager;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.utils.UIHelp;
import com.mxxy.game.was.Toolkit;
import com.mxxy.game.widget.RichLabel;

final public class TalkPanel extends AbstractPanelHandler {

	private String character;

	private INPCExtend npcEctend;
	
	private Sprite head;

	@Override
	protected void initView() {
		character = (String) panel.getAttributes("charcater");
		head = SpriteFactory.findPhoto(character);
		try {
			Class<?> class1 = Class.forName("com.mxxy.extendpackage.npc.n" + character);
			npcEctend = (INPCExtend) class1.newInstance();
			this.initTalk(npcEctend.dialogue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public JPanel getContainersPanel() {
		return new ContainersPanel(0, 0, panel.getWidth(), panel.getHeight()) {
			@Override
			protected void draw(Graphics2D g, long elapsedTime) {
				Shape oldclip = g.getClip();
				g.translate(-panel.getX(), -panel.getY());
				g.setClip(0, 0, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
				head.drawBitmap(g, panel.getX() + 10, panel.getY() - head.getHeight());
				g.translate(panel.getX(), panel.getY());
				g.setClip(oldclip);
			}
		};
	}

	public class TalkAction extends PlayerListenerAdapter {
		@Override
		public void talk(PlayerEvent evt) {
			Players player = evt.getPlayer();
			showTalkPanel(evt, player.getPalyVo().getCharacter());
		}

		public void showTalkPanel(PlayerEvent evt, String charcater) {
			Panel talkPanel = PanelManager.getPanel(TalkPanel.class.getSimpleName());
			talkPanel.setAttributes("charcater", charcater);
			UIHelp uiHelp = (UIHelp) evt.getAttributes("UIHELP");
			uiHelp.showPanel(talkPanel);
		}
	}
	
	
	@Override
	public void dispose(PanelEvent evt) {
		super.dispose(evt);
		
		if (lblText != null) {
			panel.remove(lblText);
		}
		
		
	}

	private RichLabel lblText;
	public void initTalk(String text) {
		lblText = Toolkit.getInstance().createRichLabel(16, 30, panel.getWidth() - 20, panel.getHeight(),text);
		panel.add(lblText, 0);
	}

	protected Object invokeMethods(String mName, Object arg) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, SecurityException, NoSuchMethodException {
		Method m = arg.getClass().getMethod(mName, arg.getClass());
		return m.invoke(arg);
	}
}
