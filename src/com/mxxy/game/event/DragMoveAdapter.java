package com.mxxy.game.event;

import com.mxxy.extendpackage.GamePager;
import com.mxxy.game.domain.SnapObject;
import com.mxxy.game.listener.AbstractBaseEventListener;
import com.mxxy.game.utils.UIHelp;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DragMoveAdapter extends AbstractBaseEventListener<GamePager> {

    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int TOP = 3;
    public static final int BOTTOM = 4;
    private static final int SNAP = 30;// 吸附的象素
    private Component me;
    private int startX, startY;
    private Rectangle myBound, otherBound;
    private UIHelp uiHelp;

    public DragMoveAdapter(Component com, UIHelp uihelp, GamePager gamePanel) {
        this.me = com;
        myBound = com.getBounds();
        otherBound = new Rectangle();
        this.uiHelp = uihelp;
        mPanel = gamePanel;
        mPanel.setListener(this);
    }

    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Container parent = mPanel.getPanel().getParent();
        Point p = e.getPoint();
        int x = mPanel.getPanel().getX();
        int y = mPanel.getPanel().getY();
        MouseEvent event = new MouseEvent(parent, MouseEvent.MOUSE_CLICKED,
                System.currentTimeMillis(), e.getModifiers(), x + p.x, y + p.y,
                e.getClickCount(), false);
        parent.dispatchEvent(event);

    }

    public void mousePressed(MouseEvent e) {

        if (e.getButton() != MouseEvent.BUTTON1) {
            return;
        }
        if (me == uiHelp.getTopParent()) {
            uiHelp.updateComponentSnap();
        }
        uiHelp.updateDistance();
        startX = e.getX();
        startY = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1) {
            return;
        }
        // 如果是主窗口就免了
        if (me == uiHelp.getTopParent()) {
            return;
        }
        getSnapObject();
        uiHelp.updateComponentSnap();

    }

    public void mouseDragged(MouseEvent e) {
        int endX = e.getX();
        int endY = e.getY();
        int moveX = endX - startX;
        int moveY = endY - startY;
        Point p = me.getLocation();

        me.setLocation(p.x + moveX, p.y + moveY);
    }

    private int getDirection(int dis) {
        int x1 = (int) myBound.getCenterX();
        int y1 = (int) myBound.getCenterY();
        int x2 = (int) otherBound.getCenterX();
        int y2 = (int) otherBound.getCenterY();
        int abs = Math.abs(x1 - x2 - myBound.width / 2 - otherBound.width / 2
                - dis);
        if (abs < 3) {
            return RIGHT;
        }
        abs = Math
                .abs(x2 - x1 - myBound.width / 2 - otherBound.width / 2 - dis);
        if (abs < 3) {
            return LEFT;
        }
        abs = Math.abs(y1 - y2 - myBound.height / 2 - otherBound.height / 2
                - dis);
        if (abs < 3) {
            return BOTTOM;
        }
        abs = Math.abs(y2 - y1 - myBound.height / 2 - otherBound.height / 2
                - dis);
        if (abs < 3) {
            return TOP;
        }
        return -1;
    }

    private void changeLocation(SnapObject obj) {
        Component com = obj.getCom();
        int location = obj.getLocation();
        int x, y;
        switch (location) {
            case LEFT:
                x = com.getX() - me.getWidth();
                y = me.getY();
                break;
            case RIGHT:
                x = com.getX() + com.getWidth();
                y = me.getY();
                break;
            case TOP:
                x = me.getX();
                y = com.getY() - me.getHeight();
                break;
            case BOTTOM:
                x = me.getX();
                y = com.getY() + com.getHeight();
                break;
            default:
                x = me.getX();
                y = me.getY();
                break;
        }
        me.setLocation(x, y);
    }

    /**
     * 得到应该吸附到的窗口
     *
     * @return
     */
    public void getSnapObject() {
        me.getBounds(myBound);
        Component c1 = uiHelp.getDialog();
        if (c1 != null && c1 != me && c1.isShowing()) {
            c1.getBounds(otherBound);
            int dis = UIHelp.getDistance(myBound, otherBound);
            if (dis > 0 && dis < SNAP) {
                int dir = getDirection(dis);
                if (dir != -1) {
                    SnapObject obj = new SnapObject(dir, c1);
                    changeLocation(obj);
                }
            }
        }
    }
}
