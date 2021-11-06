package com.mxxy.game.domain;

import java.util.Date;

public class Profile {
    /**
     * 人物属性
     */
    private PlayerVO playerVO;
    /**
     * 地图场景
     */
    private String sceneId;

    private String filename;
    /**
     * 时间
     */
    private Date createDate;

    public PlayerVO getPlayerVO() {
        return playerVO;
    }

    public void setPlayerVO(PlayerVO playerVO) {
        this.playerVO = playerVO;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Profile [playerVO=" + playerVO + ", sceneId=" + sceneId + ", filename=" + filename + ", createDate="
                + createDate + "]";
    }

}
