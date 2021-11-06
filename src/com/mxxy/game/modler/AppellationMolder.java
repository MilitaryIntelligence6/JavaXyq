package com.mxxy.game.modler;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import com.mxxy.game.resources.Constant;
import com.mxxy.game.widget.ScrollList;

/**
 * 称谓数据
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class AppellationMolder {

	public String[] appllation = { "护城小兵", "英雄会天科状元" };

	@SuppressWarnings("serial")
	class AppellationList extends AbstractListModel<String> {

		@Override
		public String getElementAt(int arg0) {
			return appllation[arg0];
		}

		@Override
		public int getSize() {
			return appllation.length;
		}
	}

	public JList<String> getList() {
		JList<String> list = new ScrollList<String>(new AppellationList());
		list.setFont(Constant.TEXT_MOUNT_FONT);
		return list;
	}
}
