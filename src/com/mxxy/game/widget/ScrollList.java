package com.mxxy.game.widget;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import com.mxxy.game.utils.GameColor;

@SuppressWarnings("serial")
public class ScrollList<T> extends JList<T> {

	public ScrollList(AbstractListModel<T> abstractListModel) {
		setModel(abstractListModel);
		setBackground(new GameColor(0, 0, 0, 0));
		setFixedCellHeight(20);
	}

	@Override
	public void paintImmediately(int x, int y, int w, int h) {
	}
}
