package com.mxxy.game.listener;

import com.mxxy.game.event.PlayerEvent;
import com.mxxy.game.sprite.Players;

import java.awt.*;
import java.util.EventListener;

/**
 * 玩家事件
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public interface IPlayerListener extends EventListener {

    void stepOver(Players player);

    /**
     * 人物移动
     *
     * @param player
     * @param increment
     */
    void move(Players player, Point increment);

    /**
     * 点击
     *
     * @param evt
     */
    void click(PlayerEvent evt);

    /**
     * NPC 谈话
     *
     * @param evt
     */
    void talk(PlayerEvent evt);

    /**
     * 给予
     *
     * @param event
     */
    void give(PlayerEvent event);

    /**
     * 人物行走
     *
     * @param evt
     */
    void walk(PlayerEvent evt);
}
