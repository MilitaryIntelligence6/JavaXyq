package com.mxxy.game.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mxxy.game.event.BaseEvent;
import com.mxxy.game.event.PanelEvent;

abstract public class AbstractBaseEventListener<T extends ISetOnListener<?>> extends MouseAdapter
		implements KeyListener, /** 键盘事件 */
		MouseWheelListener, /** 鼠标滚轮 */
		MouseListener, /** 鼠标 */
		ActionListener, /** 点击事件 */
		FocusListener, /** 焦点事件 */
		IPanelListener, /** 面板事件 */
		ItemListener, 
		ChangeListener{
	protected T mPanel;

	public AbstractBaseEventListener() {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	@Override
	public void focusGained(FocusEvent arg0) {
	}

	@Override
	public void focusLost(FocusEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void init(PanelEvent evt) {
	}

	@Override
	public void dispose(PanelEvent evt) {
	}

	@Override
	public void update(PanelEvent evt) {
	}

	@Override
	public void close(ActionEvent evt) {
	}

	@Override
	public void help(BaseEvent evt) {
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
	}

	@Override
	public void stateChanged(ChangeEvent e) {
	}
}