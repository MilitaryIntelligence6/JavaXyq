package com.mxxy.extendpackage;

import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.SpriteImage;

/**
 * WordMapPanel (世界地图)
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
final public class WordMap extends AbstractPanelHandler {

	@Override
	protected void initView() {
		SpriteImage loadImage = new SpriteImage(SpriteFactory.loadImage("res/smap/world.jpg"));
		panel.setBounds(0, 0, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
		panel.setSmapImage(loadImage);
	}

	@Override
	public void init(PanelEvent evt) {
		super.init(evt);
	}
}
