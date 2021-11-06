package com.mxxy.game.modler;

import com.mxxy.game.resources.Constant;

public class MagicModle {

    /**
     * 获取门派对应的法术
     *
     * @param string
     * @return
     */
    public MagicConfig[] getSchoolMagic(String string) {
        MagicConfig[] magic = null;
        switch (string) {
            case Constant.HUMAN:
                magic = new MagicConfig[]{new MagicConfig("唧唧歪歪", MagicConfig.SINGLEGROUPMAGIC, Constant.HUMAN),
                        new MagicConfig("横扫千军", MagicConfig.REPEATMAGINC, Constant.HUMAN, 3),};
                break;
            case Constant.DEMON:
                magic = new MagicConfig[]{new MagicConfig("飞砂走石", MagicConfig.BIGMAGIC, Constant.DEMON),
                        new MagicConfig("鹰击", MagicConfig.GROUPATTACK, Constant.DEMON),
                        new MagicConfig("三味真火", MagicConfig.SINGLEMAGIC, Constant.DEMON),
                        new MagicConfig("天罗地网", MagicConfig.SINGLEGROUPMAGIC, Constant.DEMON)};
                break;
            case Constant.IMMORTAL:
                magic = new MagicConfig[]{new MagicConfig("龙腾", MagicConfig.SINGLEMAGIC, Constant.IMMORTAL),
                        new MagicConfig("雷霆万钧", MagicConfig.BIGMAGIC, Constant.IMMORTAL),
                        new MagicConfig("双龙戏珠", MagicConfig.BIGMAGIC, Constant.IMMORTAL),
                        new MagicConfig("龙卷雨击", MagicConfig.BIGMAGIC, Constant.IMMORTAL)};
                break;
        }
        return magic;
    }

    public class MagicConfig {

        public static final int BIGMAGIC = 0x00; // 群法
        public static final int SINGLEMAGIC = 0x01; // 单法
        public static final int SINGLEGROUPMAGIC = 0x02; // 单群法
        public static final int REPEATMAGINC = 0x03;// 重复法术
        public static final int GROUPATTACK = 0x04;// 攻击法术
        private String name;
        private String race;
        private int magicId;

        private int repeatCount;

        /**
         * @param name
         * @param magicId 0 大群法,1单法,2单群法 ,3重复法术
         */
        public MagicConfig(String name, int magicId, String race) {
            super();
            this.name = name;
            this.magicId = magicId;
            this.race = race;
        }

        public MagicConfig(String name, int magicId, String race, int repeatCount) {
            this(name, magicId, race);
            this.repeatCount = repeatCount;
        }

        public String getName() {
            return name;
        }

        public String getRace() {
            return race;
        }

        public int getMagicId() {
            return magicId;
        }

        public int getRepeatCount() {
            return repeatCount;
        }

        @Override
        public String toString() {
            return "MagicConfig [name=" + name + ", race=" + race + ", magicId=" + magicId + ", repeatCount="
                    + repeatCount + "]";
        }
    }
}
