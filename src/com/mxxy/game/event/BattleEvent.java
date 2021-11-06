package com.mxxy.game.event;

import com.mxxy.game.ui.BattlePanel;

import java.util.EventObject;

public class BattleEvent extends EventObject {

    public static final int BATTLE_WIN = 0x01;
    public static final int BATTLE_DEFEATED = 0x02;
    public static final int BATTLE_TIMEOUT = 0x03;
    public static final int BATTLE_BREAK = 0x04;

    private BattlePanel battlePanel;

    private int id;

    public BattleEvent(BattlePanel source, int id) {
        super(source);
        this.battlePanel = source;
        this.id = id;

    }

    public BattlePanel getBattlePanel() {
        return battlePanel;
    }

    public int getId() {
        return id;
    }
}
