package com.mxxy.extendpackage;

import com.mxxy.game.base.AbstactPanel;
import com.mxxy.game.base.Panel;
import com.mxxy.game.event.DragMoveAdapter;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.listener.ISetOnListener;
import com.mxxy.game.ui.BattlePanel;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.ImageComponentButton;
import com.mxxy.game.widget.Label;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * GamePager (游戏)
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
final public class GamePager extends AbstractPanelHandler implements ISetOnListener<DragMoveAdapter> {

    private Label label;

    private ImageComponentButton playerCharacter;

    private Label sceneXY, sceneName, sceneTimer;

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void init(PanelEvent evt) {
        super.init(evt);
    }

    public List<ImageComponentButton> getAllImageButtons() {
        Component[] components = panel.getComponents();
        List<ImageComponentButton> labels = new ArrayList<ImageComponentButton>();
        for (Component c : components) {
            if (c instanceof ImageComponentButton) {
                labels.add((ImageComponentButton) c);
            }
        }
        return labels;
    }

    @Override
    protected void initView() {
        if (context.getWindows().getPanel() instanceof BattlePanel) {
            for (int i = 0; i < getAllImageButtons().size(); i++) {
                ImageComponentButton imageComponentButton = getAllImageButtons().get(i);
                imageComponentButton.setEnabled(imageComponentButton.isEnableds());
            }
            panel.removeMouseListener(this);
        }

        label = findViewById("leftdown");
        playerCharacter = findViewById("PlayerAttribute");
        addComponent();
        period = 100;
        new DragMoveAdapter(iWindows.getFrame(), uihelp, this);
        setAutoUpdate(true);
    }

    @Override
    public void update(PanelEvent evt) {
        updateSceneXY();
    }

    /**
     * 更新场景坐标
     */
    public void updateSceneXY() {
        Point sceneLocation = player.getSceneLocation();
        String XY = "X:" + sceneLocation.x + " Y:" + sceneLocation.y;
        sceneXY.setText(XY);
    }

    /**
     * 添加组件
     */
    public void addComponent() {
        Date currentTime = new Date();
        String format2 = format.format(currentTime);
        sceneTimer = new Label(format2, null, Label.LEFT);
        sceneTimer.setBounds(30, 2, 100, 15);
        AbstactPanel panel2 = (AbstactPanel) context.getWindows().getPanel();
        sceneName = new Label(panel2.getSceneName(), null, Label.LEFT);
        sceneName.setBounds(36, 15, 100, 15);
        playerCharacter.init(SpriteFactory.loadSprite("res/wzife/photo/facesmall/" + player.getPalyVo().getCharacter() + ".tcp"));
        sceneXY = new Label(null, null, Label.LEFT);
        sceneXY.setBounds(25, 65, 100, 15);
        panel.add(label, 0);
        panel.add(sceneTimer, 0);
        panel.add(sceneName, 0);
        panel.add(sceneXY, 0);
    }

    /**
     * 道具 坐骑
     *
     * @param e
     */
    public void openProp(ActionEvent e) {
        Panel proppager = uihelp.getPanel(e);
        Panel palyer = uihelp.getPanel("PlayerPager");
        if (proppager.isShowing()) {
            uihelp.hidePanel(proppager);
            uihelp.hidePanel(palyer);
        } else {
            uihelp.showPanel(proppager);
            uihelp.showPanel(palyer);
        }
    }


    public void actionOpenPager(ActionEvent e) {
        Panel openPet = uihelp.getPanel(e);
        showOrHide(openPet);
    }

    /**
     * 设置面板
     *
     * @param e
     */
    public void openSetting(ActionEvent e) {
        Panel oldpanel = uihelp.getPanel(e);
        oldpanel.setAttributes("screenId", context.getScene());
        showOrHide(oldpanel);
    }

    @Override
    public void setListener(DragMoveAdapter event) {
        panel.addMouseListener(event);
        panel.addMouseMotionListener(event);
    }

    @Override
    public void removeListener(DragMoveAdapter event) {
        panel.removeMouseMotionListener(event);
        panel.removeMouseListener(event);
    }
}
