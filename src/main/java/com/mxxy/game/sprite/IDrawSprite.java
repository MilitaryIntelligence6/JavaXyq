package com.mxxy.game.sprite;

import java.awt.*;

public interface IDrawSprite {

    void update(long elapsedTime);

    void draw(Graphics2D g, int x, int y);

    Sprite createSprite(Players players);

    void resetFrames();

    void setDirection(int index);
}
