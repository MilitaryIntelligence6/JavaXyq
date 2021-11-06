package com.mxxy.game.ui;

import com.mxxy.game.base.DrawPaneImp;
import com.mxxy.game.sprite.Cursor;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.Animation;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 登录面板
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
@SuppressWarnings("serial")
public class LoginPanel extends DrawPaneImp {
    private Animation[] Learn;// 取经四兄弟
    private int startX;// 登录面板滚动屏右边图片位置
    private int right = 10;// 登陆面板滚动屏右边图片序号
    private int left = 9;// 登陆面板滚动屏左边图片序号

    @Override
    public void init() {
        initLearn();
        setGameCursor(Cursor.DEFAULT_CURSOR);
    }

    @Override
    public void drawBitmap(Graphics2D g, long elapsedTime) {
        g.clearRect(0, 0, getWidth(), getHeight());
        drawBackground(g);
        drawtLearn(g, elapsedTime);
    }

    public void initLearn() {
        Learn = new Animation[4];
        for (int i = 0; i < Learn.length; i++) {
            Learn[i] = SpriteFactory.getXyjAnimation("000" + (i * 1 + 2));
        }
    }

    public void drawtLearn(Graphics2D g, long elapsedTime) {
        Learn[0].setLocation(80, 360);
        Learn[1].setLocation(230, 300);
        Learn[2].setLocation(180, 400);
        Learn[3].setLocation(430, 385);
        for (int i = 0; i < Learn.length; i++) {
            Learn[i].update(elapsedTime);
            Learn[i].drawBitmap(g, Learn[i].getX(), Learn[i].getY());
        }
    }

    /**
     * 绘制背景移动
     *
     * @param g
     */
    private void drawBackground(Graphics g) {
        startX = startX + 2;
        if (startX >= 800) {
            right -= 1;
            left -= 1;
            startX = 0;
            if (left == 0) {
                left = 10;
            }
            if (right == 0) {
                right = 10;
            }
        }
        Image map1 = new ImageIcon("res/mapRes/0001/" + right + ".jpg").getImage();
        Image map2 = new ImageIcon("res/mapRes/0001/" + left + ".jpg").getImage();
        g.drawImage(map1, startX, 0, null);
        int start = startX - 800;
        g.drawImage(map2, start, 0, null);
    }

    @Override
    protected String getMusic() {
        String[] music = {"res/music/1514.mp3", "res/music/1070.mp3", "res/music/1091.mp3"};
        return (music[new Random().nextInt(music.length)]);
    }
}
