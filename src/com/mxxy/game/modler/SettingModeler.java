package com.mxxy.game.modler;

import com.mxxy.game.config.IPropertiseManager;
import com.mxxy.game.utils.MP3Player;

public class SettingModeler {

    public void saveSetting(IPropertiseManager propertiesConfigManager, String settingName, boolean on) {
        propertiesConfigManager.put(settingName, String.valueOf(on));
        propertiesConfigManager.saveConfig();
    }

    public void stopMusic(boolean on, String string) {
        if (!on) {
            MP3Player.stopLoop();
        } else {
            if (!MP3Player.isPlayer) {
                MP3Player.loop("res/music/" + string + ".mp3");
            }
        }
    }
}
