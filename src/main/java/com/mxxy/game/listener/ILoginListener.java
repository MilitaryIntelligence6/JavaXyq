package com.mxxy.game.listener;

import com.mxxy.game.event.BaseEvent;

import java.util.EventListener;

public interface ILoginListener extends EventListener {
    /**
     * ImageComponent 事件
     *
     * @param loginEvent
     * @throws Exception
     */
    void loginEvent(BaseEvent loginEvent) throws Exception;
}
