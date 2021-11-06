package com.mxxy.game.xml;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import com.mxxy.game.base.Panel;
import com.mxxy.game.listener.IPanelListener;
import com.mxxy.game.listener.PaneListener;
import com.mxxy.game.utils.ComponentFactory;
import com.mxxy.game.utils.GameColor;
import com.mxxy.game.utils.InstanceUtil;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.utils.StringUtils;
import com.mxxy.game.widget.ImageCheckBox;
import com.mxxy.game.widget.ImageComponent;
import com.mxxy.game.widget.ImageComponentButton;
import com.mxxy.game.widget.Label;

/**
 * 扩展性引擎
 * @author javaman
 */
public class GameBuild implements IGameBuilder {

	private ExtendScript extendScript;

	public ArrayList<ImageComponent> imageComponents = new ArrayList<ImageComponent>();

	private SAXReader saxReader;

	public GameBuild() {
		saxReader = new SAXReader();
		extendScript = new ExtendScript();
	}

	@Override
	public Panel createPanel(String id, String fileName) {
		System.out.println("createPanel" + fileName);
		Panel panel = null;
		try {
			Document read = saxReader.read(fileName);
			Element rootElement = read.getRootElement();
			panel = parsePanel(rootElement);
			parseComponent(panel, rootElement);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return panel;
	}

	/*** 解析配置文件里面的控件 */
	@SuppressWarnings("unchecked")
	public void parseComponent(Panel panel, Element rootElement) {
		List<Element> elements = rootElement.elements();
		for (Element sElement : elements) {
			try {
				this.invokeMethod("parse" + sElement.getName(), panel, sElement);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 解析ImageComPonent(该方法通过反射调用)
	 *
	 * @param panel
	 * @param rootElement
	 */
	public void parseImageComPonent(Panel panel, DefaultElement rootElement)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException {
		int x = Integer.parseInt(rootElement.attributeValue("x"));
		int y = Integer.parseInt(rootElement.attributeValue("y"));
		String path = rootElement.attributeValue("path");
		String attributeValue = rootElement.attributeValue("width");
		String heightAttributeValue = rootElement.attributeValue("height");
		int width = 0;
		int height = 0;
		if (StringUtils.isNotBlank(attributeValue) || StringUtils.isNotBlank(heightAttributeValue)) {
			width = Integer.parseInt(attributeValue);
			height = Integer.parseInt(heightAttributeValue);
		}

		ImageComponent imageComponent = new ImageComponent(path, x, y, new Point(width, height));
		imageComponents.add(imageComponent);
		panel.setImageComponents(imageComponents);
	}

	/**
	 * 构造精灵图片(该方法通过反射调用)
	 *
	 * @param panel
	 * @param rootElement
	 */
	public final int grivate[] = { SwingConstants.CENTER, SwingConstants.LEFT, SwingConstants.RIGHT,SwingConstants.BOTTOM };

	public void parseSprite(Panel panel, DefaultElement rootElement) {
		Label label = null;
		String border = rootElement.attributeValue("border");
		String text = rootElement.attributeValue("text");
		String imageIcon = rootElement.attributeValue("imageIcon");
		String name = rootElement.attributeValue("name");
		String path = rootElement.attributeValue("path");
		String griva = rootElement.attributeValue("grivate");
		String color = rootElement.attributeValue("color");
		if (StringUtils.isNotBlank(imageIcon)) {
			ImageIcon icon = new ImageIcon(path);
			label = new Label(null, icon, 0);
		} else {
			label = new Label(SpriteFactory.loadAnimation(path));
		}
		if (StringUtils.isNotBlank(color)) {
			label.setForeground(GameColor.getColor(color));
		}
		if (StringUtils.isNotBlank(griva)) {
			label.setHorizontalAlignment(grivate[Integer.parseInt(griva)]);
		}
		if (StringUtils.isNotBlank(border)) {
			label.setBorder();
		}
		label.setText(text);
		label.setName(name);
		String x = rootElement.attributeValue("x");
		String y = rootElement.attributeValue("y");
		if (StringUtils.isNotBlank(x) && StringUtils.isNotBlank(y)) {
			label.setLocation(Integer.parseInt(x), Integer.parseInt(y));
		}
		String width = rootElement.attributeValue("width");
		String height = rootElement.attributeValue("height");
		if (StringUtils.isNotBlank(width) || StringUtils.isNotBlank(height)) {
			label.setSize(Integer.valueOf(width), Integer.valueOf(height));
		}
		panel.add(label);
	}

	/**
	 * 构造Button (该方法通过反射调用)
	 *
	 * @param panel
	 * @param rootElement
	 */
	public void parseImageComponentButton(Panel panel, DefaultElement rootElement) {
		String text = rootElement.attributeValue("text");
		String name = rootElement.attributeValue("name");
		String enable = rootElement.attributeValue("enable");
		int x = Integer.parseInt(rootElement.attributeValue("x"));
		int y = Integer.parseInt(rootElement.attributeValue("y"));
		String actionId = rootElement.attributeValue("actionId");
		String path = rootElement.attributeValue("path");
		String paths = rootElement.attributeValue("paths");
		String enableds = rootElement.attributeValue("enableds");
		ImageComponentButton imageComponentButton = new ImageComponentButton();
		imageComponentButton.setName(name);
		imageComponentButton.setEnableds(StringUtils.isNotBlank(enableds));
		imageComponentButton.addActionListener(panel);

		if (StringUtils.isNotBlank(actionId)) {
			imageComponentButton.setActionCommand(actionId);
		}

		if (StringUtils.isNotBlank(path)) {
			imageComponentButton.init(SpriteFactory.loadSprite(path));
		}

		if (StringUtils.isNotBlank(text)) {
			imageComponentButton.setText(text);
		}

		imageComponentButton.setEnabled(enable == null);
		if (StringUtils.isNotBlank(paths)) {
			int width = Integer.valueOf(rootElement.attributeValue("width"));
			int height = Integer.valueOf(rootElement.attributeValue("height"));
			int index = paths.lastIndexOf('.');
			imageComponentButton.loadAnimation(
					new ImageIcon[] { new ImageIcon(paths), new ImageIcon(StringUtils.insertString(paths, "3", index)),
							new ImageIcon(StringUtils.insertString(paths, "2", index)) });
			imageComponentButton.setSize(width, height);
		}
		imageComponentButton.setLocation(x, y);
		panel.add(imageComponentButton, 0);
	}

	/**
	 * 解析面板 (该方法通过反射调用)
	 *
	 * @param rootElement
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Panel parsePanel(Element rootElement) throws ClassNotFoundException {
		imageComponents.clear();
		int width = Integer.parseInt(rootElement.attributeValue("width"));
		int height = Integer.parseInt(rootElement.attributeValue("height"));
		int x = Integer.parseInt(rootElement.attributeValue("x"));
		int y = Integer.parseInt(rootElement.attributeValue("y"));
		String transparency = rootElement.attributeValue("transparency");
		Panel panel = new Panel(width, height);
		if (StringUtils.isNotBlank(transparency)) {
			panel.setTransparency(Float.parseFloat(transparency));
		}
		panel.setBackground(GameColor.black);

		if (rootElement.attributeValue("move") != null|| rootElement.attributeValue("isReightClose") != null) {
			new PaneListener(panel,rootElement.attributeValue("isReightClose") != null);
		}

		panel.setLocation(x, y);
		panel.setName(rootElement.attributeValue("id"));
		Class<?> class1 = Class.forName("com.mxxy.extendpackage." + panel.getName());
		IPanelListener iPanelListener = InstanceUtil.getInstance(class1);
		// IPanelListener iPanelListener=(IPanelListener)
		// extendScript.loadUIScript(panel.getName());
		if (iPanelListener != null)
			panel.addPanelListener(iPanelListener);
		return panel;
	}

	/**
	 * 解析文本(通过反射调用)
	 */
	public void parseTextField(Panel panel, DefaultElement rootElement) {
		String name = rootElement.attributeValue("name");
		int x = Integer.parseInt(rootElement.attributeValue("x"));
		int y = Integer.parseInt(rootElement.attributeValue("y"));
		int width = Integer.parseInt(rootElement.attributeValue("width"));
		int height = Integer.parseInt(rootElement.attributeValue("height"));
		String hide = rootElement.attributeValue("hide");
		JTextField jTextField = null;
		if (StringUtils.isNotBlank(hide)) {
			jTextField = ComponentFactory.regitsTextField(hide);
		} else {
			jTextField = new JTextField();
		}
		jTextField.setToolTipText(rootElement.attributeValue("tooltip"));
		jTextField.setName(name);
		jTextField.setOpaque(false);
		jTextField.setBorder(null);
		jTextField.setBounds(x, y, width, height);
		panel.add(jTextField, 0);
	}

	/**
	 * 解析CheckBox(通过反射调用)
	 *
	 * @param panel
	 * @param rootElement
	 */
	public void parseCheckBox(Panel panel, DefaultElement rootElement) {
		String name = rootElement.attributeValue("name");
		int x = Integer.parseInt(rootElement.attributeValue("x"));
		int y = Integer.parseInt(rootElement.attributeValue("y"));
		int width = Integer.parseInt(rootElement.attributeValue("width"));
		int height = Integer.parseInt(rootElement.attributeValue("height"));
		ImageIcon defaultIcon = new ImageIcon(rootElement.attributeValue("defaultIcon"));
		ImageIcon selectIcon = new ImageIcon(rootElement.attributeValue("selectIcon"));
		ImageCheckBox imageCheckBox = new ImageCheckBox(defaultIcon);
		imageCheckBox.setSelectedIcon(selectIcon);
		imageCheckBox.setOpaque(false);
		imageCheckBox.setSize(width, height);
		imageCheckBox.setLocation(x, y);
		imageCheckBox.setName(name);
		panel.add(imageCheckBox, 0);
	}

	public void processAction(Panel dialog, DefaultElement el) {
		String actionId = (String) el.attributeValue("id");
		String className = (String) el.attributeValue("class");
		try {
			Action action = (Action) Class.forName(className).newInstance();
			action.putValue(Action.ACTION_COMMAND_KEY, actionId);
			dialog.getActionMap().put(actionId, action);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 反射
	 *
	 * @param mName
	 *            方法名
	 * @param arg
	 *            对应方法的参数
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public Object invokeMethod(String mName, Object... arg) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, SecurityException, NoSuchMethodException {
		Method method = this.getClass().getDeclaredMethod(mName, arg[0].getClass(), arg[1].getClass());
		return method.invoke(this, arg);
	}
}
