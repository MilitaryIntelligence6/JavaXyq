package com.mxxy.extendpackage;

import cn.hutool.core.util.RandomUtil;
import com.mxxy.config.BuildConfig;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.ui.BattlePanel;
import com.mxxy.util.Logger;

import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ZAB
 * 战斗指令
 * 2018年5月29日
 */
final public class BattlePanelCmd extends AbstractPanelHandler {

    private BattlePanel battlepanel;

    @Override
    public void init(PanelEvent evt) {
        super.init(evt);
        if (super.iWindows.getPanel() instanceof BattlePanel) {
            this.battlepanel = (BattlePanel) super.iWindows.getPanel();
        }
    }

    @Override
    protected void initView() {

    }

    /**
     * 法术
     *
     * @param e
     */
    public void warmagic(ActionEvent e) {
        battlepanel.selectWarmagic();
    }

    /**
     * 道具
     */
    public void waritem(ActionEvent e) {
        battlepanel.selectProp();
    }

    /**
     * 防御
     */
    public void wardefend(ActionEvent e) {
        Logger.echoUnImpl();
    }

    /**
     * 召唤
     */
    public void warcall(ActionEvent e) {
        Logger.echoUnImpl();
    }

    /**
     * 保护
     */
    public void warprotect(ActionEvent e) {
        Logger.echoUnImpl();
    }

    /**
     * 捕捉
     */
    public void warcatch(ActionEvent e) {
        Logger.echoUnImpl();
    }

    /**
     * 逃跑
     */
    public void warrunaway(ActionEvent e) {
        if (BuildConfig.UN_IMPL_WAR_ONLY_CAN_RUN_AWAY) {
            battlepanel.runaway(player, true);
        } else {
            battlepanel.runaway(player, RandomUtil.randomBoolean());
        }
    }
}
