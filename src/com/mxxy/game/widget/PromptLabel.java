package com.mxxy.game.widget;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.mxxy.game.event.ComponentMove;
import com.mxxy.game.resources.Constant;

/**
 * 提示框
 * @author ZAB
 */
public class PromptLabel extends JLabel {

	public PromptLabel(String text) {
		super(text);
		setBounds(0, 0, 320, 36);
		setFont(Constant.PROMPT_FONT);
		setForeground(Color.YELLOW);
		setBorder(new CompoundBorder(new RoundLineBorder(Color.WHITE, 1, 8, 8), new EmptyBorder(10, 10, 10, 10)));
		setIgnoreRepaint(false);
		setFocusable(false);
		setToolTipText(null);
		ComponentMove componentMove = new ComponentMove();
		this.addMouseListener(componentMove);
		this.addMouseMotionListener(componentMove);
	}

	@Override
	public void paintImmediately(int x, int y, int w, int h) {}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g2d.setColor(Color.BLACK);
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
		g2d.dispose();
		super.paint(g);
	}
}
