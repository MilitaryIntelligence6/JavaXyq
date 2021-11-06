package com.mxxy.extendpackage;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import com.mxxy.game.base.Panel;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.modler.PlayerAttributeModler;
import com.mxxy.game.widget.Label;

/**
 * PlayerAttribute (人物属性)
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
final public class PlayerAttribute extends AbstractPanelHandler<PlayerAttributeModler> {

	@Override
	public void init(PanelEvent evt) {
		super.init(evt);
	}

	@Override
	protected void initView() {
		initPlayerAttribute(panel);
	}

	/**
	 * 打开称谓面板
	 * 
	 * @param e
	 */
	Panel appllation;

	public void OpenPlayerDescribe(ActionEvent e) {
		Panel appllation = uihelp.getPanel(e);
		showOrHide(appllation);
	}

	@Override
	public void dispose(PanelEvent evt) {
		super.dispose(evt);
		uihelp.hidePanel(appllation);
	}
	
	public void openSkill(ActionEvent e){
		Panel oldpanel = uihelp.getPanel(e);
		showOrHide(oldpanel);
	}

	/**
	 * TODO
	 * 
	 * @param panel
	 */
	public void initPlayerAttribute(Panel panel) {
		Component[] components = panel.getComponents();
		List<Label> labels = new ArrayList<Label>();
		for (Component c : components) {
			if (c instanceof Label) {
				labels.add((Label) c);
			}
		}
	}
}
