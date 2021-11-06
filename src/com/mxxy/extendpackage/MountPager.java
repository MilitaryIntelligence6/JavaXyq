package com.mxxy.extendpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JList;

import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.modler.MountMolder;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.sprite.Mount;
import com.mxxy.game.sprite.Sprite;
import com.mxxy.game.ui.ContainersPanel;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.ImageComponentButton;
import com.mxxy.game.widget.ListScrollPanel;
import com.mxxy.game.widget.ListScrollPanel.IScrollPanelListItem;

/**
 * MountPager (坐骑)
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
final public class MountPager extends AbstractPanelHandler<MountMolder> implements IScrollPanelListItem {

	private ListScrollPanel listScrollsPanel;

	private Sprite sprite;

	private ArrayList<String> allDir;

	private ImageComponentButton ride;

	private String character;

	private JList<String> list;

	private static int inde;

	private MountContainersPanel mountContainersPanel;

	public void init(PanelEvent evt) {
		super.init(evt);
		character = player.getPalyVo().getCharacter();
		if (Integer.parseInt(character) >= 1 && Integer.parseInt(character) <= 4) {
			character = "1000";
		} else if (Integer.parseInt(character) >= 5 && Integer.parseInt(character) <= 8) {
			character = "1001";
		} else if (Integer.parseInt(character) >= 9 && Integer.parseInt(character) <= 12) {
			character = "1002";
		}
		// if(player.getMont()!=null)
		// sprite=player.getMont();
		allDir = modler.getAllDir("res/shape/char/" + character);
		list = modler.getList(propertiesConfigManager);
		listScrollsPanel = new ListScrollPanel(120, 85, list);
		list.setBounds(0, 0, 100, 100);
		list.setPreferredSize(new Dimension(100, 100));
		list.setSelectedIndex(inde);
		list.setSelectionForeground(Color.yellow);
		listScrollsPanel.setScrollPanelListItem(this);
		listScrollsPanel.setLocation(187, 33);
		mountContainersPanel = new MountContainersPanel(0, 0, 200, 200);
		panel.add(mountContainersPanel, 0);
		panel.add(listScrollsPanel, 0);
	}

	@Override
	protected void initView() {
		ride = findViewById("ride");
		ride.setText(player.getMount()!=null?"下骑" : "骑乘");
	}

	@SuppressWarnings("serial")
	class MountContainersPanel extends ContainersPanel {
		public MountContainersPanel(int x, int y, int width, int height) {
			super(x, y, width, height);
		}

		@Override
		protected void draw(Graphics2D g, long elapsedTime) {
			if (sprite != null && g != null) {
				sprite.drawBitmap(g, 100, 130);
				sprite.setDirection(Sprite.DIRECTION_BOTTOM_RIGHT);
				sprite.update(elapsedTime);
			}
		}

	}

	/**
	 * 坐骑事件
	 * @param e
	 */
	public void ride(ActionEvent e) {
		Mount mount=new Mount();
		mount.setMountCharacter(mountIndex);
		if (sprite != null) {
			player.getPalyVo().setRace(character);
			player.setMount(player.getMount()==null?mount:null);
			ride.setText(player.getMount()!=null?"下骑" : "骑乘");
		}
	}

	/** 坐骑编号 */
	private String mountIndex;

	@Override
	public void getListValue(int index, String value) {
		inde = index;
		// TODO 需要判断MAC 系统 因为在MAC里面是/
		String string = allDir.get((int) index);
		String spritePath = string.substring(
				string.lastIndexOf(Constant.currentDir.charAt(Constant.currentDir.length() - 1)) + 1, string.length())
				+ "/mountstand.tcp";
		
		
		System.out.println(spritePath);
		mountIndex = string.substring(string.lastIndexOf(Constant.flie_spance) + 1, string.length());
		sprite = SpriteFactory.loadSprite(spritePath.replace("\\", "/"));
	}

	@Override
	public void dispose(PanelEvent evt) {
		super.dispose(evt);
		panel.remove(mountContainersPanel);
		panel.remove(listScrollsPanel);
	}

	@Override
	protected String setConfigFileName() {
		return "value/resources.properties";
	}
}
