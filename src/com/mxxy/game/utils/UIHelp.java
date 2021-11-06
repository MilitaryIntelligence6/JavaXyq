package com.mxxy.game.utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.mxxy.game.base.Panel;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.ui.IWindows;
import com.mxxy.game.widget.ImageComponentButton;
import com.mxxy.game.widget.Label;
import com.mxxy.game.widget.PromptLabel;

/**
 * UI 辅助
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class UIHelp {

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			/** 自动匹配当前系统风格 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private IWindows windows;

	public UIHelp(IWindows windows) {
		this.windows = windows;
		componentMap = new HashMap<String, Set<String>>();
	}

	public void showPanel(String key) {
		Panel panel = getPanel(key);
		if (panel != null) {
			showPanel(panel);
		}
	}

	/**
	 * 显示面板
	 * 
	 * @param dialog
	 */
	public void showPanel(Panel panel) {
		Container canvas = windows.getContainers();
		if (panel != null && panel.getParent() != canvas) {
			panel.handleEvent(new PanelEvent(panel, PanelEvent.INITIAL)); // 触发面板init事件
			canvas.add(panel, 0);
		}
	}

	/**
	 * 隐藏面板
	 * 
	 * @param panel
	 */
	public void hidePanel(Panel panel) {
		if (panel != null) {
			Container canvas = windows.getContainers();
			if (panel.getParent() == canvas) {
				canvas.remove(panel);
				panel.fireEvent(new PanelEvent(panel, PanelEvent.DISPOSE)); // 触发面板dispose事件
				PanelManager.dispose(panel.getName(), panel);
			}
		}
	}

	public void hidePanel(String key) {
		Panel panel = getPanel(key);
		if (panel != null) {
			hidePanel(panel);
		}
	}

	public void switchPanel(Panel panel) {
		if (panel.isShowing()) {
			hidePanel(panel);
		} else {
			showPanel(panel);
		}
	}

	public Panel getPanel(String id) {
		Panel dlg = PanelManager.getPanel(id);
		return dlg;
	}

	public Panel getPanel(ActionEvent event) {
		Panel dlg = PanelManager.getPanel(((ImageComponentButton) event
				.getSource()).getName());
		return dlg;
	}

	private List<PromptLabel> prompts = new ArrayList<PromptLabel>();

	public void prompt(JComponent component, String text, long delay) {
		final PromptLabel label = new PromptLabel(text);
		ImageIcon icon = new ImageIcon("res/componentsRes/tts.png");
		Label label2 = new Label(null, icon, 0);
		int offset = prompts.size() * 15;
		label2.setBounds(240 + offset, 210 + offset, icon.getIconWidth(),
				icon.getIconHeight());
		label.setLocation((Constant.WINDOW_WIDTH - label.getWidth()) / 2
				+ offset, 230 + offset);
		Container jComponent = component == null ? windows.getContainers()
				: component;
		jComponent.add(label, 0);
		jComponent.add(label2, 0);
		prompts.add(label);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				jComponent.remove(label);
				jComponent.remove(label2);
				prompts.set(prompts.indexOf(label), null);
				boolean empty = true;
				for (int i = 0; i < prompts.size(); i++) {
					if (prompts.get(i) != null) {
						empty = false;
					}
				}
				if (empty) {
					prompts.clear();
				}
			}
		};
		new Timer().schedule(task, delay);
	}

	public void showToolTip(JComponent c, JComponent src, MouseEvent e) {
		final Container canvas = windows.getContainers();
		Point p = SwingUtilities.convertPoint(src, src.getToolTipLocation(e),
				canvas);
		p.translate(20, 30);
		p.x = (p.x + c.getWidth() < canvas.getWidth() - 2) ? p.x : (canvas
				.getWidth() - c.getWidth() - 2);
		p.y = (p.y + c.getHeight() < canvas.getHeight() - 2) ? p.y : (canvas
				.getHeight() - c.getHeight() - 2);
		c.setLocation(p);
		canvas.add(c, 0);
	}

	/**
	 * 隐藏tooltip
	 * 
	 * @param c
	 */
	public void hideToolTip(JComponent c) {
		final Container canvas = windows.getContainers();
		canvas.remove(c);
	}

	private JFrame topParent;

	private Point dialogPoint;

	private JDialog dialog;

	public static final String DIALOGI = "DIALOGI";
	public static final String MAIN_WINDOW = "MAIN_WINDOS";

	private boolean isSnapDialog = true;

	private Map<String, Set<String>> componentMap;// 一个窗口关系的变量

	public void updateDistance() {
		dialogPoint = new Point(dialog.getLocation().x
				- topParent.getLocation().x, dialog.getLocation().y
				- topParent.getLocation().y);
	}

	public void updateComponentSnap() {
		// 先更新窗口互相之间的状态,比如有没有吸附
		udpateComponentMapWithLoation();
		// 更新歌词秀窗口状态
		String me = this.getComponentName(dialog);
		Set<String> set = componentMap.get(me);// 这里放的是可以遍历的字符串
		Set<String> list = new HashSet<String>();// 这个列表放的是遍历过的字符串
		list.add(me);
		boolean find = false;
		out: while (true) {

			if (set.size() == 0) {
				break out;
			} else {
				Set<String> temp = new HashSet<String>();
				for (String other : set) {
					if (other.equals(MAIN_WINDOW)) {
						find = true;
						break out;
					} else if (!list.contains(other)) {
						temp.addAll(componentMap.get(other));
					}
					list.add(other);
				}
				set.removeAll(list);
				set.addAll(temp);
			}
		}

		this.setSnapWindow(dialog, find);
	}

	public String getComponentName(Component com) {
		if (com == this.dialog) {
			return DIALOGI;
		} else if (com == this.topParent) {
			return MAIN_WINDOW;
		} else {
			return "";
		}
	}

	private void setSnapWindow(Component com, boolean snap) {
		if (com == this.dialog) {
			this.setSnapDialog(snap);
		}
	}

	public boolean isSnapDialog() {
		return isSnapDialog;
	}

	public void setSnapDialog(boolean isSnapDialog) {
		this.isSnapDialog = isSnapDialog;
	}

	private void udpateComponentMapWithLoation() {
		List<Component> list = new ArrayList<Component>();
		Rectangle otherBound = new Rectangle();
		Rectangle myBound = new Rectangle();
		list.add(dialog);
		list.add(topParent);
		componentMap.clear();
		// 先查歌词秀窗口吸附到谁了
		Component me = dialog;

		me.getBounds(myBound);

		Set<String> set = new HashSet<String>();
		for (Component c1 : list) {
			if (c1 != null && c1 != me && c1.isShowing() && me.isShowing()) {
				c1.getBounds(otherBound);
				int dis = getDistance(myBound, otherBound);
				if (Math.abs(dis) <= 10) {

					set.add(getComponentName(c1));
					if (c1 == topParent) {
						break;
					}
				}
			}
		}
		componentMap.put(getComponentName(me), set);

	}

	public static int getDistance(Rectangle rec1, Rectangle rec2) {
		if (rec1.intersects(rec2)) {
			return Integer.MAX_VALUE;
		}
		int x1 = (int) rec1.getCenterX();
		int y1 = (int) rec1.getCenterY();
		int x2 = (int) rec2.getCenterX();
		int y2 = (int) rec2.getCenterY();
		int dis1 = Math.abs(x1 - x2) - rec1.width / 2 - rec2.width / 2;
		int dis2 = Math.abs(y1 - y2) - rec1.height / 2 - rec2.height / 2;
		return Math.max(dis1, dis2) - 1;
	}

	public void setTopParent(JFrame topParent) {
		this.topParent = topParent;
	}

	public JFrame getTopParent() {
		return topParent;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	public Point getDialogPoint() {
		return dialogPoint;
	}

	public void setDialogPoint(Point dialogPoint) {
		this.dialogPoint = dialogPoint;
	}

	public JDialog getDialog() {
		return dialog;
	}

}
