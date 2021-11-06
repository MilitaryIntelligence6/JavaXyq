package com.mxxy.game.resources;

import com.mxxy.game.utils.OSinfo;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * 游戏常量
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class Constant {
    /**
     * 窗体大小
     */
    public static final int WINDOW_WIDTH = 806;
    public static final int WINDOW_HEIGHT = 600;
    public static final float PLAYER_RUNAWAY = 0.14f;  //逃跑
    public static final float BATTLE_PLAYER_RUNAWAY = 0.12f; //攻击
    /**
     * 邮箱正则
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)" +
            "+[a-zA-Z]{2,}$";
    /**
     * 字体及颜色
     */
    public static final Font TEXT_NAME_FONT = new Font("黑体", Font.PLAIN, 16);
    public static final Font TEXT_MOUNT_FONT = new Font("黑体", Font.PLAIN, 14);
    public static final Color TEXT_NAME_NPC_COLOR = Color.yellow;
    public static final Color PLAYER_NAME_COLOR = new Color(118, 229, 128);
    public static final Color DESCRIBE_COLOR = Color.decode("#218EFC");
    public static final Font PROMPT_FONT = new Font("宋体", Font.PLAIN, 14);
    /**
     * 东海湾
     */
    public static final String SCENE_DHW = "1506";
    /**
     * 傲来国
     */
    public static final String SCENE_ALG = "1092";
    /**
     * 傲来国武器店
     */
    public static final String SCENE_ALG_WEAPON = "1101";
    /**
     * 傲来国药店
     */
    public static final String SCENE_ALG_PHARMACY = "1104";
    /**
     * 化生寺
     */
    public static final String SCENE_HSS = "1002";
    /**
     * 水帘洞
     */
    public static final String SCENE_SLD = "1103";
    /**
     * 五庄观
     */
    public static final String SCENE_WZG = "1146";

    /**
     * scene
     */
    /**
     * 五庄观
     */
    public static final String SCENE_WZG_BS = "1147";
    /**
     * 花果山
     */
    public static final String SCENE_HGS = "1514";
    /**
     * 长安城
     */
    public static final String SCENE_CAC = "1001";
    /**
     * 长寿村
     */
    public static final String SCENE_CSC = "1070";
    /**
     * 建邺城
     */
    public static final String SCENE_JYC = "1501";
    /**
     * 朱紫国
     */
    public static final String SCENE_ZZG = "1208";
    /**
     * 西凉女国
     */
    public static final String SCENE_XL = "1040";
    /**
     * 北俱芦洲
     */
    public static final String SCENE_BJ = "1174";
    /**
     * 药店老板
     */
    public static final String NPC_PHARMACY_BOSS = "3002";
    /**
     * 东海湾传送
     */
    public static final String NPC_DHW_DELIVERY = "3307";
    /**
     * 超级巫医 和林老汉
     */
    public static final String NPC_DHW_LLH = "3001";
    /**
     * 五庄观道童
     */
    public static final String NPC_WZZ_DELIVERY = "3044";
    /**
     * 镇远大仙
     */
    public static final String NPC_WZZ_Teacher = "3062";
    public static final String NPC_HSS_HESHANG = "3049";
    public static final String NPC_ALG_WEAPON_TT = "3017";
    /**
     * NPC
     */
    /**
     * 逍遥生
     */
    public static final String PLAYERS_XYS = "0001";
    /**
     * 剑侠客
     */
    public static final String PLAYERS_JYK = "0002";
    /**
     * 飞燕女
     */
    public static final String PLAYERS_FYN = "0003";
    /**
     * 英女侠
     */
    public static final String PLAYERS_YNX = "0004";
    /**
     * 巨魔王
     */
    public static final String PLAYERS_JMW = "0005";
    /**
     * 虎头怪
     */
    public static final String PLAYERS_HTG = "0006";
    /**
     * 狐美人
     */
    public static final String PLAYERS_HMR = "0007";
    /**
     * PlayersCharacter
     */
    /**
     * 骨精灵
     */
    public static final String PLAYERS_GJL = "0008";
    /**
     * 神天兵
     */
    public static final String PLAYERS_STB = "0009";
    /**
     * 龙太子
     */
    public static final String PLAYERS_LTZ = "0010";
    /**
     * 舞天姬
     */
    public static final String PLAYERS_WTJ = "0011";
    /**
     * 玄彩娥
     */
    public static final String PLAYERS_XCE = "0012";
    /**
     * 东海湾怪物
     */
    public static final String[] SCENE_DHW_ELFS = {"2036", "2037", "2010", "2011", "2012"};
    public static final String[] DHW_ELFNAMES = {"大海龟", "巨蛙", "树怪", "蝴蝶仙子", "花妖"};
    /**
     * 小西天怪物
     */
    public static final String[] XXT_ELFS = {"4001", "4002", "4003", "4004", "4005"};
    public static final String[] XXT_ELFNAMES = {"大力金刚", "灵鹤", "雾中仙 ", "炎魔神", "夜罗刹"};
    /**
     * 地府5怪物
     */
    public static final String[] DF_ELFS = {"6001", "6002", "6003"};
    public static final String[] DF_ELFNAMES = {"吸血鬼", "鬼将", "幽灵"};
    /**
     * 坐骑
     */
    public static final String[] MOUNT_CHARACTER = {"0200", "0201", "0202", "0203", "0204"};
    public static final String[] MOUNT_NAME = {"小毛驴", "神奇小龟", "闲云野鹤", "披甲战狼", "魔力斗兽"};
    /**
     * 神兽
     */
    public static final String[] GODPET_CHARACTER = {"5001", "5002", "5003", "5004", "5005", "5005", "5006", "5007"};
    public static final String[] GODPET_NAME = {"超级白泽", "超级泡泡", "超级赤焰兽", "超级神虎", "超级神牛",};
    /**
     * 遇怪周期
     */
    public static final String LAST_PATROL_TIME = "LastPatrolTime";
    /**
     * 人族 ,魔族,仙族
     */
    public static final String HUMAN = "1000";
    public static final String DEMON = "1001";
    public static final String IMMORTAL = "1002";
    /**
     * 游戏帧率 (值越高绘制越快)
     */
    public static int FPS = 30;
    /**
     * PlayerDeviation(人物移动偏移量)
     */
    public static float PLAYER_SPEED = 0.13f; //正常
    /**
     * 适配MAC
     */
    public static String flie_spance = OSinfo.isMacOSX() ? "/" : "\\";
    public static String currentDir = System.getProperty("user.dir");
    public static Map<String, Object> props = new HashMap<>();
    /**
     * 游戏文本文字配置
     */
    private static ResourceBundle rb = ResourceBundle.getBundle("gametext");

    static {
        props.put(LAST_PATROL_TIME, System.currentTimeMillis());
    }

    public static String getString(String key) {
        try {
            return new String(rb.getString(key).getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 邮箱正则
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    public static void setProps(String name, Object value) {
        props.put(name, value);
    }

    /**
     * 登录状态枚举
     */
    public enum LoginStatus {
        /**
         * 账号密码错误
         */
        PASSWORDANDUSER_ERR,
        /**
         * 账号密码为空
         */
        PASSWORDANDUSER_EMPTY,
        /**
         * 登录成功
         */
        SUCCESS,
        ;
    }

    /**
     * 注册枚举
     *
     * @author dell
     */
    public enum RegistStatus {
        /**
         * 注册成功
         */
        SUCCESS,
        /**
         * 密码为空
         */
        PASSWORD_ISEMPTY,
        /**
         * 两次密码不一致
         */
        CHECK_PASSWORD,
        /**
         * 格式错误
         */
        USER_PATTERN_ERR,
        /**
         * 用户名为空
         */
        USER_ISEMPTY
    }

    public enum NetMessage {
        LOGIN,
        PLATERMOVE,
        ;
    }

    /**
     * 系统常量
     *
     * @author dell
     */
    public enum EPlatform {
        Linux("Linux"),

        Mac_OS("Mac OS"),

        Mac_OS_X("Mac OS X"),

        Windows("Windows"),

        SunOS("SunOS");

        private String description;

        private EPlatform(String desc) {
            this.description = desc;
        }

        @Override
        public String toString() {
            return description;
        }
    }
}
