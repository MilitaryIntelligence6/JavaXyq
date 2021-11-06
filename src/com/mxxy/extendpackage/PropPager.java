package com.mxxy.extendpackage;

import com.mxxy.game.base.Panel;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;

import java.awt.event.ActionEvent;

/**
 * PropPager (道具)
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
@SuppressWarnings("rawtypes")
final public class PropPager extends AbstractPanelHandler {

    private Panel palyer;

    private Panel mount;

    @Override
    public void init(PanelEvent evt) {
        super.init(evt);
    }

    @Override
    protected void initView() {
        palyer = uihelp.getPanel(PlayerPager.class.getSimpleName());
        mount = uihelp.getPanel(MountPager.class.getSimpleName());
    }

    /**
     * 人物面板
     *
     * @param e
     */
    public void player(ActionEvent e) {
        show();
    }

    public void show() {
        uihelp.hidePanel(mount);
        uihelp.showPanel(palyer);
    }

    /**
     * 坐骑面板
     *
     * @param e
     */
    public void mount(ActionEvent e) {
        uihelp.hidePanel(palyer);
        uihelp.showPanel(mount);
    }

    @Override
    public void dispose(PanelEvent evt) {
        super.dispose(evt);
        uihelp.hidePanel(palyer);
        uihelp.hidePanel(mount);
    }
}
