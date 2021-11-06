package com.mxxy.game.ui;

import com.mxxy.game.resources.Constant;
import com.mxxy.game.utils.GameColor;
import com.mxxy.game.utils.GraphicsUtils;
import com.mxxy.game.was.Toolkit;

import javax.swing.*;
import java.awt.*;

public class Announcer extends JPanel {

    private StringBuilder stringBuilder = new StringBuilder();

    private int textLocation;

    private String text;

    public Announcer() {
        super(null);
        text = "我是测试文字,我是测试文字，我是测试文字,我是测试文字，我是测试文字,我是测试文字，我是测试文字,我是测试文字，我是测试文字,我是测试文字";
        setSize(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g.create();
        GraphicsUtils.setAlpha(graphics2d, 0.8f);
        graphics2d.fillRect(0, 0, getWidth(), getHeight());
        GraphicsUtils.setAntialias(g, true);
        g.setColor(GameColor.white);
        Font font = new Font("黑体", Font.PLAIN, 15);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        String[] split = text.split("H");
        if (textLocation < text.length() - 1) {
            stringBuilder.append(text.substring(textLocation, textLocation + 1));
            g.drawString(stringBuilder.toString(), 10, 10);
            textLocation++;
            Toolkit.sleep(30);
            this.repaint();
        } else {
            g.drawString(stringBuilder.toString(), 10, 10);
        }
    }
}
