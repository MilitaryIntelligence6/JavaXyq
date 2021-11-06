package com.mxxy.game.ui.battle;

import com.mxxy.game.domain.PlayerVO;
import com.mxxy.game.sprite.Players;

import java.util.HashMap;
import java.util.Map;

/**
 * 指令
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class Command implements Comparable {

    private String cmd;

    private Players source;

    private Players target;

    private Map<String, Object> params = new HashMap<String, Object>();

    public Command(String cmd, Players source) {
        this(cmd, source, null, null);
    }

    public Command(String cmd, Players source, Players target) {
        this.cmd = cmd;
        this.source = source;
        this.target = target;
    }

    public Command(String cmd, Players source, Players target, Map<String, Object> params) {
        this(cmd, source, target);
        this.params = params;
    }

    public String getCmd() {
        return this.cmd;
    }

    /**
     * 添加一个参数
     *
     * @param name
     * @param value
     */
    public void add(String name, Object value) {
        this.params.put(name, value);
    }

    public Object get(String name) {
        return this.params.get(name);
    }

    public int getInt(String name) {
        Integer val = (Integer) this.params.get(name);
        return val != null ? val.intValue() : 0;
    }

    public boolean getBool(String name) {
        Boolean val = (Boolean) this.params.get(name);
        return val != null ? val.booleanValue() : false;
    }

    /**
     * @return the source 已方
     */
    public Players getSource() {
        return source;
    }

    /**
     * @return the target 目标
     */
    public Players getTarget() {
        return target;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Command [cmd=");
        builder.append(cmd);
        builder.append(", params=");
        builder.append(params);
        builder.append(", source=");
        builder.append(source);
        builder.append(", target=");
        builder.append(target);
        builder.append("]");
        return builder.toString();
    }

    /**
     * 根据人物速度比较
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof Command) {
            Command cmd2 = (Command) o;
            PlayerVO data1 = this.source.getPalyVo();
            PlayerVO data2 = cmd2.getSource().getPalyVo();
            return data1.getSpeed() - data2.getSpeed();
        }
        return 0;
    }
}
