package com.mxxy.game.domain;

import com.mxxy.game.sprite.Weapon;

import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * 任务配置对象 用于后期的 存档和读档
 *
 * @author ZAB
 */
public class PlayerVO implements Serializable {

    public static final String STATE_STAND = "stand";

    public static final String STATE_WALK = "walk";

    private static final long serialVersionUID = 1L;

    public String id;

    /**
     * 人物血量
     */
    private int hp;

    /**
     * 法力值;
     */
    private int mp;

    /**
     * 人物文件
     */
    private String character;

    /**
     * 人物名字
     */
    private String name;

    /**
     * 人物染色
     */
    private int[] colorations;

    /**
     * 人物状态
     */
    private String state = STATE_STAND;

    /**
     * 方向
     */
    private int direction;

    /**
     * 坐标
     */
    private Point sceneLocation;

    /**
     * 当前场景编号
     */
    private String sceneId;

    /**
     * 武器对象
     */
    private Weapon mWeapon;

    /**
     * 称谓
     */
    private String describe;

    /**
     * 金钱
     */
    private long moeny;

    /**
     * 速度
     */
    private int speed;

    /**
     * 力量
     */
    private int strength;

    /**
     * 潜力点
     */
    private int potentiality;

    /**
     * 门派标识
     * 1000人族,
     * 1001魔族,
     * 1002仙族
     */
    private String race;


    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getPotentiality() {
        return potentiality;
    }

    public void setPotentiality(int potentiality) {
        this.potentiality = potentiality;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public long getMoeny() {
        return moeny;
    }

    public void setMoeny(long moeny) {
        this.moeny = moeny;
    }

    public Weapon getmWeapon() {
        return mWeapon;
    }

    public void setmWeapon(Weapon mWeapon) {
        this.mWeapon = mWeapon;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
        if (Integer.parseInt(character) >= 1 && Integer.parseInt(character) <= 4) {
            this.setRace("1000");
        } else if (Integer.parseInt(character) >= 5 && Integer.parseInt(character) <= 8) {
            this.setRace("1001");
        } else if (Integer.parseInt(character) >= 9 && Integer.parseInt(character) <= 12) {
            this.setRace("1002");
        }
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getColorations() {
        return colorations;
    }

    public void setColorations(int[] colorations) {
        this.colorations = colorations;
    }

    public Point getSceneLocation() {
        return sceneLocation;
    }

    public void setSceneLocation(Point sceneLocation) {
        this.sceneLocation = sceneLocation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "PlayerVO [id=" + id + ", hp=" + hp + ", character=" + character
                + ", name=" + name + ", colorations="
                + Arrays.toString(colorations) + ", state=" + state
                + ", direction=" + direction + ", sceneLocation="
                + sceneLocation + ", sceneId=" + sceneId + ", mWeapon="
                + mWeapon + ", describe=" + describe + ", moeny=" + moeny
                + ", speed=" + speed + ", strength=" + strength
                + ", potentiality=" + potentiality + ", race=" + race + "]";
    }


}
