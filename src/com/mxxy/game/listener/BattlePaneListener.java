package com.mxxy.game.listener;

import com.mxxy.game.sprite.Players;
import com.mxxy.game.ui.BattlePanel;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author ZAB 邮箱 ：624284779@qq.com
 */
final public class BattlePaneListener extends AbstractBaseEventListener<BattlePanel> {

    public BattlePaneListener(BattlePanel gamePanel) {
        mPanel = gamePanel;
        mPanel.setListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Players clickPlayer = null;
        // 是否点击在敌方单位上
        for (int i = 0; i < mPanel.getHostileTeam().size(); i++) {
            Players p = mPanel.getHostileTeam().get(i);
            if (p.contains(x - p.getX(), y - p.getY())) {
                clickPlayer = p;
                break;
            }
        }
        if (mPanel.isWaitingCmd() && clickPlayer != null) {
            mPanel.setTarget(clickPlayer);
            if (mPanel.isSelectMagic()) {
                mPanel.setSelectMagic(false);
                // if (selectedMagic != null) {
                mPanel.magicCmd();
                // }
            } else {
                mPanel.attackCmd();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isAltDown() && mPanel.isWaitingCmd()) {  //判断是否Alt 按下
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    mPanel.attackCmd();
                    break;
                case KeyEvent.VK_E:
                    mPanel.selectProp();
                    break;
                case KeyEvent.VK_W:
                    mPanel.selectWarmagic();
                    break;
            }
        }
    }
}
