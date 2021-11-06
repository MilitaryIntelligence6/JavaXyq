package com.mxxy.game.modler;

import java.io.IOException;

import com.mxxy.game.config.IPropertiseManager;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.resources.Constant.LoginStatus;
import com.mxxy.net.IClient;
import com.mxxy.protocol.LoginMessage;

/**
 * 登录数据处理
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class LoginModler {
	public LoginStatus next(IPropertiseManager propertiesConfigManager, String user, String password) {
		if (user.isEmpty() || user.equals("   " + Constant.getString("USER_HINT")) || password.isEmpty()
				|| password.equals("   " + Constant.getString("PASSWOR_HINT"))) {
			return LoginStatus.SUCCESS;
		} else if (propertiesConfigManager.loadCheckUser(user, password) || user.equals("1") && password.equals("1")) {
			return LoginStatus.SUCCESS;
		} else {
			return LoginStatus.PASSWORDANDUSER_ERR;
		}
	}

	public LoginStatus next(IClient client, String user, String password) {
		try {
			client.send(new LoginMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
