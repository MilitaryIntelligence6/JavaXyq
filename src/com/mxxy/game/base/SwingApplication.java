package com.mxxy.game.base;

import com.mxxy.extendpackage.GamePager;
import com.mxxy.game.config.DataStoreManager;
import com.mxxy.game.config.PropertiseConfigImpl;
import com.mxxy.game.domain.PlayerVO;
import com.mxxy.game.handler.BattleRoundHandler;
import com.mxxy.game.listener.BattlePaneListener;
import com.mxxy.game.listener.GamePaneListener;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.ui.*;
import com.mxxy.game.utils.PanelManager;
import com.mxxy.game.widget.TileMap;

import java.awt.*;
import java.util.List;

public class SwingApplication extends Application {

    private GameFrame gameFrame;

    private LoadingPanel loadingPanel;

    private GamePanel gamePanel;

    @Override
    protected IWindows createWindows() {
        gameFrame = new GameFrame();
        gameFrame.initContent(context);
        return gameFrame;
    }

    private PropertiseConfigImpl config;

    @Override
    protected void loadeResourceProgress(int i) {
        loadingPanel.getjProgressBar().setMinimum(0);
        config = (PropertiseConfigImpl) objects[1];
        loadingPanel.getjProgressBar().setMaximum(config.getPropertiseSize());
        loadingPanel.getjProgressBar().setValue(i);
    }

    @Override
    protected void loadingpanel() {
        loadingPanel = new LoadingPanel(312, 104);
        gameFrame.showPanel(loadingPanel);
    }

    @Override
    protected void loadeResourceEnd() {
        showHomePager();
    }

    @Override
	public void showHomePager() {
        LoginPanel loginPanel = new LoginPanel();
        Panel panel = PanelManager.getPanel("HomePager");
        loginPanel.setUIhelp(getUIHelp());
        loginPanel.setConfigManager(config);
        loginPanel.playMusic();
        gameFrame.showPanel(loginPanel);
        getUIHelp().showPanel(panel);
    }

    /**
     * 进入游戏
     */
    @Override
    public void enterGame(PlayerVO data) {
        gameFrame.setIsfristApplication(false);
        GamePanel gamePanel = new GamePanel();
        gamePanel.setConfigManager(config);
        /** 由于是覆盖在上面的 所以需要将事件传递给父容器 */
        DataStoreManager dataStore = (DataStoreManager) objects[0];
        dataStore.initData(data);
        dataStore.loadSceneNpc();
        dataStore.loadSceneTeleporter();
        gamePanel.setContext(dataStore.getContext());
        gamePanel.setDataStore(dataStore);
        gamePanel.initGameDate();
        gamePanel.setUIhelp(getUIHelp());
        gamePanel.playMusic();
        gameFrame.showPanel(gamePanel);
        new GamePaneListener(gamePanel);
        getUIHelp().showPanel(GamePager.class.getSimpleName());
    }

    private BattleRoundHandler battleRoundHandler;  //  战争结果管理

    /**
     * 进入战争模块
     */
    @Override
    public void enterTheWar(Object[] args) {
        getUIHelp().hidePanel(GamePager.class.getSimpleName());
        battleRoundHandler = new BattleRoundHandler(getUIHelp());
        IWindows windows = context.getWindows();
        GamePanel gamePanel = (GamePanel) windows.getPanel();
        this.gamePanel = gamePanel;
        TileMap map = gamePanel.getMap(gamePanel.getContext().getScene()); // 获取当前
        Point viewPosition = gamePanel.getViewPosition();
        BattlePanel battlePanel = new BattlePanel(map, viewPosition, gamePanel);
        battlePanel.setUIhelp(getUIHelp());
        battlePanel.addBattleListener(battleRoundHandler);
        battlePanel.setConfigManager(config);
        battlePanel.setHostileTead((List) args[1]); // 设置敌方队伍
        battlePanel.setOwnsideTeam((List) args[0]); // 设置己方队伍
        battlePanel.playMusic();
        gameFrame.showPanel(battlePanel);
        new BattlePaneListener(battlePanel);
        getUIHelp().showPanel(GamePager.class.getSimpleName());
        battlePanel.initComponetn();
    }

    /**
     * 退出战争页面
     */
    @Override
    public void quitWar() {
        getUIHelp().hidePanel(GamePager.class.getSimpleName());
        GamePanel gamePanel = this.gamePanel;
        gamePanel.setPlayerLocation(context.getPlayer().getSceneLocation());
        gamePanel.initGameDate();
        ((BattlePanel) gameFrame.getPanel()).getTimerManager().cleanTimer();
        gameFrame.showPanel(gamePanel);
        getUIHelp().showPanel(GamePager.class.getSimpleName());
        gamePanel.playMusic();
        Constant.setProps(Constant.LAST_PATROL_TIME, System.currentTimeMillis());
    }

    /**
     * main 程序入口
     */
    public static void main(String[] args) {
        Application application = new SwingApplication();
        application.startGame();
    }
}
