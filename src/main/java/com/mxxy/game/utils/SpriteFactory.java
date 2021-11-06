package com.mxxy.game.utils;

import com.mxxy.game.sprite.Sprite;
import com.mxxy.game.was.Toolkit;
import com.mxxy.game.was.WASDecoder;
import com.mxxy.game.widget.Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

/**
 * 精灵工厂
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class SpriteFactory {

    public static Sprite loadSprite(String filename) {
        return loadSprite(filename, null);
    }

    public static Sprite createSprite(InputStream is) throws Exception {
        WASDecoder decoder = new WASDecoder();
        decoder.load(is);
        return createSprite(decoder);
    }

    /**
     * 创建精灵
     *
     * @param decoder
     * @return
     */
    private static Sprite createSprite(WASDecoder decoder) {
        int centerX = decoder.getRefPixelX();
        int centerY = decoder.getRefPixelY();
        Sprite sprite = new Sprite(decoder.getWidth(), decoder.getHeight(), centerX, centerY);
        int spriteCount = decoder.getAnimCount();
        int frameCount = decoder.getFrameCount();
        for (int i = 0; i < spriteCount; i++) {
            Animation anim = new Animation();
            anim.setWidth(decoder.getWidth());
            anim.setHeight(decoder.getHeight());
            for (int j = 0; j < frameCount; ) {
                try {
                    int index = i * frameCount + j;
                    BufferedImage frame = decoder.getFrame(index);
                    int delay = decoder.getDelay(index);
                    int duration = delay * 100;
                    anim.addFrame(frame, duration, centerX, centerY);
                    j += delay;
                } catch (Exception e) {
                    if ((e instanceof IndexOutOfBoundsException)) {
                        // System.err.println("解析精灵出错：frameCount大于实际值 " + frameCount + " > " +
                        // anim.getFrames().size());
                        break;
                    }
                    System.err.println("解析精灵资源文件失败！");
                    e.printStackTrace();
                    j++;
                }
            }
            sprite.addAnimation(anim);
        }
        sprite.setDirection(0);
        return sprite;
    }

    public static Animation loadAnimation(String filename, int index) {
        Sprite s = loadSprite(filename);
        return s == null ? null : s.getAnimation(index);
    }

    public static Animation loadAnimation(String filename) {
        return loadAnimation(filename, 0);
    }

    /**
     * 加载图片资源
     *
     * @param filename 路径
     * @return
     */
    public static Image loadImage(String filename) {
        if (filename.endsWith(".was")) {
            Sprite s = loadSprite(filename);
            return s == null ? null : s.getImage();
        }
        return Toolkit.createImageFromResource(filename);
    }

    /**
     * 阴影
     *
     * @return
     */
    public static Sprite loadShadow() {
        return loadSprite("res/shape/char/shadow.tcp");
    }

    public static Sprite loadSprite(String filename, int[] colorations) {
        if ((filename == null) || (filename.trim().length() == 0)) {
            return null;
        }
        try {
            WASDecoder decoder = new WASDecoder();
            InputStream inputStream = Toolkit.getInputStream(filename);
            if (inputStream == null) {
                System.err.println("找不到精灵的资源文件!" + filename);
                return null;
            }
            decoder.load(inputStream);
            if (colorations != null) {
                String pp = filename.replaceFirst("(\\w)*.tcp", "00.pp");
                decoder.loadColorationProfile(pp);
                decoder.coloration(colorations);
            }
            Sprite s = createSprite(decoder);
            if (colorations != null) {
                s.setColorations(colorations);
            }
            return s;
        } catch (Exception e) {
            System.err.println("加载精灵失败:" + filename);
            e.printStackTrace();
        }
        return null;
    }

    public static Animation getXyjAnimation(String num) {
        Animation anim = new Animation();
        Icon ico = new ImageIcon("res/npcRes/" + num + "/1.png");
        int height = ico.getIconHeight();
        int width = ico.getIconWidth();
        anim.setHeight(height);
        anim.setWidth(width);
        File path = new File("res/npcRes/" + num);
        String[] list = path.list();
        int len = list.length + 1;
        for (int i = 1; i < len; i++) {
            anim.addFrame(new ImageIcon("res/npcRes/" + num + "/" + i + ".png").getImage(), 100L, 0, 0);
        }
        return anim;
    }

    /**
     * 查找对话框NPC 头像
     *
     * @param characterId
     * @return
     */
    public static Sprite findPhoto(String characterId) {
        if (characterId.compareTo("0012") <= 0) {
            return SpriteFactory.loadSprite("res/wzife/photo/hero/" + characterId + ".tcp");
        }
        return SpriteFactory.loadSprite("res/wzife/photo/npc/" + characterId + ".tcp");
    }
}
