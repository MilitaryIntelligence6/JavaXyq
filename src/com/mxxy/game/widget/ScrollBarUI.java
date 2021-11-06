package com.mxxy.game.widget;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

import com.mxxy.game.utils.GameColor;
import com.mxxy.game.utils.SpriteFactory;

public class ScrollBarUI extends BasicScrollBarUI {

	@Override
	protected void configureScrollBarColors() {
		Color color = new GameColor(0, 0, 0, 0);
		super.trackColor = color;
	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		super.paintTrack(g, c, trackBounds);
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		g.translate(thumbBounds.x, thumbBounds.y);
		g.setColor(GameColor.black);
		g.drawRoundRect(0, 0, 15, thumbBounds.height - 1, 10, 5);
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.addRenderingHints(rh);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f)); // 透明效果 0-1f透明度
		g.setColor(GameColor.decode("#A8B8C8"));
		g2.fillRoundRect(0, 0, 15, thumbBounds.height - 1, 10, 5);
	}

	@Override
	protected JButton createIncreaseButton(int orientation) {
		ImageComponentButton button = new ImageComponentButton(SpriteFactory.loadSprite("res/wzife/button/down.tcp"));
		return button;
	}

	@Override
	protected JButton createDecreaseButton(int orientation) {
		ImageComponentButton button = new ImageComponentButton(SpriteFactory.loadSprite("res/wzife/button/up.tcp"));
		return button;
	}
}