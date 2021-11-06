package com.mxxy.game.listener;

import java.util.EventListener;

import com.mxxy.game.event.BaseEvent;

public interface ILoginListener extends EventListener {
	/**
	 * ImageComponent 事件
	 * 
	 * @param loginEvent
	 * @throws Exception
	 */
	void loginEvent(BaseEvent loginEvent) throws Exception;
}
