package com.mxxy.extendpackage;

import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.modler.RegistUserModler;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.resources.Constant.RegistStatus;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * RegistUser (注册用户)
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
final public class RegistUser extends AbstractPanelHandler<RegistUserModler> {

    private JTextField regist_user;
    private JTextField regist_password;
    private JTextField check_password;
    private JTextField check_num;

    @Override
    public void init(PanelEvent evt) {
        super.init(evt);
    }

    @Override
    protected void initView() {
        regist_user = findViewById("regist_user");
        regist_password = findViewById("regist_password");
        check_password = findViewById("check_password");
        check_num = findViewById("check_num");
    }

    @Override
    public void dispose(PanelEvent evt) {
        uihelp.hidePanel(panel);
    }

    @Override
    protected String setConfigFileName() {
        return "save/logindata.propertise";
    }

    /**
     * 注册账号
     *
     * @param event
     */
    public void regist(ActionEvent event) {
        String user = regist_user.getText().trim();
        String password = regist_password.getText().trim();
        String check = check_password.getText().trim();
        String verification = check_num.getText().trim();
        RegistStatus regitst = modler.regitst(propertiesConfigManager, user, password, check, verification);
        switch (regitst) {
            case SUCCESS:
                uihelp.prompt((JComponent) panel.getParent(), Constant.getString("RegistSuccess"), 2000);
                regist_user = null;
                regist_password = null;
                check_password = null;
                check_num = null;
                break;
            case CHECK_PASSWORD:
                uihelp.prompt((JComponent) panel.getParent(), Constant.getString("TwoCipherInconsistencies"), 2000);
                break;
            case PASSWORD_ISEMPTY:
                uihelp.prompt((JComponent) panel.getParent(), Constant.getString("PassWordIsEmpty"), 2000);
                break;
            case USER_ISEMPTY:
                uihelp.prompt((JComponent) panel.getParent(), Constant.getString("UserNameIsEmpty"), 2000);
                break;
            case USER_PATTERN_ERR:
                uihelp.prompt((JComponent) panel.getParent(), Constant.getString("UserFormatErr"), 2000);
                break;
        }
    }
}
