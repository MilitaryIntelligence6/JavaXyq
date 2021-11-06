package com.mxxy.game.config;

public interface IConfigManager extends IConfig {

    /**
     * 获取文件名
     *
     * @return
     */
    String getFilename();

    /**
     * 设置Properties文件名
     *
     * @param filename
     */
    void setFilename(String filename);

    void loadConfigs();

    /**
     * 获取集合个数
     *
     * @return
     */
    int getPropertiseSize();

    /**
     * 获取指定键
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 添加
     *
     * @param key
     * @param object
     */
    void put(String key, Object object);

    /**
     * 保存
     */
    void saveConfig();

    /**
     * 判断集合种是否有元素
     *
     * @param key
     * @return
     */
    boolean contains(String key);

    /**
     * 验证用户信息
     *
     * @param user
     * @param cipher
     * @return
     */
    boolean loadCheckUser(String user, String cipher);
}
