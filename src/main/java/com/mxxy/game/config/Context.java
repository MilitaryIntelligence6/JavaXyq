package com.mxxy.game.config;

import com.mxxy.game.sprite.Players;
import com.mxxy.game.ui.IWindows;
import com.mxxy.net.IClient;

/**
 * @author javaman
 */
public class Context {
    private Players player;
    private String scene;
    private IWindows windows;
    private IClient client;

    public IClient getClient() {
        return client;
    }

    public void setClient(IClient client) {
        this.client = client;
    }

    public IWindows getWindows() {
        return windows;
    }

    public void setWindows(IWindows windows) {
        this.windows = windows;
    }

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String screen) {
        this.scene = screen;
    }
}
