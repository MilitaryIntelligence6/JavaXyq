package com.mxxy.game.base;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import org.w3c.dom.events.EventException;

import com.mxxy.game.event.BaseEvent;
import com.mxxy.game.event.EventDispatcher;
import com.mxxy.game.event.IEventTask;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.listener.IPanelListener;
import com.mxxy.game.listener.ISetOnListener;
import com.mxxy.game.listener.PaneListener;
import com.mxxy.game.utils.GraphicsUtils;
import com.mxxy.game.widget.ImageComponent;
import com.mxxy.game.widget.SpriteImage;

/**
 * 功能面板
 * 
 * @author dell
 */
@SuppressWarnings("serial")
public class Panel extends JPanel implements IEventTask, ActionListener, ISetOnListener<PaneListener> {

	private EventListenerList listener = new EventListenerList();
	/*** 透明度 */
	private float transparency;
	/** 右键关闭 */
	private boolean isRightClickClose;
	/** 小地图或者背景图片 **/
	private SpriteImage smapImage;
	/** 面板是否移动 */
	private boolean isMove;
	/** 参数传递 */
	private Map<String, Object> attributes;

	public Panel(int width, int height) {
		super(null);
		setSize(width, height);
		setIgnoreRepaint(true);
		setBorder(null);
		setFocusable(false);
		setPreferredSize(new Dimension(width, height));
		setSize(width, height);
	}

	@Override
	public boolean handleEvent(EventObject evt) throws EventException {
		if (evt instanceof BaseEvent) {
			handleActionEvent((BaseEvent) evt);
		}
		return false;
	}

	public void handleActionEvent(ActionEvent evt) {
		IPanelListener[] listeners = listener.getListeners(IPanelListener.class);
		for (int i = 0; i < listeners.length; i++) {
			listeners[i].actionPerformed(evt);
		}
	}

	/**
	 * 面板初始化事件
	 */
	public void init() {
		fireEvent(new PanelEvent(this, PanelEvent.INITIAL));
	}

	/**
	 * 关闭面板
	 */
	public void close() {
		if (getParent() != null) {
			getParent().remove(this);
			fireEvent(new PanelEvent(this, PanelEvent.DISPOSE));
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g.create();
		GraphicsUtils.setAlpha(graphics2d, transparency);
		graphics2d.setColor(getBackground());
		graphics2d.fillRect(0, 0, getWidth(), getHeight());
		try {
			if (smapImage != null)
				smapImage.drawBitmap(graphics2d);
			drawImageComponent(graphics2d);

			paintBorder(g); // 绘制边框

			paintChildren(g); // 绘制该Panel的子组件
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * 设置透明度
	 * 
	 * @param transparency
	 */
	public void setTransparency(float transparency) {
		this.transparency = transparency;
	}

	public void setSmapImage(SpriteImage smapImage) {
		this.smapImage = smapImage;
	}

	public SpriteImage getSmapImage() {
		return smapImage;
	}

	private ArrayList<ImageComponent> imageComponents;

	public void setImageComponents(ArrayList<ImageComponent> imageComponents) {
		this.imageComponents = imageComponents;
	}

	public void clear() {
		this.imageComponents.clear();
	}

	private void drawImageComponent(Graphics2D g) {
		if (imageComponents != null)
			for (int i = 0; i < imageComponents.size(); i++) {
				ImageComponent imageComponent = imageComponents.get(i);
				imageComponent.drawBitmap(g, imageComponent.getX(), imageComponent.getY());
			}
	}

	/**
	 * 查询该Panel的控件
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends Component> T findViewById(String name) {
		if (name != null) {
			Component[] comps = getComponents();
			for (Component comp : comps) {
				if (name.equals(comp.getName()))
					return (T) comp;
			}
		}
		return null;
	}

	public void addPanelListener(IPanelListener l) {
		listener.add(IPanelListener.class, l);
	}

	public void removePanelListener(IPanelListener l) {
		listener.remove(IPanelListener.class, l);
	}

	/**
	 * 清空所有事件
	 */
	public void removeAllPanelListeners() {
		IPanelListener[] listeners = listenerList.getListeners(IPanelListener.class);
		for (int i = 0; i < listeners.length; i++) {
			listenerList.remove(IPanelListener.class, listeners[i]);
		}
	}

	/**
	 * 事件分发
	 * 
	 * @param e
	 */
	@SuppressWarnings("unchecked")
	public void fireEvent(ActionEvent e) {
		EventDispatcher.getInstance().dispatchEvent(e);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		handleActionEvent(e);
	}

	@Override
	public void setListener(PaneListener event) {
		this.addMouseListener(event);
		this.addMouseMotionListener(event);
	}

	@Override
	public void removeListener(PaneListener event) {
		this.removeMouseListener(event);
		this.removeMouseMotionListener(event);
	}

	public void setRightClickClose(boolean isRightClickClose) {
		this.isRightClickClose = isRightClickClose;
	}

	public boolean isRightClickClose() {
		return isRightClickClose;
	}

	@Override
	public void paintImmediately(int x, int y, int w, int h) {
	}

	/**
	 * 获取当前鼠标坐标
	 * 
	 * @return
	 */
	public Point getPointCursor() {
		return super.getMousePosition();
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
}
