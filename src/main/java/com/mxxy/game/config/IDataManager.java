package com.mxxy.game.config;

import com.mxxy.game.domain.PlayerVO;
import com.mxxy.game.domain.SceneNpc;
import com.mxxy.game.domain.SceneTeleporter;
import com.mxxy.game.sprite.Players;

import java.util.List;

/**
 * 数据管理器
 *
 * @author dell
 */
public interface IDataManager {
    /**
     * findSceneNpc(查找场景对应NPC)
     *
     * @param sceneId
     * @return
     */
    public List<SceneNpc> findSceneNpc(String sceneId);

    /**
     * findJump(查找场景对应跳转点)
     *
     * @param sceneId
     * @return
     */
    public List<SceneTeleporter> findJump(String sceneId);

    /**
     * findNpcChat(查找NPC聊天内容)
     *
     * @param npcId
     * @return
     */
    public String findNpcChat(String npcId);


    public Players createPlayer(PlayerVO playerData);
}
