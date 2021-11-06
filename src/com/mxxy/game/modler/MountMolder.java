package com.mxxy.game.modler;

import com.mxxy.game.config.IPropertiseManager;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.utils.FileUtils;
import com.mxxy.game.widget.ScrollList;

import javax.swing.*;
import java.util.ArrayList;

/**
 * 坐骑数据处理
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class MountMolder {

    private String fileName;

    /**
     * 列表数据
     *
     * @param propertiesConfigManager
     * @param mountName
     * @return
     */
    public JList<String> getList(IPropertiseManager propertiesConfigManager) {
        JList<String> list = new ScrollList<String>(new ListModler(getMountName(propertiesConfigManager)));
        list.setFont(Constant.TEXT_MOUNT_FONT);
        return list;
    }

    /**
     * 获取坐骑名字
     *
     * @param propertiesConfigManager
     * @return
     */
    public ArrayList<String> getMountName(IPropertiseManager propertiesConfigManager) {
        ArrayList<String> mountName = new ArrayList<String>();
        ArrayList<String> allDir = getAllDir(fileName);
        for (int i = 0; i < allDir.size(); i++) {
            String string = allDir.get(i);
            String substring = string.substring(string.lastIndexOf(Constant.flie_spance) + 1, string.length());
            mountName.add(propertiesConfigManager.get(substring));
        }
        return mountName;
    }

    /**
     * 获取文件层级
     *
     * @param string
     * @return
     */
    public ArrayList<String> getAllDir(String string) {
        this.fileName = string;
        return FileUtils.getAllDir(string);
    }

    @SuppressWarnings("serial")
    public class ListModler extends AbstractListModel<String> {

        private ArrayList<String> string;

        public ListModler(ArrayList<String> allDir) {
            this.string = allDir;
        }

        @Override
        public String getElementAt(int index) {
            return string.get(index);
        }

        @Override
        public int getSize() {
            return string.size();
        }
    }
}
