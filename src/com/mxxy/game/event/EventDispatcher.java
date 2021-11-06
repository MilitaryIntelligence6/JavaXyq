package com.mxxy.game.event;

import java.util.EventObject;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 事件分发生产消费者
 * 
 * @author ZAB
 * @param <S>
 *            任务调度
 * @param <E>
 *            事件源
 */
public class EventDispatcher<S extends IEventTask, E extends EventObject> extends Thread {
	/**
	 * 事件队列
	 */
	private BlockingQueue<E> eventQueue = new LinkedBlockingQueue<E>();

	private static int processorCount;

	private EventDispatcher() {
		processorCount++;
		setName("EventDispatcher-" + processorCount);
		setDaemon(true);
	}

	/** 分发事件 */
	public void dispatchEvent(E e) {
		eventQueue.offer(e); // 添加到队列
	}

	private static EventDispatcher<?, ?> dispatcher;

	@SuppressWarnings("rawtypes")
	public synchronized static EventDispatcher getInstance() {
		if (dispatcher == null) {
			dispatcher = new EventDispatcher<>();
			dispatcher.start();
		}
		return dispatcher;
	}

	@Override
	public void run() {
		pumpEvents(new Condition() {
			@Override
			public boolean isRunnig() {
				return true;
			}
		});
	}

	/** Callback控制线程消费事件 */
	public interface Condition {
		boolean isRunnig(); // true消费,false停止线程
	}

	public void actionEvent(Condition condition) {
		pumpEvents(condition);
	}

	@SuppressWarnings("unchecked")
	private void pumpEvents(Condition condition) {
		while (condition.isRunnig()) {
			try {
				E evt = eventQueue.take(); // 取出事件
				if (evt != null) {
					((S) evt.getSource()).handleEvent(evt); // 消费
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
