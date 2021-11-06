package com.mxxy.game.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mxxy.game.event.BaseEvent;
import com.mxxy.game.event.PanelEvent;

/**
 * 面板事件Listener
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public interface IPanelListener extends ActionListener {

	/**
	 * 初始化（每次显示时调用）
	 * 
	 * @param evt
	 */
	void init(PanelEvent evt);

	/**
	 * 注销（关闭时调用）
	 * 
	 * @param evt
	 */
	void dispose(PanelEvent evt);

	/**
	 * 更新面板的数据
	 * 
	 * @param evt
	 */
	void update(PanelEvent evt);

	/**
	 * 关闭窗口
	 * 
	 * @param evt
	 */
	void close(ActionEvent evt);

	/**
	 * 显示帮助信息
	 * 
	 * @param evt
	 */
	void help(BaseEvent evt);

	/**
	 * 按钮按下、点击Label等事件
	 */
	void actionPerformed(ActionEvent evt);

}