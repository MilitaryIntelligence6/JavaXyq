package com.mxxy.extendpackage;

import com.mxxy.game.base.Panel;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.utils.GameColor;
import com.mxxy.game.utils.StringUtils;
import com.mxxy.game.was.Toolkit;
import com.mxxy.game.widget.Label;
import com.mxxy.game.widget.RoundLineBorder;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * SelectServer (选择服务器)
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
@SuppressWarnings("rawtypes")
final public class SelectServer extends AbstractPanelHandler {

    private static Label defaultServer_label;
    String[] strings = null;
    private Label cz, hy, zz;
    private Label scenicSpot[];
    private String[] CZscenicSpotString = {"临武", "桂阳", "嘉禾", "资兴", "汝城", "桂东"};
    private String[] HYscenicSpotString = {"衡南", "衡阳", "衡山", "衡东", "祁东"};
    private String[] ZZscenicSpotString = {"株洲", "醴陵", "攸县", "茶陵", "炎陵"};
    private Color[] color = {GameColor.red, GameColor.decode("#E32FC7"), GameColor.green,
            GameColor.decode("#B48418")};
    private ScenicSpotPanel ScenicSpotPanel;
    private Map<String, String[]> map = new HashMap<String, String[]>();
    private EmptyBorder emptyborder;
    private RoundLineBorder RoundLineBorder;

    @Override
    protected void initView() {
        cz = findViewById("cz");
        hy = findViewById("hy");
        zz = findViewById("zz");
        defaultServer_label = findViewById("defaultServer_label");
        defaultServer_label.setOpaque(true);
        defaultServer_label.setBackground(GameColor.decode("#173448"));
        defaultServer_label.setForeground(GameColor.decode("#F8E890"));
        defaultServer_label.setBorder(new CompoundBorder(new RoundLineBorder(GameColor.white, 1, 0, 0), emptyborder));
        defaultServer_label.setHorizontalAlignment(0);
        ScenicSpotPanel = new ScenicSpotPanel();
        emptyborder = new EmptyBorder(10, 10, 10, 10);
        RoundLineBorder = new RoundLineBorder(GameColor.WHITE, 1, 8, 8);
        cz.addMouseListener(this);
        hy.addMouseListener(this);
        zz.addMouseListener(this);
        map.put("cz", CZscenicSpotString);
        map.put("hy", HYscenicSpotString);
        map.put("zz", ZZscenicSpotString);
    }

    /**
     * 下一步
     *
     * @param e
     */
    public void next(ActionEvent e) {
        openSelectRole();
    }

    public void openSelectRole() {
        if (defaultServer_label.getText() == null) {
            uihelp.prompt(panel, Constant.getString("PleaseSelectServer"), 2000);
            return;
        }
        uihelp.prompt(panel, "连接 《" + defaultServer_label.getText() + "》 中请稍后......", 1000);
        Toolkit.sleep(1000);
        Panel dlg = uihelp.getPanel("SelectRole");
        uihelp.hidePanel(panel);
        uihelp.showPanel(dlg);
        iWindows.setTitle("     " + "[" + defaultServer_label.getText() + "]");
    }

    /**
     * 上一步
     *
     * @param e
     */
    public void back(ActionEvent e) {
        Panel dlg = uihelp.getPanel("LoginPager");
        uihelp.hidePanel(panel);
        uihelp.showPanel(dlg);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Label source = (Label) e.getSource();
        int index = 0;
        if (StringUtils.isAlphabet(source.getName())) {
            ScenicSpotPanel.removeAll();
            strings = map.get(source.getName());
            scenicSpot = new Label[strings.length];
            for (int i = 0; i < strings.length; i++) {
                scenicSpot[i] = new Label("", null, 0);
                scenicSpot[i].remove(scenicSpot[i]);
                scenicSpot[i].setBounds(3 + 70 * i, 0, 65, 24);
                scenicSpot[i].setBorder();
                scenicSpot[i].setName(i + "");
                scenicSpot[i].addMouseListener(this);
                scenicSpot[i].setText(strings[i]);
                scenicSpot[i].setColor(color[0]);
                ScenicSpotPanel.add(scenicSpot[i]);
            }
            panel.add(ScenicSpotPanel);
            scenicSpot[1].setColor(color[2]);
            scenicSpot[2].setColor(color[3]);
            scenicSpot[4].setColor(color[1]);
            cz.setBorder(new CompoundBorder(RoundLineBorder, emptyborder));
            hy.setBorder(new CompoundBorder(RoundLineBorder, emptyborder));
            zz.setBorder(new CompoundBorder(RoundLineBorder, emptyborder));
            source.setBorder(new CompoundBorder(new RoundLineBorder(GameColor.green, 2, 8, 8), emptyborder));
        } else {
            index = Integer.parseInt(source.getName());
        }
        defaultServer_label.setText(strings[index]);
        if (!source.getName().equals("cz") && !source.getName().equals("hy") && !source.getName().equals("zz")) {
            int clickTimes = e.getClickCount();
            if (clickTimes == MouseEvent.BUTTON1) {
                openSelectRole();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Label source = (Label) e.getSource();
        if (source == cz || source == hy || source == zz) {
            source.setForeground(GameColor.red);
        } else {
            int parseInt = Integer.parseInt(source.getName());
            scenicSpot[parseInt].setForeground(GameColor.red);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Label source = (Label) e.getSource();
        if (source == cz || source == hy || source == zz) {
            source.setForeground(GameColor.decode("#F8E890"));
        } else {
            int parseInt = Integer.parseInt(source.getName());
            scenicSpot[parseInt].setForeground(GameColor.decode("#F8E890"));
        }
    }

    /**
     * @author dell
     */
    @SuppressWarnings("serial")
    class ScenicSpotPanel extends JPanel {
        public ScenicSpotPanel() {
            super(null);
            setBounds(135, 385, 485, 64);
            setOpaque(false);
            setRequestFocusEnabled(false);
        }

        @Override
        public void paintImmediately(int x, int y, int w, int h) {
        }
    }
}
