package com.mxxy.game.widget;

import com.mxxy.game.sprite.Sprite;

import java.awt.*;

public class SpriteImage extends AbstractCanvas {

    private Sprite sprite;

    private boolean isVisible;

    private Image image;

    public SpriteImage(Sprite sprite) {
        this(sprite, 0, 0, sprite.getWidth(), sprite.getHeight());
    }

    public SpriteImage(Image image) {
        this.image = image;
    }

    public SpriteImage(Sprite sprite, int x, int y, int width, int height) {
        this.sprite = sprite;
        setWidth(width);
        setHeight(height);
        setX(x);
        setY(y);
        this.isVisible = true;
    }

    @Override
    public void dispose() {
        this.sprite.dispose();
    }

    @Override
    protected void draw(Graphics2D g, int x, int y, int width, int height) {
        if (this.isVisible && this.sprite != null) {
            sprite.drawBitmap(g, x, y, width, height);
        }
        if (image != null) {
            g.drawImage(image, x, y, null);
        }
    }

    public void update(long elapsedTime) {
        this.sprite.update(elapsedTime);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setRepeat(int i) {
        this.sprite.setRepeat(i);
    }
}
