package com.mxxy.game.widget;

import javax.swing.border.LineBorder;
import java.awt.*;

@SuppressWarnings("serial")
/**
 * 边框
 * @author dell
 */
public class BorderUi extends LineBorder {
    public BorderUi(Color color, int thickness, boolean roundedCorners) {
        super(color, thickness, roundedCorners);
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color oldColor = g.getColor();
        Graphics2D g2 = (Graphics2D) g;
        int i;
        g2.setRenderingHints(rh);
        g2.setColor(lineColor);
        for (i = 0; i < thickness; i++) {
            if (roundedCorners) {
                g2.drawRoundRect(x, y, width - 1, height - 2, 8, 8);
            } else {
                g2.drawRect(x, y, width - 1, height - 2);// 实际中此循环语句需要修改
            }
        }
        g2.setColor(oldColor);
    }
}
