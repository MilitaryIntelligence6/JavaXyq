package com.mxxy.extendpackage;

import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;

public class MessageNotificationPanel extends AbstractPanelHandler {

    @Override
    protected void initView() {
        period = 100;
        setAutoUpdate(true);
    }


    @Override
    public void update(PanelEvent evt) {
    }
}
