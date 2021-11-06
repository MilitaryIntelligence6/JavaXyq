package com.mxxy.extendpackage;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.BorderUi;
import com.mxxy.game.widget.Label;

/**
 * EmoticonPanel (小表情)
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
@SuppressWarnings("rawtypes")
final public class Emoticon extends AbstractPanelHandler {

	private Label[] emoticonSymbol;

	@Override
	public void init(PanelEvent evt) {
		super.init(evt);
	}

	@Override
	protected void initView() {
		panel.requestFocus();
		emoticonSymbol = new Label[60];
		initEmoticonSymbol();
	}

	private void initEmoticonSymbol() {
		for (int i = 0; i < emoticonSymbol.length; i++) {
			emoticonSymbol[i] = new Label(SpriteFactory.loadAnimation("res/wzife/emoticons/#" + i + ".was"));
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 10; j++) {
				emoticonSymbol[i * 10 + j].setLocation(10 + j * 44, 0 + i * 44);
				emoticonSymbol[i * 10 + j].setHorizontalAlignment(SwingConstants.CENTER);
				emoticonSymbol[i * 10 + j].setSize(45, 45);
				emoticonSymbol[i * 10 + j].setName(String.valueOf(i * 10 + j));
				emoticonSymbol[i * 10 + j].addMouseListener(this);
				panel.add(emoticonSymbol[i * 10 + j]);
			}
		}
		panel.setBorder(new BorderUi(Color.decode("#808080"), 5, true));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		Label label=(Label) e.getSource();
		System.out.println(label.getName());
		
		uihelp.hidePanel(panel);
	}
}
