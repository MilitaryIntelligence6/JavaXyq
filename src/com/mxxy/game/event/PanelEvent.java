package com.mxxy.game.event;

import java.awt.Panel;

@SuppressWarnings("serial")
public class PanelEvent extends BaseEvent {

	public static final String INITIAL = "init";

	public static final String UPDATE = "update";

	public static final String DISPOSE = "dispose";

	private Panel panel;

	public PanelEvent(Object source, String command, Object[] args) {
		super(source, command, args);
		consumed = false;
		if (source instanceof Panel) {
			this.panel = (Panel) source;
		}
	}

	public PanelEvent(Object source, String command) {
		super(source, command);
		consumed = false;
		if (source instanceof Panel) {
			this.panel = (Panel) source;
		}
	}

	public PanelEvent(Object source, String command, Panel panel) {
		this(source, command, panel, null);
	}

	public PanelEvent(Object source, String command, Panel panel, Object[] args) {
		super(source, command, args);
		consumed = false;
		this.panel = panel;
	}

	public Panel getPanel() {
		return panel;
	}
}