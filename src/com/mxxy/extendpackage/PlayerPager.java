package com.mxxy.extendpackage;

import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.ImageComponentButton;

/**
 * PlayerPager (道具人物头像)
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
final public class PlayerPager extends AbstractPanelHandler {

	private ImageComponentButton head;

	@Override
	public void init(PanelEvent evt) {
		super.init(evt);
	}

	@Override
	protected void initView() {
		head = findViewById("head");
		head.init(SpriteFactory.loadSprite("res/wzife/photo/facebig/" + player.getPalyVo().getCharacter() + ".tcp"));
	}
}
