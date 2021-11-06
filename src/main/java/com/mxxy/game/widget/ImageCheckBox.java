package com.mxxy.game.widget;

import javax.swing.*;

@SuppressWarnings("serial")
public class ImageCheckBox extends JCheckBox {

    public ImageCheckBox(ImageIcon icon) {
        super(icon);
        setOpaque(false);
    }

    @Override
    public void paintImmediately(int x, int y, int w, int h) {
    }
}
