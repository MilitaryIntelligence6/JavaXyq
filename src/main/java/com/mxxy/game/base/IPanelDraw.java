package com.mxxy.game.base;

import com.mxxy.game.config.Context;
import com.mxxy.game.sprite.Cursor;
import com.mxxy.game.utils.UIHelp;

import javax.swing.*;

public interface IPanelDraw {
    /**
     * 设置Panel宽高
     *
     * @param width
     * @param height
     */
    void setScreenSize(int width, int height);

    /**
     * 获取当前Panel的宽度
     *
     * @return
     */
    int getScreenWidth();

    /**
     * 获取当前Panel的高度
     *
     * @return
     */
    int getScernHeight();

    JComponent getComponent();

    /**
     * 获取当前鼠标状态
     *
     * @return
     */
    Cursor getGameCursor();

    /**
     * 设置当前Panel鼠标状态
     *
     * @param cursor
     */
    void setGameCursor(String cursor);

    /**
     * 播放音乐
     */
    void playMusic();

    /**
     * 停止播放
     */
    void stopMusic();

    void setUIhelp(UIHelp uiHelp);

    void setContext(Context context);
}
