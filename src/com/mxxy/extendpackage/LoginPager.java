package com.mxxy.extendpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.mxxy.game.base.Panel;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.modler.LoginModler;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.resources.Constant.LoginStatus;
import com.mxxy.game.utils.ComponentFactory;
import com.mxxy.game.was.Toolkit;

/**
 * LoginPager (登录控制器 )
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
final public class LoginPager extends AbstractPanelHandler<LoginModler> {

	private JTextField user_number;
	private JPasswordField cipher;
	private JButton button;

	@Override
	public void init(PanelEvent evt) {
		super.init(evt);
		System.out.println("LoginPager" + panel);
	}

	@Override
	protected void initView() {
		user_number = ComponentFactory.creatTextField();
		cipher = ComponentFactory.creatPswd();
		button = ComponentFactory.createJButton();
		button.registerKeyboardAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkUeser();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		panel.add(button);
		panel.add(user_number, 0);
		panel.add(cipher, 0);
	}

	/**
	 * 上一步
	 * 
	 * @param e
	 */
	public void back(ActionEvent e) {
		Panel oldpanel = uihelp.getPanel(e);
		showHide(oldpanel);
	}

	/**
	 * 下一步
	 * 
	 * @param e
	 */
	public void next(ActionEvent e) {
		if (cipher != null || user_number != null) {
			checkUeser();
		}
	}

	@Override
	protected String setConfigFileName() {
		return "save/logindate.propertise";
	}

	private void checkUeser() {
		char[] password = cipher.getPassword();
		String passwords = new String(password);
		LoginStatus loginStatus = modler.next(propertiesConfigManager, user_number.getText().trim(), passwords);
		switch (loginStatus) {
		case SUCCESS:
			uihelp.prompt(null, Constant.getString("Loding"), 1000);
			Toolkit.sleep(1000);
			Panel selectserver = uihelp.getPanel("SelectServer");
			uihelp.hidePanel(panel);
			uihelp.showPanel(selectserver);
			break;
		case PASSWORDANDUSER_ERR:
			uihelp.prompt(null, Constant.getString("PASSWORDANDUSER_ERR"), 2000);
			break;
		case PASSWORDANDUSER_EMPTY:
			uihelp.prompt(null, Constant.getString("PASSWORDANDUSER_EMPTY"), 2000);
			break;
		}
	}
}
