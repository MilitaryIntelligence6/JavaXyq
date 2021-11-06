package com.mxxy.game.resources;

import com.mxxy.game.config.MapConfigImpl;
import com.mxxy.game.sprite.Cursor;

import java.util.HashMap;
import java.util.Map;

/**
 * 资源提供
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class ResourceStores {

    private static ResourceStores stores;
    private Map<String, MapConfigImpl> configMap = new HashMap<String, MapConfigImpl>();
    private Map<String, Cursor> cursors = new HashMap<String, Cursor>();

    public static ResourceStores getInstance() {
        if (stores == null) {
            stores = new ResourceStores();
        }
        return stores;
    }

    /**
     * 实例Map配置
     *
     * @param id
     * @return
     */
    public MapConfigImpl getMapConfig(String id) {
        MapConfigImpl config = configMap.get(id);
        if (config == null) {
            String sceneName = null;
            switch (id) {
                case Constant.SCENE_ALG:
                    sceneName = "傲来国";
                    break;
                case Constant.SCENE_DHW:
                    sceneName = "东海湾";
                    break;
                case Constant.SCENE_ALG_PHARMACY:
                    sceneName = "药店";
                    break;
                case Constant.SCENE_WZG:
                    sceneName = "五庄观";
                    break;
                case Constant.SCENE_HSS:
                    sceneName = "化生寺";
                    break;
                case Constant.SCENE_CAC:
                    sceneName = "长安城";
                    break;
                case Constant.SCENE_JYC:
                    sceneName = "建邺城";
                    break;
                case Constant.SCENE_CSC:
                    sceneName = "长寿村";
                    break;
                case Constant.SCENE_ZZG:
                    sceneName = "朱紫国";
                    break;
                case Constant.SCENE_XL:
                    sceneName = "西凉女国";
                    break;
                case "1412":
                case "1415":
                    sceneName = "顶级豪宅";
                    break;
                case Constant.SCENE_BJ:
                    sceneName = "北俱芦洲";
                    break;
                default:
            }
            config = new MapConfigImpl(id, sceneName, "res/scene/" + id + ".map");
            configMap.put(id, config);
        }
        return config;
    }

    /**
     * 实例Cursor对象
     *
     * @param cursorId
     * @return
     */
    public Cursor getCursor(String cursorId) {
        Cursor cursor = cursors.get(cursorId);
        if (cursor == null) {
            boolean effect = Cursor.DEFAULT_CURSOR.equals(cursorId);
            cursor = new Cursor(cursorId, effect);
            cursors.put(cursorId, cursor);
        }
        return cursor;
    }
}
