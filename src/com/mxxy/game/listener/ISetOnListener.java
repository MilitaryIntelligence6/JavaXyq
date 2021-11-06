package com.mxxy.game.listener;

/**
 * @author ZAB
 * @param <T>
 *            Controller(事件源)
 */
public interface ISetOnListener<T> {
	/**
	 * setting Listener
	 * 
	 * @param event
	 */
	void setListener(T event);

	/**
	 * romove Listener
	 * 
	 * @param event
	 */
	void removeListener(T event);

}
