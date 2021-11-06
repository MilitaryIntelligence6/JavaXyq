package com.mxxy.game.event;

import org.w3c.dom.events.EventException;

import java.util.EventObject;

/**
 * 任务处理
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public interface IEventTask {

    abstract boolean handleEvent(EventObject evt) throws EventException;
}
