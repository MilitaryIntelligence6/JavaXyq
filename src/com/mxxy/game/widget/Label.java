package com.mxxy.game.widget;

import com.mxxy.game.utils.GameColor;
import com.mxxy.game.utils.GraphicsUtils;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Label extends JLabel {

    private Animation animation;

    private long lastUpdateTime;

    private Rectangle bounds; // 矩形范围 bounds; //矩形范围

    private Color color;

    private JToolTip tooltip; //提示框组件

    public Label(Animation anim) {
        this(null, anim != null ? new ImageIcon(anim.getImage()) : null, LEFT);
        setAnim(anim);
    }

    public Label(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        setIgnoreRepaint(true);
        setBorder(null);
        setFont(new Font("黑体", Font.PLAIN, 13));
        setForeground(Color.WHITE);
    }

    public Label(String text) {
        this(text, null, LEFT);
    }

    public void setAnim(Animation anim) {
        this.animation = anim;
        if (anim != null) {
            this.lastUpdateTime = System.currentTimeMillis();
            this.setSize(anim.getWidth(), anim.getHeight());
            this.setHorizontalAlignment(SwingConstants.CENTER);
            this.setVerticalAlignment(SwingConstants.CENTER);
        } else {
            setIcon(null);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        GraphicsUtils.setAntialiasAll(g, true);
        if (animation != null) {
            animation.update(System.currentTimeMillis() - lastUpdateTime);
            lastUpdateTime = System.currentTimeMillis();
            setIcon(new ImageIcon(animation.getImage()));
        }
        drawColorLenvl(g);
    }

    public JToolTip getTooltip() {
        if (this.tooltip == null) {
            LucidToolTip tip = new LucidToolTip();
            tip.setComponent(this);
            this.tooltip = tip;
        }
        return tooltip;
    }

    @Override
    public Point getToolTipLocation(MouseEvent evt) {
        try {
            Point p = evt.getPoint();
            p.move(30, 25);
            Component win = getWindow();
            if (win != null) {
                SwingUtilities.convertPointToScreen(p, this);
                Point winLoc = win.getLocationOnScreen();
                Dimension tipSize = this.getTooltip().getPreferredSize();
                if (p.x + tipSize.width > winLoc.x + win.getWidth() - 10) {
                    p.x = winLoc.x + win.getWidth() - tipSize.width - 10;
                }
                if (p.y + tipSize.height > winLoc.y + win.getHeight() - 10) {
                    p.y = winLoc.y + win.getHeight() - tipSize.height - 10;
                }
                SwingUtilities.convertPointFromScreen(p, this);
            }
            return p;
        } catch (Exception e) {
//			 e.printStackTrace();
        }
        return super.getToolTipLocation(evt);
    }


    private Component getWindow() {
        Container parent = this.getParent();
        while (parent != null && !(parent instanceof RootPaneContainer)) {
            parent = parent.getParent();
        }
        return parent;
    }


    /**
     * 绘制服务器矩形
     *
     * @param g
     */
    public void drawColorLenvl(Graphics g) {
        if (color != null) {
            g.setColor(color);
            g.fillRect(5, getHeight() - 4, getWidth() - 10, 2);
            g.setColor(null);
        }
    }

    public void setRectangleBounds(int x, int y) {
        this.bounds = new Rectangle(x, y, getWidth(), getHeight());
    }

    public boolean hit(Point p) {
        return this.bounds.contains(p);
    }

    /**
     * 设置边框
     */
    public void setBorder() {
        setForeground(GameColor.decode("#F8E890"));
        setBorder(new CompoundBorder(new RoundLineBorder(Color.WHITE, 1, 8, 8), new EmptyBorder(10, 10, 10, 10)));
    }

    public void setColor(Color p) {
        this.color = p;
    }

    @Override
    public void paintImmediately(int x, int y, int w, int h) {
    }
}
