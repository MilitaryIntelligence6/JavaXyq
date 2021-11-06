package com.mxxy.game.sprite;

import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.utils.StringUtils;

import java.awt.*;

/**
 * 法术绘制对象
 *
 * @author dell
 */
public class Magic extends AbsSprite {

    public static final String FSZS = "飞砂走石";

    public static final String LT = "龙腾";

    public static final String LTWJ = "雷霆万钧";

    public static final String JJWW = "唧唧歪歪";

    private String magincId;

    @Override
    public void draw(Graphics2D g, int x, int y) {
        if (sprite != null) {
            sprite.drawBitmap(g, x, y);
        }
    }

    @Override
    public Sprite createSprite(Players players) {
        if (!StringUtils.isBlank(this.magincId)) {
            super.sprite = SpriteFactory
                    .loadSprite("res/magic/" + players.getPalyVo().getRace() + "/" + this.magincId + ".tcp");
        }
        return super.sprite;
    }

    public void setMagincId(String magincId) {
        this.magincId = magincId;
    }

    public void onces() {
        if (sprite != null) {
            sprite.getCurrAnimation().setRepeat(1);
        }
    }
}
