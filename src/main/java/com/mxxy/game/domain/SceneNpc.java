package com.mxxy.game.domain;

/**
 * SceneNPC
 */
public class SceneNpc {

    private int sceneId;
    private String characterId;
    private String name;
    private int sceneX;
    private int sceneY;
    private String state;
    private String describe;
    private int direction;

    /**
     * @param sceneId     场景ID
     * @param characterId 人物效果
     * @param name        姓名
     * @param sceneX      坐标
     * @param sceneY      坐标
     */
    public SceneNpc(int sceneId, String characterId, String name, int sceneX, int sceneY, String state, String describe,
                    int direction) {
        this.sceneId = sceneId;
        this.characterId = characterId;
        this.name = name;
        this.sceneX = sceneX;
        this.sceneY = sceneY;
        this.state = state;
        this.describe = describe;
        this.direction = direction;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSceneX() {
        return sceneX;
    }

    public void setSceneX(int sceneX) {
        this.sceneX = sceneX;
    }

    public int getSceneY() {
        return sceneY;
    }

    public void setSceneY(int sceneY) {
        this.sceneY = sceneY;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "SceneNpc [sceneId=" + sceneId + ", characterId=" + characterId + ", name=" + name + ", sceneX=" + sceneX
                + ", sceneY=" + sceneY + ", state=" + state + ", describe=" + describe + "]";
    }

}
