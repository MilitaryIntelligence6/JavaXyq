package com.mxxy.game.ui;

import com.mxxy.game.base.AbstactPanel;
import com.mxxy.game.sprite.Sprite;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.was.Toolkit;
import com.mxxy.game.widget.RichLabel;
import com.mxxy.game.widget.SpriteImage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 谈话面板
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
@SuppressWarnings({"serial"})
public class TalkPanel extends AbstactPanel implements MouseListener {

    private SpriteImage background;

    private String character;

    @Override
    public void init() {
        background = new SpriteImage(SpriteFactory.loadSprite("/wzife/dialog/npcchat.tcp"));
        background.setLocation(0, 0);
        this.addMouseListener(this);
    }

    @Override
    public void drawComponent(Graphics2D graphics2d, long elapsedTime2) {
        background.drawBitmap(graphics2d, background.getX(), background.getY());
        if (this.character != null) {
            Sprite s = SpriteFactory.findPhoto(character);
            Shape oldclip = graphics2d.getClip();
            graphics2d.translate(-getX(), -getY());
            graphics2d.setClip(0, 0, 640, 480);
            s.drawBitmap(graphics2d, getX() + 12, getY() - s.getHeight());
            graphics2d.translate(getX(), getY());
            graphics2d.setClip(oldclip);
        }
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            // GameMain.getContainer().remove(this);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void paintImmediately(int x, int y, int w, int h) {

    }

    public void initTalk(String text) {
        RichLabel lblText = Toolkit.getInstance().createRichLabel(16, 30, 450, 130, text);
        this.add(lblText);
    }
}
