package com.mxxy.game.widget;

import javax.swing.JSlider;

@SuppressWarnings("serial")
public class GameSlider extends JSlider {

	public GameSlider() {
		super();
		setDoubleBuffered(true);
	}

	public boolean isRequestFocusEnabled() {
		setValueIsAdjusting(true);
		repaint();
		return super.isRequestFocusEnabled();
	}

	public void setHideThumb(boolean hide) {
		((GameSliderUI) getUI()).setHideThumb(hide);
	}
}
