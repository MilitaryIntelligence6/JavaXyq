package com.mxxy.game.handler;

import com.mxxy.game.config.IPropertiseManager;
import com.mxxy.game.config.PropertiseConfigImpl;
import com.mxxy.game.utils.InstanceUtil;

abstract public class AbstractPanelHandler<M> extends PanelHandler {

    protected M modler;

    protected IPropertiseManager propertiesConfigManager;

    @Override
    public M initModler() {
        modler = InstanceUtil.getInstance(getModlerClazz());
        exampleConfigManager();
        return modler;
    }

    @SuppressWarnings("unchecked")
    protected Class<?> getModlerClazz() {
        return (Class<M>) InstanceUtil.getModlerClazz(getClass(), 0);
    }

    /**
     * 实例化配置
     *
     * @return
     */
    public IPropertiseManager exampleConfigManager() {
        propertiesConfigManager = new PropertiseConfigImpl();
        propertiesConfigManager.setFilename(setConfigFileName());
        if (setConfigFileName() != null) {
            propertiesConfigManager.loadConfigs();
        }
        return propertiesConfigManager;
    }

    /**
     * 需要访问的文件
     *
     * @return filename
     */
    protected String setConfigFileName() {
        return null;
    }
}
