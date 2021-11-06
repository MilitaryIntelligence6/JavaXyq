package com.mxxy.extendpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JList;

import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.modler.AppellationMolder;
import com.mxxy.game.widget.Label;
import com.mxxy.game.widget.ListScrollPanel;
import com.mxxy.game.widget.ListScrollPanel.IScrollPanelListItem;

/**
 * Appellation (称谓)
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
final public class Appellation extends AbstractPanelHandler<AppellationMolder> implements IScrollPanelListItem {

	private ListScrollPanel listScrollPanel;

	private Label label;

	public String currentDescribe;

	@Override
	protected void initView() {
		JList<String> list = modler.getList();
		listScrollPanel = new ListScrollPanel(205, 105, list);
		listScrollPanel.setScrollPanelListItem(this);
		list.setBounds(0, 20, 180, 105);
		list.setPreferredSize(new Dimension(180, 110));
		list.setSelectionForeground(Color.yellow);
		listScrollPanel.setLocation(25, 100);
		currentDescribe = player.getPalyVo().getDescribe();
		panel.add(listScrollPanel, 0);
		label = findViewById("currentAppellation");
		label.setText(player.getPalyVo().getDescribe());
		label.setForeground(Color.BLACK);
	}

	public void changes(ActionEvent e) {
		if (label.getText().length() > 0 && !label.getText().equals(currentDescribe))
			player.getPalyVo().setDescribe(label.getText().trim());
	}

	@Override
	public void getListValue(int index, String value) {
		label.setText(value);
	}
}
