package com.mxxy.extendpackage;

import java.awt.event.MouseEvent;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.modler.MagicModle;
import com.mxxy.game.modler.MagicModle.MagicConfig;
import com.mxxy.game.sprite.Cursor;
import com.mxxy.game.ui.BattlePanel;
import com.mxxy.game.utils.GameColor;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.ImageComponentButton;
import com.mxxy.game.widget.Label;
import com.mxxy.game.widget.RoundLineBorder;

/**
 * 战斗法术
 * 
 * @author dell
 *
 */
final public class BattleWarmagic extends AbstractPanelHandler<MagicModle> {

	private ImageComponentButton[] magicArrays;

	private BattlePanel battlepanel;

	private Label defaultMagic;

	@Override
	public void init(PanelEvent evt) {
		super.init(evt);
		if (super.iWindows.getPanel() instanceof BattlePanel) {
			this.battlepanel = (BattlePanel) super.iWindows.getPanel();
		}
		defaultMagic = findViewById("defaultMagic");
	}

	@Override
	protected void initView() {
		MagicConfig[] schoolMagic = modler.getSchoolMagic(player.getPalyVo().getRace());
		magicArrays = new ImageComponentButton[schoolMagic.length];
		for (int i = 0; i < magicArrays.length; i++) {
			magicArrays[i] = new ImageComponentButton();
			magicArrays[i].init(SpriteFactory
					.loadSprite("res/magic/" + player.getPalyVo().getRace() + "/" + schoolMagic[i].getName() + "icon.tcp"));
			magicArrays[i].setSize(45, 45);
			magicArrays[i].setMagicConfig(new MagicModle().new MagicConfig(schoolMagic[i].getName(),
					schoolMagic[i].getMagicId(), schoolMagic[i].getRace(), schoolMagic[i].getRepeatCount()));
			magicArrays[i].addMouseListener(this);
		}

		try {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					magicArrays[i * 2 + j].setLocation(28 + 88 * j, 30 + i * magicArrays[0].getWidth() - 1);
					panel.add(magicArrays[i * 2 + j], 0);
				}
			}
		} catch (Exception e) {}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		battlepanel.setGameCursor(Cursor.SELECT_CURSOR);
		ImageComponentButton source = (ImageComponentButton) e.getSource();
		defaultMagic.setText(source.getMagicConfig().getName());
		battlepanel.setSelectMagic(source.getMagicConfig());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		ImageComponentButton imageComponentButton = (ImageComponentButton) e.getSource();
		imageComponentButton.setBorder(
				new CompoundBorder(new RoundLineBorder(GameColor.red, 1, 0, 0), new EmptyBorder(10, 10, 10, 10)));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		ImageComponentButton imageComponentButton = (ImageComponentButton) e.getSource();
		imageComponentButton.setBorder(null);
	}

}
