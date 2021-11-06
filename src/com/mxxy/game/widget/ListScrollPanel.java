package com.mxxy.game.widget;

import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class ListScrollPanel extends JScrollPane implements ListSelectionListener {

	private JList<String> list;

	public ListScrollPanel(int width, int height, JList<String> view) {
		setSize(width, height);
		this.list = view;
		setOpaque(false);
		setBorder(null);
		view.addListSelectionListener(this);
		setViewportView(view);
		JScrollBar jScrollBar = new JScrollBar();
		jScrollBar.setOrientation(JScrollBar.VERTICAL);
		jScrollBar.setUI(new ScrollBarUI());
		jScrollBar.setOpaque(false);
		jScrollBar.setBounds(0, 0, 25, height);
		setVerticalScrollBar(jScrollBar);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollBar.setEnabled(true);
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (listItem != null)
			listItem.getListValue(list.getSelectedIndex(), list.getSelectedValue());
	}

	/**
	 * CallBack
	 * @author dell
	 */
	public interface IScrollPanelListItem {
		void getListValue(int index, String value);
	}

	private IScrollPanelListItem listItem;

	public void setScrollPanelListItem(IScrollPanelListItem listItem) {
		this.listItem = listItem;
	}

	@Override
	public void paintImmediately(int x, int y, int w, int h) {}
}
