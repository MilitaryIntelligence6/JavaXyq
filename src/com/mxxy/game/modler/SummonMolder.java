package com.mxxy.game.modler;

import com.mxxy.game.config.IPropertiseManager;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.utils.FileUtils;
import com.mxxy.game.widget.ScrollList;

import javax.swing.*;
import java.util.ArrayList;

/**
 * 召唤兽数据处理
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class SummonMolder {

    public JList<String> getList(IPropertiseManager propertiesConfigManager) {
        JList<String> list = new ScrollList<String>(new SummonList(getSummonList(propertiesConfigManager)));
        list.setFont(Constant.TEXT_MOUNT_FONT);
        return list;
    }

    /**
     * 获取神兽List
     *
     * @param propertiesConfigManager
     * @return
     */
    private ArrayList<String> getSummonList(IPropertiseManager propertiesConfigManager) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < getGodPet().size(); i++) {
            arrayList.add(propertiesConfigManager.get(getGodPet().get(i)));
        }
        return arrayList;
    }

    /**
     * 获取神兽目录
     *
     * @return
     */
    public ArrayList<String> getGodPet() {
        ArrayList<String> godPet = new ArrayList<String>();
        String[] dirList = FileUtils.toDirList("res/shape/char");
        for (int i = 0; i < dirList.length; i++) {
            if (dirList[i].startsWith("5")) {
                godPet.add(dirList[i]);
            }
        }
        return godPet;
    }

    @SuppressWarnings("serial")
    class SummonList extends AbstractListModel<String> {

        private ArrayList<String> string;

        public SummonList(ArrayList<String> allDir) {
            this.string = allDir;
        }

        @Override
        public String getElementAt(int arg0) {
            return string.get(arg0);
        }

        @Override
        public int getSize() {
            return string.size();
        }
    }
}
