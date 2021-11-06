package com.mxxy.game.handler;

import com.mxxy.game.base.Application;
import com.mxxy.game.base.Panel;
import com.mxxy.game.config.Context;
import com.mxxy.game.config.DataStoreManager;
import com.mxxy.game.event.BaseEvent;
import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.listener.PanelEventListenerAdapter;
import com.mxxy.game.sprite.Players;
import com.mxxy.game.ui.IWindows;
import com.mxxy.game.utils.UIHelp;
import com.mxxy.net.IClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

abstract public class PanelHandler extends PanelEventListenerAdapter {

    protected Panel panel;

    protected Application application;

    protected UIHelp uihelp;

    protected Object[] object;
    /**
     * 数据更新频率
     */
    protected long period = 1000;
    protected IWindows iWindows;
    protected Players player;
    protected Context context;
    protected IClient client;
    protected DataStoreManager dataStoreManager;
    /**
     * 开启数据更新开关
     *
     * @param on
     */
    protected long lastTime;
    private Timer timer;
    private boolean autoUpdate;

    @Override
    public void init(PanelEvent evt) {
        application = Application.application;
        uihelp = application.getUIHelp();
        object = application.getObjects();
        iWindows = application.getiWindows();
        dataStoreManager = (DataStoreManager) object[0];
        context = (Context) object[2];
        client = context.getClient();
        panel = (Panel) evt.getSource();
        player = context.getPlayer();
        if (getContainersPanel() != null) {
            panel.add(getContainersPanel(), 0);
        }
        initModler();
        initView();
    }

    /**
     * 初始化View
     */
    abstract protected void initView();

    /**
     * 初始化Modler
     *
     * @return
     */
    protected <T extends Component> T findViewById(String id) {
        return panel.findViewById(id);
    }

    abstract public Object initModler();

    public JPanel getContainersPanel() {
        return null;
    }

    @Override
    public void dispose(PanelEvent evt) {
        setAutoUpdate(false);
        panel.close();
        uihelp.hidePanel(panel);
        if (getContainersPanel() != null) {
            panel.remove(getContainersPanel());
        }
    }

    @Override
    public void update(PanelEvent evt) {

    }

    @Override
    public void close(ActionEvent evt) {
        setAutoUpdate(false);
        panel.close();
        uihelp.hidePanel(panel);
        if (getContainersPanel() != null) {
            panel.remove(getContainersPanel());
        }
    }

    public void removeCompont() {
        // Component[] components = panel.getComponents();
        // for (Component component : components) {
        // if(component instanceof Label){
        // panel.remove(component);
        // }
        // }
    }

    @Override
    public void help(BaseEvent evt) {

    }

    protected void showOrHide(Panel panel) {
        uihelp.switchPanel(panel);
    }

    protected void showHide(Panel panels) {
        if (panel != null) {
            uihelp.hidePanel(panel);
            uihelp.showPanel(panels);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            String cmd = evt.getActionCommand();
            if (cmd.length() > 0) {
                this.invokeMethod(cmd, evt);
            }
        } catch (NoSuchMethodException e) {
            System.err
                    .println("\n[PanelHandler]该类找不到事件的处理方法：" + e.getMessage());
        } catch (Exception e) {
            System.err.println("\n[PanelHandler]执行事件时发生异常：" + evt);
            e.printStackTrace();
        }
    }

    protected Object invokeMethod(String mName, Object arg)
            throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException, SecurityException, NoSuchMethodException {
        Method m = this.getClass().getMethod(mName, arg.getClass());
        return m.invoke(this, arg);
    }

    public boolean isAutoUpdate() {
        return autoUpdate;
    }

    synchronized public void setAutoUpdate(boolean on) {
        if (on) {
            if (timer == null) {
                timer = new Timer("update-" + this.getClass().getName(), true);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        update(null);
                    }
                }, 0, period);
            }
        } else {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }
        this.autoUpdate = on;
    }

    public Panel getPanel() {
        return panel;
    }
}
