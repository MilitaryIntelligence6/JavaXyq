package com.mxxy.game.base;

import com.mxxy.game.config.Context;
import com.mxxy.game.config.DataStoreManager;
import com.mxxy.game.config.IPropertiseManager;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.resources.ResourceStores;
import com.mxxy.game.sprite.Cursor;
import com.mxxy.game.sprite.Players;
import com.mxxy.game.utils.GraphicsUtils;
import com.mxxy.game.utils.MP3Player;
import com.mxxy.game.utils.UIHelp;
import com.mxxy.game.was.Toolkit;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZAB
 * 绘制面板基类
 * 2018年5月28日
 */
@SuppressWarnings("serial")
abstract public class DrawPaneImp extends JPanel implements IPanelDraw {

    public static final Object UPDATE_LOCK = new Object();
    public static final Object MOVEMENT_LOCK = new Object();
    public transient boolean isDraw = true;
    protected long lastTime, elapsedTime;
    protected Graphics2D graphics2d;
    protected List<Players> npcList = new ArrayList<Players>();
    protected Context context;
    protected DataStoreManager dataStore;
    protected UIHelp UIHelp;
    private int screenWidth;
    private int screenHeight;
    private BufferedImage bufferedImage;
    private Cursor gameCursor;
    private int mapWidth;
    private int mapHeight;
    private int viewportY; // 当前视图X
    private int viewportX; // 当前视图Y
    private Players player;
    private FPSController fpsListen;
    private DrawThread drawThread = new DrawThread();
    private IPropertiseManager configManager;

    public DrawPaneImp() {
        super(null);
        setScreenSize(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        setIgnoreRepaint(true);
        setFocusable(true);
        requestFocus(true);
        this.bufferedImage = new BufferedImage(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT,
                BufferedImage.TYPE_USHORT_565_RGB);
        graphics2d = (Graphics2D) bufferedImage.getGraphics();
        fpsListen = new FPSController();
        init();
        this.drawThread.start();
    }

    /**
     * 初始化配置
     */
    public abstract void init();

    private synchronized void drawGame() {// 将游戏内容绘制二次缓冲图片上
        Graphics g = getGraphics();
        long currTime = System.currentTimeMillis();// 程序现在时间
        if (lastTime == 0L) {
            lastTime = currTime;// 如果lastTime=0.则它等于程序第一次编译时间
        }
        this.elapsedTime = (currTime - this.lastTime);// elapsedTime前后两次绘制时间差
        this.lastTime = currTime;// 使用完毕后把现在时间等于持续时间，方便上一步计算前后两次绘制中间时间差
        if (g != null) {
            this.draw(graphics2d, elapsedTime);
            g.drawImage(bufferedImage, 0, 0, null);// 把缓冲图片绘制到屏幕上
            g.dispose();// 使用完Graphics后要清除绘制内容方便下一步的绘制
        }
    }

    ;

    private void draw(Graphics2D g, long elapsedTime2) {
        if (g == null) {
            return;
        }
        try {
            g.setColor(Color.BLACK);
            GraphicsUtils.setRenderingHints(g, true, true);
            drawBitmap(g, elapsedTime);
            drawComponent(g, elapsedTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘制
     *
     * @param g
     * @param elapsedTime
     */
    public void drawBitmap(Graphics2D g, long elapsedTime) {
    }

    /**
     * 绘制Component
     *
     * @param g
     * @param elapsedTime
     */
    protected void drawComponent(Graphics2D g, long elapsedTime) {
        Component[] comps = getComponents();
        for (int i = comps.length - 1; i >= 0; i--) {
            Component c = comps[i];
            Graphics g2 = g.create(c.getX(), c.getY(), c.getWidth(), c.getHeight());
            c.paint(g2);
            g2.dispose();
        }
        drawCursor(g, elapsedTime);
    }

    /**
     * drawCursor (绘制鼠标)
     *
     * @param g
     * @param elapsedTime
     */
    protected void drawCursor(Graphics2D g, long elapsedTime) {
        Point p = getPointCursor();
        if (gameCursor != null) {
            if (p != null) {
                gameCursor.setLocation(p.x, p.y);
            }
            gameCursor.update(elapsedTime);
            gameCursor.draw(g);
        }
    }

    /**
     * drawMemory (绘制内存)
     *
     * @param g
     */
    protected void drawMemory(Graphics2D g) {
        g.setFont(new Font("黑体", Font.PLAIN, 13));
        double mb = 1024 * 1024;
        double maxMem = Runtime.getRuntime().maxMemory() / mb;
        double freeMem = Runtime.getRuntime().freeMemory() / mb;
        int x = 130, y = 20;
        g.setColor(Color.decode("#00FFFF"));
        g.drawString(String.format("运行内存：%.2f/%.2f MB", freeMem, maxMem), x, y);
        g.drawString("FPS：" + fpsListen.getFPS(), x + 31, y + 19);
    }

    @Override
    public void setScreenSize(int width, int height) {
        this.screenHeight = height;
        this.screenWidth = width;
        setIgnoreRepaint(true);
        setFocusable(true);
        requestFocus(true);
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
    }

    public IPropertiseManager getConfigManager() {
        return configManager;
    }

    public void setConfigManager(IPropertiseManager configManager) {
        this.configManager = configManager;
    }

    public Point getPointCursor() {
        return super.getMousePosition();
    }

    @Override
    public Cursor getGameCursor() {
        return gameCursor;
    }

    @Override
    public void setGameCursor(String type) {
        Cursor cursor = ResourceStores.getInstance().getCursor(type);
        if (cursor != null) {
            this.gameCursor = cursor;
        }
    }

    @Override
    public void playMusic() {
        if (configManager != null) {
            configManager.setFilename("save/setting.properties");
            configManager.loadConfigs();
            if (Boolean.parseBoolean(configManager.get("music"))) {
                final String filename = getMusic();
                if (filename != null) {
                    Thread mp3play = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            MP3Player.loop(filename);
                        }
                    });
                    mp3play.start();
                }
            }
        }
    }

    protected String getMusic() {
        return null;
    }

    @Override
    public void stopMusic() {
        MP3Player.stopLoop();
    }

    @Override
    public int getScernHeight() {
        return screenHeight;
    }

    @Override
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * deleteAllComponent(清空面板所有组件)
     */
    protected void removeComponent() {
        this.removeAll();
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    /***
     * addComponent(添加组件)
     *
     * @param comp
     */
    protected void addComponent(JComponent comp) {
        this.add(comp);
    }

    /**
     * addComponent to up(添加组件到最上层)
     *
     * @param comp
     */
    protected void addComponents(JComponent comp) {
        this.add(comp, 0);
    }

    /**
     * deleteComponent(删除组件)
     *
     * @param comp
     */
    protected void romveComponent(JComponent comp) {
        this.remove(comp);
    }

    public int getMaxHeight() {
        return mapHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.mapHeight = maxHeight;
    }

    public int getMaxWidth() {
        return mapWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.mapWidth = maxWidth;
    }

    protected int getViewportY() {
        return viewportY;
    }

    protected int getViewportX() {
        return viewportX;
    }

    /**
     * 获取到当前视图的XY
     *
     * @return
     */
    public Point getViewPosition() {
        return new Point(viewportX, viewportY);
    }

    /**
     * 设置地图的坐标 (移动地图)
     *
     * @param x
     * @param y
     */
    public void setViewPosition(int x, int y) {
        this.viewportX = x;
        this.viewportY = y;
        judgeViewport();
    }

    /**
     * 判断是否超越屏幕宽高
     */
    private void judgeViewport() {
        int canvasWidth = getWidth();
        int canvasHeight = getHeight();
        if (viewportX + canvasWidth > mapWidth) {
            viewportX = mapWidth - canvasWidth;
        } else if (viewportX < 0) {
            viewportX = 0;
        }
        if (viewportY + canvasHeight > mapHeight) {
            viewportY = mapHeight - canvasHeight;
        } else if (viewportY < 0) {
            viewportY = 0;
        }
    }

    public void stopDraw() {
        this.isDraw = false;
    }

    public void startDraw() {
        this.isDraw = true;
    }

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }

    public Point localToView(Point p) {
        return new Point(p.x - this.viewportX, p.y - this.viewportY);
    }

    public Point viewToLocal(Point p) {
        return new Point(p.x + getViewportX(), p.y + getViewportY());
    }

    @Override
    public void setUIhelp(UIHelp UIHelp) {
        this.UIHelp = UIHelp;
    }

    public UIHelp getUIHelp() {
        return UIHelp;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    public DataStoreManager getDataStore() {
        return dataStore;
    }

    public void setDataStore(DataStoreManager dataStore) {
        this.dataStore = dataStore;
    }

    // protected IWindows windows;
    // @Override
    // public void setWindows(IWindows windows) {
    // this.windows=windows;
    // }

    @Override
    public void paintImmediately(int x, int y, int w, int h) {
    }

    /**
     * 绘制线程
     */
    private final class DrawThread extends Thread {

        public DrawThread() {
            this.setName("DrawThread");
            this.setDaemon(true);
        }

        @Override
        public void run() {
            long beforeTime, timeNow, timeDiff, sleepTime;
            long overSleepTime = 0L;
            int noDelays = 0;
            beforeTime = System.nanoTime();
            fpsListen.setTime(beforeTime);
            while (isDraw) {
                synchronized (UPDATE_LOCK) {
                    if (isShowing() && isVisible()) {
                        drawGame();
                    }
                }
                timeNow = System.nanoTime();
                timeDiff = timeNow - beforeTime;
                sleepTime = (FPSController.PERIOD - timeDiff) - overSleepTime;
                if (sleepTime > 0) {
                    Toolkit.sleep(sleepTime / 1000000L);
                    overSleepTime = (System.nanoTime() - timeNow) - sleepTime;
                } else {
                    overSleepTime = 0L;
                    if (++noDelays >= 16) {
                        Thread.yield();
                        noDelays = 0;
                    }
                }
                beforeTime = System.nanoTime();
                fpsListen.makeFPS();
            }
        }
    }
}
