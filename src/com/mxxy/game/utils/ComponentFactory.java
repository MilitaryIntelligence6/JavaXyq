package com.mxxy.game.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

import com.mxxy.game.resources.Constant;
import com.mxxy.game.widget.GameSlider;
import com.mxxy.game.widget.GameSliderUI;

/**
 * 组件工厂
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class ComponentFactory {

	private static GameColor color = new GameColor(9, 48, 64);

	public static JTextField createTextName() {
		final JTextField text = new JTextField();
		text.setBorder(BorderFactory.createLineBorder(GameColor.decode("#E0F0A8"), 1));
		text.setCaretColor(new GameColor(190, 190, 190));
		text.setForeground(new GameColor(190, 190, 190));
		text.setBackground(color);
		text.setBounds(91, 389, 107, 25);
		return text;
	}

	public static JTextField creatTextField() {
		final JTextField text = new JTextField("   " + Constant.getString("USER_HINT"));
		text.setBorder(null);
		text.setCaretColor(new GameColor(190, 190, 190));
		text.setForeground(new GameColor(190, 190, 190));
		text.setBackground(color);
		text.setBounds(312, 221, 240, 25);
		text.setFont(new Font("system", Font.PLAIN, 14)); // 设置字体样式

		text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 鼠标移进去
				text.setFocusable(true);
				text.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
				text.setForeground(GameColor.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {// 鼠标移出去
				text.setBorder(BorderFactory.createLineBorder(new Color(168, 175, 173), 1));
				text.setForeground(new GameColor(190, 190, 190));
			}

		});

		text.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {

				if (text.getText().length() <= 0)
					text.setText("   " + Constant.getString("USER_HINT"));
			}

			@Override
			public void focusGained(FocusEvent e) {
				String passwordvalue = text.getText();
				if (text.getText().length() <= 0 || passwordvalue.equals("   " + Constant.getString("USER_HINT")))
					text.setText("");
			}
		});
		return text;
	}

	public static JTextField regitsTextField(String hide) {// 创建文本框
		final JTextField text = new JTextField(hide);
		text.setBorder(null);
		text.setCaretColor(new GameColor(190, 190, 190));
		text.setForeground(GameColor.decode("#404040"));
		text.setBackground(color);
		text.setBounds(312, 221, 240, 25);
		text.setFont(new Font("system", Font.PLAIN, 13)); // 设置字体样式
		text.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (text.getText().length() <= 0)
					text.setText(Constant.getString("USER_HINT"));
			}

			@Override
			public void focusGained(FocusEvent e) {
				String passwordvalue = text.getText();
				if (text.getText().length() <= 0 || passwordvalue.equals(Constant.getString("USER_HINT")))
					text.setText("");
			}
		});
		return text;
	}

	public static JPasswordField creatPswd() {// 创建密码框
		final JPasswordField pswd = new JPasswordField("   " + Constant.getString("PASSWOR_HINT"));
		pswd.setBorder(null);
		pswd.setCaretColor(new GameColor(190, 190, 190));
		pswd.setForeground(new GameColor(190, 190, 190));
		pswd.setEchoChar((char) 0);
		pswd.setBackground(color);
		pswd.setFont(new Font("system", Font.PLAIN, 14)); // 设置字体样式
		pswd.setBounds(312, 261, 240, 25);
		pswd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 鼠标移进去
				pswd.setFocusable(true);
				pswd.setBorder(BorderFactory.createLineBorder(GameColor.yellow, 1));
				pswd.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {// 鼠标移出去
				pswd.setBorder(BorderFactory.createLineBorder(new GameColor(168, 175, 173), 1));
				pswd.setForeground(new GameColor(190, 190, 190));
			}
		});

		pswd.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				String passwordvalue = new String(pswd.getPassword());
				if (passwordvalue.length() <= 0 || passwordvalue.equals("   " + Constant.getString("PASSWOR_HINT"))) {
					pswd.setText("");
					pswd.setEchoChar('*');
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				String passwordvalue = new String(pswd.getPassword());
				if (passwordvalue.length() <= 0 || passwordvalue.equals("   " + Constant.getString("PASSWOR_HINT"))) {
					pswd.setText("   " + Constant.getString("PASSWOR_HINT"));
					pswd.setEchoChar((char) 0);
				} else {
					pswd.setEchoChar('*');
				}
			}
		});

		return pswd;
	}

	public static JButton createJButton() {
		@SuppressWarnings("serial")
		JButton button = new JButton() {
			@Override
			public void paintImmediately(int x, int y, int w, int h) {
			}
		};
		button.setIgnoreRepaint(true);
		button.setContentAreaFilled(false);
		button.setFocusable(false);
		button.setToolTipText(null);
		button.setBorder(null);
		return button;
	}

	public static GameSlider createSlider(int min, int max, int value, Image ball1, Image ball2, Image ball3, Image bg1,
			Image bg2, ChangeListener listener, int orientation) {
		GameSlider jslider = new GameSlider();
		GameSliderUI ui = new GameSliderUI(jslider);
		jslider.setOpaque(false);
		jslider.setMaximum(max);
		jslider.setMinimum(min);
		jslider.setValue(value);
		jslider.setOrientation(orientation);
		// ui.setThumbImage(ball1);
		// ui.setThumbOverImage(ball2);
		// ui.setThumbPressedImage(ball3);
		// ui.setBackgroundImages(bg1);
		// ui.setActiveBackImage(bg2);
		jslider.setUI(ui);
		// jslider.addChangeListener(listener);
		return jslider;
	}

	/*
	 * JSlider createSlider(final FloatControl c) { if (c == null) return null;
	 * final JSlider s = new JSlider(0, 1000); final float min = c.getMinimum();
	 * final float max = c.getMaximum(); final float width = max - min; float fval =
	 * c.getValue(); s.setValue((int) ((fval - min) / width * 1000));
	 * java.util.Hashtable labels = new java.util.Hashtable(3); labels.put(new
	 * Integer(0), new JLabel(c.getMinLabel())); labels.put(new Integer(500), new
	 * JLabel(c.getMidLabel())); labels.put(new Integer(1000), new
	 * JLabel(c.getMaxLabel())); s.setLabelTable(labels); s.setPaintLabels(true);
	 * s.addChangeListener(new ChangeListener() { public void
	 * stateChanged(ChangeEvent e) { int i = s.getValue(); float f = min + (i *
	 * width / 1000.0f); c.setValue(f); } }); return s; }
	 */
}
