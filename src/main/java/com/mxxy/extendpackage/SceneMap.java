package com.mxxy.extendpackage;

import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.sprite.Sprite;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.SpriteImage;

/**
 * SceneMap (场景小地图)
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
@SuppressWarnings("rawtypes")
final public class SceneMap extends AbstractPanelHandler {

    private SpriteImage smap;

    @Override
    public void init(PanelEvent evt) {
        super.init(evt);
    }

    @Override
    protected void initView() {
        Sprite background = SpriteFactory.loadSprite("res/smap/" + context.getScene() + ".tcp");
        smap = new SpriteImage(background);
        panel.setBounds(Constant.WINDOW_WIDTH / 2 - background.getWidth() / 2,
                Constant.WINDOW_HEIGHT / 2 - background.getHeight() / 2, background.getWidth(), background.getHeight());
        panel.setSmapImage(smap);
    }
}
