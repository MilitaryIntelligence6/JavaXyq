package com.mxxy.game.base;

import com.mxxy.game.config.*;
import com.mxxy.game.domain.PlayerVO;
import com.mxxy.game.ui.IWindows;
import com.mxxy.game.utils.FileUtils;
import com.mxxy.game.utils.FilterByJava;
import com.mxxy.game.utils.UIHelp;
import com.mxxy.game.xml.XMLoader;
import org.dom4j.DocumentException;

import javax.swing.*;
import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * @author javaman
 */
abstract public class Application {

    private XMLoader loader;

    protected IWindows iWindows;

    protected Context context;

    private IDataManager dataStore;

    private IPropertiseManager configmanager;

    private IProfileManager profileManager;

//	private ExtendScript extendScript;

    protected Object[] objects;

    public static Application application;

    public Application() {
        application = this;
    }

    public void startGame() {

        runLoadingWork();

        iWindows = createWindows();

        loadingpanel();

        loadLayoutResources();

        loadeResourceEnd();
    }

    /**
     * 加载游戏资源成功
     */
    protected void loadeResourceEnd() {

    }

    /**
     * 游戏加载
     */
    protected void loadingpanel() {

    }

    protected abstract IWindows createWindows();

    public void showHomePager() {
        try {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    iWindows.show();
                }
            });
        } catch (Exception e) {
        }
    }

    public void runLoadingWork() {
        LoadingsWork loadingWork = new LoadingsWork();
        loadingWork.execute();
        try {
            objects = loadingWork.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    protected Context createContext() {
        Context context = new Context();
        // IClient iClient=new ClientImp();
        // iClient.connect("127.0.0.1", 8888);
        // try {
        // iClient.initialize();
        // iClient.openReadThread();
        // } catch (InterruptedException | IOException e) {
        // e.printStackTrace();
        // }
        // context.setClient(iClient);
        return context;
    }

    public Context getContext() {
        return context;
    }

    /**
     * 配置数据线程
     *
     * @author dell
     */
    private class LoadingsWork extends SwingWorker<Object[], Void> {
        @Override
        protected Object[] doInBackground() throws Exception {
            FileUtils.deleteAll(new File("script/panel"));
            context = createContext();
            dataStore = new DataStoreManager(context);
            configmanager = new PropertiseConfigImpl();
            configmanager.setFilename("res/layout/ui.properties");
            configmanager.loadConfigs();
            profileManager = new ProfileImpl();
//			extendScript = new ExtendScript();
            return new Object[]{dataStore, configmanager, context, profileManager};
        }
    }

    /**
     * 加载UI 脚本资源
     */
    protected int i;

    public void loadLayoutResources() {
        loader = new XMLoader();
        File dir = new File("script");
        File[] files = dir.listFiles(new FilterByJava(".java"));
        for (int i = 0; i < configmanager.getPropertiseSize(); i++) {
            try {
                loader.loadUI(FileUtils.getPath("res/layout/" + configmanager.get(String.valueOf(i))));
//				extendScript.compile(files[i].getAbsolutePath());
                loadeResourceProgress(i);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 加载资源进度
     *
     * @param i
     */
    protected void loadeResourceProgress(int i) {

    }

    public UIHelp getUIHelp() {
        return iWindows.getUIHelp();
    }

    public IWindows getiWindows() {
        return iWindows;
    }

    public IPropertiseManager getConfigmanager() {
        return (IPropertiseManager) objects[1];
    }

    public Object[] getObjects() {
        return objects;
    }

    public void enterGame(PlayerVO data) {

    }

    public void enterTheWar(Object object[]) {

    }

    public void quitWar() {

    }

    /**
     * 退出游戏
     */
    public void exitGame() {
        System.exit(0);
    }
}
