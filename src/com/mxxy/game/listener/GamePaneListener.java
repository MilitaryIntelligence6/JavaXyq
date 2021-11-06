package com.mxxy.game.listener;

import com.mxxy.game.event.PlayerEvent;
import com.mxxy.game.sprite.Players;
import com.mxxy.game.ui.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class GamePaneListener extends AbstractBaseEventListener<GamePanel> {

    public GamePaneListener(GamePanel gamePanel) {
        mPanel = gamePanel;
        mPanel.setListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Players player = mPanel.getPlayer();
        if (e.getButton() == MouseEvent.BUTTON1) { // 鼠标左击
            Point p = e.getPoint();
            mPanel.click(e.getPoint());
            mPanel.requestFocus(true);
            Point coords = mPanel.viewToScene(p);
            player.fireEvent(new PlayerEvent(player, PlayerEvent.WALK, coords)); // 触发移动事件
            List<Players> npcs = mPanel.getNpcList();
            for (int i = 0; i < npcs.size(); i++) {
                Players npc = npcs.get(i);
                if (mPanel.isHover(npc)) {// 判断鼠标是否在对话框上面触发谈话事件
                    PlayerEvent playerEvent = new PlayerEvent(npc, PlayerEvent.TALK);
                    playerEvent.setAttributes("UIHELP", mPanel.getUIHelp());
                    npc.fireEvent(playerEvent);
                    return;
                }
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) { // 右击
            player.stop(false);
            player.changeDirection(e.getPoint());
        }
    }
}
