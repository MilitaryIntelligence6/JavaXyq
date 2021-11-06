package com.mxxy.game.ui;

import javax.swing.ImageIcon;
import javax.swing.JProgressBar;

import com.mxxy.game.base.DrawPaneImp;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.widget.Label;

/**
 * 加载面板
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
@SuppressWarnings("serial")
public class LoadingPanel extends DrawPaneImp {
	private ImageIcon load_icon;
	private Label label;
	private JProgressBar jProgressBar;

	public LoadingPanel(int width, int height) {
		super.setScreenSize(width, height);
	}

	@Override
	public void init() {
		stopDraw();
		load_icon = new ImageIcon("res/componentsRes/loads.png");
		label = new Label(null, load_icon, 0);
		label.setBounds(0, 0, load_icon.getIconWidth(), load_icon.getIconHeight());
		jProgressBar = new JProgressBar();
		jProgressBar.setBounds(0, load_icon.getIconHeight(), load_icon.getIconWidth(), 13);
		jProgressBar.setFont(Constant.PROMPT_FONT);
//		 jProgressBar.setStringPainted(true);
//		 jProgressBar.setIndeterminate(true);
		jProgressBar.setBorderPainted(true);
		add(jProgressBar);
		add(label, 0);
	}

	public JProgressBar getjProgressBar() {
		return jProgressBar;
	}
}
