package com.mxxy.game.modler;

import com.mxxy.game.config.IPropertiseManager;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.resources.Constant.RegistStatus;

/**
 * 注册数据处理
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class RegistUserModler {

    public RegistStatus regitst(IPropertiseManager configManager, String user, String password, String check_password,
                                String verification) {
        return isPassword(configManager, user, password, check_password);
    }

    private RegistStatus isPassword(IPropertiseManager configManager, String u_name, String u_pwd, String u_pwd_ag) {
        if (!u_name.equals(Constant.getString("USER_HINT"))) {
            if (Constant.isEmail(u_name)) {
                if (u_pwd.equals(u_pwd_ag)) {
                    if (u_pwd.length() != 0) {
                        configManager.put(u_name, u_pwd_ag);
                        configManager.saveConfig();
                        return RegistStatus.SUCCESS;
                    } else {
                        return RegistStatus.PASSWORD_ISEMPTY;
                    }
                } else {
                    return RegistStatus.CHECK_PASSWORD;
                }
            } else {
                return RegistStatus.USER_PATTERN_ERR;
            }
        } else {
            return RegistStatus.USER_ISEMPTY;
        }
    }
}
