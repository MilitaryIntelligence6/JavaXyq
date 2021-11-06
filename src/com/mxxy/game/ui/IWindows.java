package com.mxxy.game.ui;

import com.mxxy.game.base.IPanelDraw;
import com.mxxy.game.config.Context;
import com.mxxy.game.utils.UIHelp;

import javax.swing.*;
import java.awt.*;

public interface IWindows {
    /**
     *
     */
    void setTitle(String title);

    /**
     * 初始化控件
     */
    void initContent(Context context);

    /**
     * 显示
     */
    void show();

    /**
     * 获取当前控件的Container
     *
     * @return
     */
    Container getContainers();

    /**
     * UI 帮助类
     */
    UIHelp getUIHelp();

    /**
     * 隐藏鼠标
     */
    void hideCursor();

    /**
     * 显示托盘
     */
    void showSystemtTray();


    IPanelDraw getPanel();


    void showPanel(IPanelDraw panelDraw);


    JFrame getFrame();
}
