package com.mxxy.game.widget;

import com.mxxy.game.modler.MagicModle.MagicConfig;
import com.mxxy.game.sprite.Sprite;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.util.List;

/**
 * 自定义Button
 *
 * @author dell
 */
@SuppressWarnings("serial")
public class ImageComponentButton extends JButton {
    List<AnimationFrame> frames;
    /**
     * 按钮按下时是否自动向右下偏移
     */
    private boolean autoOffset = false;
    private boolean enableds;
    private MagicConfig magicConfig;

    public ImageComponentButton() {
        init();
    }

    public ImageComponentButton(Action action) {
        super(action);
    }

    /**
     * 设置一个包括4帧图片的数组作为按钮的4种状态,0-3依次为normal,pressed,rollover,disabled
     *
     * @param frames
     */
    public ImageComponentButton(int width, int height, List<AnimationFrame> frames) {
        setSize(width, height);
        loadAnimation(frames);
    }

    public ImageComponentButton(Sprite sprite) {
        init(sprite);
    }

    public void init() {
        setUI(new CustomButtonUI());
        setForeground(Color.WHITE);
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        setHorizontalAlignment(JButton.CENTER);
        setVerticalAlignment(JButton.CENTER);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setIgnoreRepaint(true);
        setBorder(null);
        setFocusable(false);
    }

    private void loadAnimation(List<AnimationFrame> frames) {
        init();
        try {
            int frameCount = frames.size();
            this.setAutoOffset(frameCount < 3);
            if (frameCount > 0) {
                setIcon(new ImageIcon(frames.get(0).getImage()));
            }
            if (frameCount > 1) {
                setPressedIcon(new ImageIcon(frames.get(1).getImage()));
            }
            if (frameCount > 2) {
                setRolloverIcon(new ImageIcon(frames.get(2).getImage()));
            }
            if (frameCount > 3) {
                setDisabledIcon(new ImageIcon(frames.get(3).getImage()));
            }
        } catch (Exception e) {
            System.err.println("创建Button失败!");
            e.printStackTrace();
        }
    }

    public void loadAnimation(ImageIcon[] arr) {
        init();
        try {
            int frameCount = arr.length;
            if (frameCount > 0) {
                setIcon(arr[0]);
            }
            if (frameCount > 1) {
                setPressedIcon(arr[1]);
            }
            if (frameCount > 2) {
                setRolloverIcon(arr[2]);
            }
            if (frameCount > 3) {
                setDisabledIcon(arr[3]);
            }
        } catch (Exception e) {
            System.err.println("创建Button失败!");
            e.printStackTrace();
        }
    }

    public void start(int i) {
        setIcon(new ImageIcon(frames.get(i).getImage()));
    }

    public List<AnimationFrame> getFrames() {
        return frames;
    }

    public void init(Sprite sprite) {
        setSize(sprite.getWidth(), sprite.getHeight());
        frames = sprite.getAnimation(0).getFrames();
        loadAnimation(frames);
    }

    public boolean isAutoOffset() {
        return autoOffset;
    }

    /**
     * 按钮按下时是否自动向右下偏移
     */
    public void setAutoOffset(boolean autoOffset) {
        this.autoOffset = autoOffset;
    }

    public boolean isEnableds() {
        return enableds;
    }

    public void setEnableds(boolean enableds) {
        this.enableds = enableds;
    }

    public MagicConfig getMagicConfig() {
        return magicConfig;
    }

    public void setMagicConfig(MagicConfig magicConfig) {
        this.magicConfig = magicConfig;
    }

    // 立即绘制
    @Override
    public void paintImmediately(int x, int y, int w, int h) {
    }

    /**
     * 点击时效果
     *
     * @author dell
     */
    static class CustomButtonUI extends BasicButtonUI {
        @Override
        protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect) {
            ImageComponentButton btn = (ImageComponentButton) c;
            ButtonModel model = btn.getModel();
            if (btn.isAutoOffset() && model.isArmed() && model.isPressed()) {
                defaultTextShiftOffset = 1; // down
            } else {
                defaultTextShiftOffset = 0;
            }
            setTextShiftOffset();
            super.paintIcon(g, c, iconRect);
        }

        protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
            AbstractButton b = (AbstractButton) c;
            ButtonModel model = b.getModel();
            if (model.isArmed() && model.isPressed()) {
                defaultTextShiftOffset = 1; // down
            } else {
                defaultTextShiftOffset = 0;
            }
            setTextShiftOffset();
            super.paintText(g, c, textRect, text);
        }
    }

}
