package com.mxxy.game.ui;

import com.mxxy.game.base.AbstactPanel;
import com.mxxy.game.utils.GraphicsUtils;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.BorderUi;
import com.mxxy.game.widget.Label;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class EmoticonPanel extends AbstactPanel {

    private Label[] emoticonSymbol;

    @Override
    public void init() {
        super.stopDraw();
        emoticonSymbol = new Label[60];
        initEmoticonSymbol();
    }

    public void initEmoticonSymbol() {
        for (int i = 0; i < emoticonSymbol.length; i++) {
            emoticonSymbol[i] = new Label(SpriteFactory.loadAnimation("res/wzife/emoticons/#" + i + ".was"));
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                emoticonSymbol[i * 10 + j].setLocation(10 + j * 44, 18 + i * 44);
                emoticonSymbol[i * 10 + j].setHorizontalAlignment(SwingConstants.CENTER);
                emoticonSymbol[i * 10 + j].setSize(45, 45);
                emoticonSymbol[i * 10 + j].setName(String.valueOf(i * 10 + j));
                add(emoticonSymbol[i * 10 + j]);
            }
        }
        setBorder(new BorderUi(Color.decode("#808080"), 5, true));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g.create();
        GraphicsUtils.setAlpha(graphics2d, 0.5f);
        graphics2d.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
