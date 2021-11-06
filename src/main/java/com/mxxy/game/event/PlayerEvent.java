package com.mxxy.game.event;

import com.mxxy.game.sprite.Players;

import java.awt.*;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

/**
 * 玩家事件源
 *
 * @author dell
 */
@SuppressWarnings("serial")
public class PlayerEvent extends EventObject {

    /**
     * 行走完一步的事件
     */
    public static final int STEP_OVER = 1;

    /**
     * 移动事件
     */
    public static final int MOVE = 2;

    /**
     * 点击事件
     */
    public static final int CLICK = 3;

    /**
     * 谈话
     */
    public static final int TALK = 4;

    /**
     * 走
     */
    public static final int WALK = 5;
    public static final String MOVE_INCREMENT = "move.increment";
    private Map<String, Object> attributes;
    private Point target;
    /**
     * 行走的目标点
     */

    private String arguments;

    private int id;

    private Players player;

    public PlayerEvent(Players player, int id) {
        super(player);
        this.id = id;
        this.player = player;
    }

    public PlayerEvent(Players player, int id, Point target) {
        super(player);
        this.player = player;
        this.id = id;
        this.target = target;
    }

    public PlayerEvent(Players player, int id, String args) {
        super(player);
        this.player = player;
        this.id = id;
        this.arguments = args;
    }

    public void setAttributes(String name, Object value) {
        if (attributes == null) {
            attributes = new HashMap<String, Object>();
        }
        attributes.put(name, value);
    }

    public Object getAttributes(String name) {
        return (attributes == null) ? null : attributes.get(name);
    }

    public Point getTarget() {
        return target;
    }

    public void setTarget(Point target) {
        this.target = target;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "PlayerEvent [id=" + id + "]";
    }
}
