package com.mxxy.game.widget;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class ListScrollPanel extends JScrollPane implements ListSelectionListener {

    private JList<String> list;
    private IScrollPanelListItem listItem;

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
        if (listItem != null) {
            listItem.getListValue(list.getSelectedIndex(), list.getSelectedValue());
        }
    }

    public void setScrollPanelListItem(IScrollPanelListItem listItem) {
        this.listItem = listItem;
    }

    @Override
    public void paintImmediately(int x, int y, int w, int h) {
    }

    /**
     * CallBack
     *
     * @author dell
     */
    public interface IScrollPanelListItem {
        void getListValue(int index, String value);
    }
}
