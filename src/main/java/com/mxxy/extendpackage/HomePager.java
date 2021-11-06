package com.mxxy.extendpackage;

import com.mxxy.game.base.Panel;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.ui.ScrollAnnouncer;
import com.mxxy.game.utils.RuntimeUtil;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * HomePager (主页)
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
@SuppressWarnings("rawtypes")
final public class HomePager extends AbstractPanelHandler {
    public static boolean isfrist;
    /**
     * 游戏主页
     */
    private ScrollAnnouncer pager;

    @Override
    public void init(PanelEvent evt) {
        super.init(evt);
        System.out.println("HomePager" + panel);
        if (!isfrist) {
            showAnnouncer();
        }
        isfrist = true;
    }

    @Override
    protected void initView() {

    }

    /**
     * 进入游戏
     */
    public void enter(ActionEvent e) {
        Panel openHeadPet = uihelp.getPanel(e);
        showHide(openHeadPet);
    }

    /**
     * 注册用户
     */
    public void register(ActionEvent e) {
        Panel dlg = uihelp.getPanel("RegistUser");
        uihelp.showPanel(dlg);
    }

    public void web(ActionEvent e) {
        showAnnouncer();
    }

    /**
     * 报幕
     */
    public void showAnnouncer() {
        pager = new ScrollAnnouncer();
        Thread thread = new Thread(pager);
        thread.start();
        panel.add(pager, 0);
        pager.setBounds(0, 0, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        pager.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel.remove(pager);
                pager.setStop(false);
                pager = null;
            }
        });
    }

    /**
     * 游戏充值
     */
    public void pay(ActionEvent e) {
        RuntimeUtil.exec("cmd /c start http://xyq.163.com/fab/");
    }

    /**
     * 点击梦幻西游LOGO
     *
     * @param e
     */
    public void logo(ActionEvent e) {
        RuntimeUtil.exec("cmd /c start http://xyq.163.com/fab/");
    }

    /**
     * 退出
     */
    public void exit(ActionEvent e) {
        application.exitGame();
    }
}
