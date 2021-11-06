package com.mxxy.extendpackage;

import com.mxxy.game.base.Panel;
import com.mxxy.game.domain.PlayerVO;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.modler.SummonMolder;
import com.mxxy.game.sprite.Players;
import com.mxxy.game.sprite.Sprite;
import com.mxxy.game.ui.ContainersPanel;
import com.mxxy.game.utils.GameColor;
import com.mxxy.game.widget.Label;
import com.mxxy.game.widget.ListScrollPanel;
import com.mxxy.game.widget.ListScrollPanel.IScrollPanelListItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * SummonPager (召唤兽)
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
final public class SummonPager extends AbstractPanelHandler<SummonMolder>
        implements IScrollPanelListItem {

    private static int summonIndex;
    public Label summonName;
    public String[] state = {Players.STATE_STAND, Players.STATE_WALK, Players.STATE_ATTACK, Players.STATE_MAGIC};
    /**
     * 控制宠物状态
     */
    int stateInex = 0;
    private ListScrollPanel listscrollpanel;
    private Players summonPlayers;
    /**
     * 查看召唤兽资质
     *
     * @param event
     */
    private Panel summonAttrbute;

    @Override
    public void init(PanelEvent evt) {
        super.init(evt);
    }

    @Override
    protected void initView() {
        JList<String> list = modler.getList(propertiesConfigManager);
        listscrollpanel = new ListScrollPanel(125, 110, list);
        list.setSelectedIndex(summonIndex);
        list.setBounds(0, 0, 100, 100);
        list.setSelectionForeground(GameColor.yellow);
        listscrollpanel.setLocation(15, 47);
        listscrollpanel.setScrollPanelListItem(this);
        summonName = panel.findViewById("petName");
        summonName.setText(list.getSelectedValue());
        Label number = panel.findViewById("number");
        number.setText(list.getModel().getSize() + "/9");
        panel.add(listscrollpanel, 0);
        period = 4000;
        setAutoUpdate(true);
    }

    @Override
    protected String setConfigFileName() {
        return "value/resources.properties";
    }

    @Override
    public void getListValue(int index, String value) {
        stateInex = 0;
        summonIndex = index;
        ArrayList<String> godPet = modler.getGodPet();
        PlayerVO playerVO = new PlayerVO();
        playerVO.setCharacter(godPet.get((int) index));
        playerVO.setState(Players.STATE_STAND);
        playerVO.setDirection(Sprite.DIRECTION_BOTTOM);
        summonPlayers = dataStoreManager.createPlayer(playerVO);
        summonPlayers.setShadow(true);
        summonName.setText(value);
    }

    @Override
    public void update(PanelEvent evt) {
        super.update(evt);
        if (summonPlayers != null) {
            summonPlayers.setState(state[stateInex]);
        }
        if (stateInex >= state.length - 1) {
            stateInex = 0;
        } else {
            stateInex++;
        }
    }

    public void seeAttrbute(ActionEvent event) {
        summonAttrbute = uihelp.getPanel(event);
        showOrHide(summonAttrbute);
    }

    @Override
    public void dispose(PanelEvent evt) {
        super.dispose(evt);
        summonPlayers = null;
        panel.remove(listscrollpanel);
        if (summonAttrbute != null) {
            uihelp.hidePanel(summonAttrbute);
        }
    }

    @SuppressWarnings("serial")
    @Override
    public JPanel getContainersPanel() {
        return new ContainersPanel(105, 0, 300, 150) {
            @Override
            protected void draw(Graphics2D g, long elapsedTime) {
                if (summonPlayers != null && g != null) {
                    summonPlayers.draw(g, 100, 110);
                    summonPlayers.update(elapsedTime);
                }
            }
        };
    }
}
