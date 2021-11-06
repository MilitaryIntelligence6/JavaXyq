package com.mxxy.game.ui;

import com.mxxy.game.base.AbstactPanel;
import com.mxxy.game.base.Application;
import com.mxxy.game.base.Panel;
import com.mxxy.game.event.BattleEvent;
import com.mxxy.game.listener.BattlePaneListener;
import com.mxxy.game.listener.IBattleListener;
import com.mxxy.game.listener.ISetOnListener;
import com.mxxy.game.modler.MagicModle.MagicConfig;
import com.mxxy.game.resources.Constant;
import com.mxxy.game.sprite.Cursor;
import com.mxxy.game.sprite.Magic;
import com.mxxy.game.sprite.Players;
import com.mxxy.game.sprite.Sprite;
import com.mxxy.game.ui.battle.Command;
import com.mxxy.game.ui.battle.CommandManager;
import com.mxxy.game.ui.battle.TimeManager;
import com.mxxy.game.utils.MP3Player;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.Animation;
import com.mxxy.game.widget.RichLabel;
import com.mxxy.game.widget.TileMap;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

/***
 * 战争面板
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class BattlePanel extends AbstactPanel implements ISetOnListener<BattlePaneListener> {

    private static final String BATTLE_ROLE_CMD = "BattlePanelCmd";

    private static final String BATTLE_ROLE_WARMAGIC = "BattleWarmagic";

    private static final String BATTLE_ROLE_PROP = "BattleUserProp";
    public boolean flage;
    private EventListenerList eventListener = new EventListenerList();
    private CommandManager commandManager;
    private TimeManager timerManager;
    private Command lastCmd;
    /**
     * 已方
     */
    private List<Players> ownsideTeam;
    /**
     * 敌方
     */
    private List<Players> hostileTeam;
    private TileMap tileMap;
    private Point point;
    private Image backgroundImage;
    private Random random = new Random();
    private Players target;
    private Magic mMagic;
    private RichLabel battleMessage;
    /**
     * 是否选择的法术
     */
    private boolean isSelectMagic;
    /**
     * 控制命令流程 true 战斗未开始，false战斗已开始 避免重复指令操作
     */
    private boolean waitingCmd;
    /**
     * 当前指定战斗指令的人物序号
     */
    private int cmdIndex;
    private MagicConfig magicConfig;
    private int targetX, targetY;
    private int originX, originY;
    private Players movingPlayer;
    private Map<Players, Integer> points = new HashMap<Players, Integer>();
    private int dx = 60, dy = 40;
    // int x0 = 340, y0 = 400;
    private int x1 = 300, y1 = 150;

    /**
     * @param tileMap      地图
     * @param viewPosition 人物坐标
     * @param gamePanel
     */
    public BattlePanel(TileMap tileMap, Point viewPosition, GamePanel gamePanel) {
        this.tileMap = tileMap;
        this.point = viewPosition;
        commandManager = new CommandManager(this);
    }

    @Override
    public void init() {
        setGameCursor(Cursor.DEFAULT_CURSOR);
        backgroundImage = SpriteFactory.loadImage("res/scene/battlebg.png");
        waitingCmd = true;
        timerManager = new TimeManager(this);
        timerManager.initTimes();
        timerManager.countDown();
    }

    public void initComponetn() {
        Panel dialog = getUIHelp().getPanel(BATTLE_ROLE_CMD);
        getUIHelp().showPanel(dialog);
        battleMessage = new RichLabel();
        battleMessage.setBounds(Constant.WINDOW_WIDTH / 2 - 100, 500, 300, 50);
        add(battleMessage, 0);
    }

    public void fireBattleEvent(BattleEvent evt) {
        IBattleListener[] listeners = listenerList.getListeners(IBattleListener.class);
        for (int i = 0; i < listeners.length; i++) {
            switch (evt.getId()) {
                case BattleEvent.BATTLE_WIN:
                    listeners[i].battleWin(evt);
                    break;
                case BattleEvent.BATTLE_DEFEATED:
                    listeners[i].battleDefeated(evt);
                    break;
                case BattleEvent.BATTLE_TIMEOUT:
                    listeners[i].battleTimeout(evt);
                    break;
                case BattleEvent.BATTLE_BREAK:
                    listeners[i].battleBreak(evt);
                    break;
            }
        }
    }

    public void addCmd(Command cmd) {
        commandManager.addCmd(cmd);
        lastCmd = cmd;
        if (cmdIndex >= ownsideTeam.size() - 1) {
            turnBattle();
        } else {
            getUIHelp().showPanel(BATTLE_ROLE_CMD);
            cmdIndex++;
            this.setPlayer(ownsideTeam.get(cmdIndex));
            waitingCmd = true;
        }
    }

    /**
     * 进入回合战斗
     */
    private void turnBattle() {
        waitingCmd = false;
        getUIHelp().hidePanel(BATTLE_ROLE_CMD);
        new Thread("BattleThread") {
            public void run() {
                commandManager.turnBattle();
            }

            ;
        }.start();
    }

    /**
     * 新的回合开始
     */
    public void roundStartNew() {
        Players cmdPlayer = ownsideTeam.get(cmdIndex);
        waitingCmd = true;
        getUIHelp().showPanel(BATTLE_ROLE_CMD);
        cmdIndex = 0;
        this.setPlayer(ownsideTeam.get(cmdIndex));

    }

    /**
     * 发送攻击命令
     */
    public void attackCmd() {
        if (target == null) {
            target = randomEnemy();
        }
        Players cmdPlayer = ownsideTeam.get(cmdIndex);
        Command cmd = new Command(Players.STATE_ATTACK, cmdPlayer, target);
        addCmd(cmd);
    }

    /**
     * 施法指令
     */
    public void magicCmd() {
        if (target == null) {
            target = randomEnemy();
        }
        Players cmdPlayer = ownsideTeam.get(cmdIndex);
        Command cmd = new Command(Players.STATE_MAGIC, cmdPlayer, target);
        cmd.add(MagicConfig.class.getSimpleName(), magicConfig);
        addCmd(cmd);
        playOnceMagic();
    }

    /**
     * @param player
     * @param x
     * @param y
     * @param state  Players.STATE_RUSHB||Players.STATE_RUSHA
     */
    public void rush(Players player, int x, int y, String state) {
        this.targetX = x;
        this.targetY = y;
        this.originX = player.getX();
        this.originY = player.getY();
        this.movingPlayer = player;
        player.setState(state);
        long lastTime = System.currentTimeMillis();
        while (!isReach()) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
            }
            long nowTime = System.currentTimeMillis();
            updateMovement(nowTime - lastTime);
            lastTime = nowTime;
        }
    }

    private void updateMovement(long elapsedTime) {
        int dx = 0, dy = 0;
        // 计算起点与目标点的弧度角
        double radian = Math.atan(1.0 * (targetY - movingPlayer.getY()) / (targetX - movingPlayer.getX()));
        // 计算移动量
        int distance = (int) (Constant.BATTLE_PLAYER_RUNAWAY * 6 * elapsedTime);
        dx = (int) (distance * Math.cos(radian));
        dy = (int) (distance * Math.sin(radian));
        // 修正移动方向
        if (targetX > originX) {
            dx = Math.abs(dx);
            dx = Math.min(dx, targetX - movingPlayer.getX());
        } else {
            dx = -Math.abs(dx);
            dx = Math.max(dx, targetX - movingPlayer.getX());
        }
        if (targetY > originY) {
            dy = Math.abs(dy);
            dy = Math.min(dy, targetY - movingPlayer.getY());
        } else {
            dy = -Math.abs(dy);
            dy = Math.max(dy, targetY - movingPlayer.getY());
        }
        movingPlayer.moveBy(dx, dy);
    }

    /**
     * 当前单位是否到达目标点
     *
     * @return
     */
    private boolean isReach() {
        return Math.abs(targetX - movingPlayer.getX()) <= 2 && Math.abs(targetY - movingPlayer.getY()) <= 2;
    }

    /**
     * 随机挑选敌人
     *
     * @return
     */
    private Players randomEnemy() {
        Players target = hostileTeam.get(random.nextInt(hostileTeam.size()));
        // do {
        // target = hostileTeam.get(random.nextInt(hostileTeam.size()));
        // } while (target.getData().hp == 0);
        return target;
    }

    @Override
    public void drawBitmap(Graphics2D g, long elapsedTime) {
        g.setColor(Constant.PLAYER_NAME_COLOR);
        g.clearRect(0, 0, getWidth(), getHeight());
        this.tileMap.drawBitmap(g, point.x, point.y, getWidth(), getHeight());
        if (this.backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, null);
        }
        drawNpc(g, elapsedTime);
        drawComponent(g, elapsedTime);
        if (mMagic != null) {
            drawMagic(g, elapsedTime);
        }
        drawPoints(g);
        drawMemory(g);
    }

    /**
     * 绘制法术
     *
     * @param elapsedTime
     * @param g
     */
    public void drawMagic(Graphics2D g, long elapsedTime) {
        if (mMagic != null) {
            switch (magicConfig.getMagicId()) {
                case MagicConfig.BIGMAGIC:
                    if (mMagic.getSprite() != null) {
                        // g.drawRect(mMagic.getSprite().getCenterX(), mMagic.getSprite().getCenterY(),
                        // mMagic.getSprite().getWidth()
                        // , mMagic.getSprite().getHeight());
                        if (magicConfig.getName().equals("龙卷雨击")) {
                            mMagic.draw(g, mMagic.getSprite().getCenterX() - 230,
                                    mMagic.getSprite().getCenterY() - 180);
                        } else {
                            mMagic.draw(g, mMagic.getSprite().getCenterX(), mMagic.getSprite().getCenterY());
                        }
                    }
                    break;
            }
            mMagic.update(elapsedTime);
        }
    }

    /**
     * 播放一次法术动画
     *
     * @param true 为播放音乐
     */
    public void playOnceMagic() {
        getUIHelp().hidePanel(BATTLE_ROLE_WARMAGIC);
        setGameCursor(Cursor.DEFAULT_CURSOR);
        mMagic = new Magic();
        mMagic.setMagincId(magicConfig.getName());
        mMagic.createSprite(this.ownsideTeam.get(0));
        if (mMagic != null) {
            mMagic.getSprite().setRepeat(1);
            mMagic.getSprite().getCurrAnimation().waitFor();
            mMagic = null;
        }
    }

    public void playOnceMusic(boolean sound) {
        if (sound) {
            try {
                MP3Player.play("res/music/" + magicConfig.getName() + ".mp3");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void drawPoints(Graphics2D g) {
        Set<Entry<Players, Integer>> entrys = points.entrySet();
        for (Entry<Players, Integer> en : entrys) {
            Players player = en.getKey();
            int value = en.getValue();
            int x = player.getX() - player.getPerson().getCenterX() / 2;
            int y = player.getY() - player.getPerson().getCenterY() / 2;
            int dx = 0;
            Animation numAnim = SpriteFactory.loadAnimation(value > 0 ? "res/misc/3cf8f9fe" : "res/misc/30f737d8");
            String strValue = Integer.toString(Math.abs(value));
            for (int i = 0; i < strValue.length(); i++) {
                int index = strValue.charAt(i) - '0';
                numAnim.setIndex(index);
                numAnim.drawBitmap(g, x + dx, y);
                dx += numAnim.getWidth();
            }
        }
    }

    /**
     * 设置显示的增加、消耗点数
     *
     * @param player
     * @param value
     */
    public void showPoints(Players player, int value) {
        points.put(player, value);
    }

    /**
     * 隐藏点数
     *
     * @param player
     */
    public void hidePoints(Players player) {
        points.remove(player);
    }

    public void initPlayer() {
        for (int i = 0; i < ownsideTeam.size(); i++) {
            Players player = ownsideTeam.get(i);
            player.setLocation(530 - 55 * i, 400 - 45 * i);
            player.setState(Players.STATE_WRITBUTTLE);
            player.setDirection(Sprite.DIRECTION_TOP_LEFT);
            super.addNpc(player);
        }
    }

    /**
     * initHostileTeam(初始化敌方团队)
     */
    public void initHostileTeam() {
        switch (hostileTeam.size()) {
            case 1:
                x1 -= 2 * dx;
                y1 += 2 * dy;
                break;
            case 2:
                x1 -= 1.5 * dx;
                y1 += 1.5 * dy;
                break;
            case 3:
                x1 -= 1 * dx;
                y1 += 1 * dy;
                break;
            case 4:
                break;
            default:
                break;
        }
        for (int i = 0; i < hostileTeam.size(); i++) {
            Players player = hostileTeam.get(i);
            player.setLocation(x1 - dx * i, y1 + dy * i);
            player.setState(Players.STATE_STAND);
            player.setDirection(Sprite.DIRECTION_BOTTOM_RIGHT);
            super.addNpc(player);
        }
    }

    /**
     * 道具选择面板
     */
    public void selectProp() {
        Panel dlg = getUIHelp().getPanel(BATTLE_ROLE_PROP);
        getUIHelp().switchPanel(dlg);
    }

    /**
     * 法术选择面板
     */
    public void selectWarmagic() {
        Panel dlg = getUIHelp().getPanel(BATTLE_ROLE_WARMAGIC);
        getUIHelp().switchPanel(dlg);
    }

    @Override
    public String getMusic() {
        return ("res/music/2003.mp3");
    }

    /**
     * 移除指定
     *
     * @param players
     */
    public void reomovPlayer(Players players) {
        this.ownsideTeam.remove(players);
        this.hostileTeam.remove(players);
        super.romveNpc(players);
    }

    /**
     * 敌方团队
     *
     * @param team
     */
    public void setHostileTead(List<Players> team) {
        this.hostileTeam = team;
        initHostileTeam();
    }

    public void runaway(Players player, boolean success) {
        try {
            RunawayWorker worker = new RunawayWorker(player, success, 2000);
            worker.execute();
            worker.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setListener(BattlePaneListener event) {
        this.addMouseListener(event);
        this.addMouseMotionListener(event);
        this.addKeyListener(event);
    }

    @Override
    public void removeListener(BattlePaneListener event) {
        this.removeListener(event);
    }

    /**
     * 根据速度排序
     *
     * @param target
     * @param hostileTeam
     * @return
     */
    public List<Players> getMagicHostileTeam(Players target, List<Players> hostileTeam) {
        List<Players> list = new ArrayList<Players>(hostileTeam);
        if (list.size() >= 3) {
            Collections.sort(list, new Comparator<Players>() {
                @Override
                public int compare(Players o1, Players o2) {
                    if (o1.getPalyVo().getSpeed() < o2.getPalyVo().getSpeed()) {
                        return 1;
                    }
                    if (o1.getPalyVo().getSpeed() == o2.getPalyVo().getSpeed()) {
                        return 0;
                    }
                    return -1;
                }
            });
            ArrayList<Players> players = new ArrayList<Players>();
            players.add(list.get(0));
            players.add(list.get(1));
            players.add(target);
        }
        return list;
    }

    /**
     * 管理Battle 状态
     *
     * @param listener
     */
    public void addBattleListener(IBattleListener listener) {
        listenerList.add(IBattleListener.class, listener);
    }

    public void removeBattleListener(IBattleListener listener) {
        listenerList.remove(IBattleListener.class, listener);
    }

    /**
     * 指令介绍
     *
     * @param text 文本
     */
    public void setBattleMessage(String text) {
        battleMessage.setText(text);
    }

    public List<Players> getHostileTeam() {
        return hostileTeam;
    }

    public List<Players> getOwnsideTeam() {
        return ownsideTeam;
    }

    /**
     * 已方团队
     *
     * @param team
     */
    public void setOwnsideTeam(List<Players> team) {
        this.ownsideTeam = team;
        initPlayer();
    }

    public void setTarget(Players target) {
        this.target = target;
    }

    public Magic getmMagic() {
        return mMagic;
    }

    public void setmMagic(Magic mMagic) {
        this.mMagic = mMagic;
    }

    public boolean isWaitingCmd() {
        return waitingCmd;
    }

    public boolean isSelectMagic() {
        return isSelectMagic;
    }

    /**
     * 设置对应法术
     *
     * @param mBean
     */
    public void setSelectMagic(MagicConfig mBean) {
        this.magicConfig = mBean;
        isSelectMagic = true;
    }

    public void setSelectMagic(boolean isSelectMagic) {
        this.isSelectMagic = isSelectMagic;
    }

    public TimeManager getTimerManager() {
        return timerManager;
    }

    public EventListenerList getEventListener() {
        return eventListener;
    }

    public void hidePanel() {
        getUIHelp().hidePanel(BATTLE_ROLE_WARMAGIC);
        getUIHelp().hidePanel(BATTLE_ROLE_PROP);
        getUIHelp().hidePanel(BATTLE_ROLE_CMD);
    }

    /**
     * Runaway(逃跑)
     */
    private class RunawayWorker extends SwingWorker<Object, Object> {
        private Players player;
        private long duration;
        private boolean success;

        public RunawayWorker(Players player, boolean success, long duration) {
            super();
            this.player = player;
            this.duration = duration;
            this.success = success;
        }

        @Override
        protected Object doInBackground() throws Exception {
            int dir = player.getDirection();
            player.setDirection(dir - 2);
            player.setState(Players.STATE_WALK);
            Thread.sleep(1000);
            if (this.success) {
                MP3Player.play("res/music/escape_ok.mp3");
                long interval = 50;
                long t = 0;
                while (t < duration) {
                    Thread.sleep(interval);
                    // 计算移动量
                    long elapsedTime = interval;
                    int distance = (int) (2 * Constant.PLAYER_RUNAWAY * elapsedTime);
                    int dx = distance; // 向右下逃跑
                    int dy = distance;
                    if (player.getDirection() == Sprite.DIRECTION_TOP_LEFT) {// 向左上逃跑
                        dx = -dx;
                        dy = -dy;
                    }
                    player.moveBy(dx, dy);
                    super.publish(new Point(dx, dy));
                    t += interval;
                    if (playerGoBeyone(player)) {
                        Application.application.quitWar();
                        break;
                    }
                }
            } else {
                getUIHelp().prompt(getComponent(), "运气不济,逃跑失败 #83", 2000);
            }
            player.setState(Players.STATE_STAND);
            player.setDirection(dir);
            return null;
        }

        /**
         * judge playerLacotion GoBeyone Screen(判断人物是否超出屏幕)
         *
         * @param players
         * @return
         */
        private boolean playerGoBeyone(Players players) {
            return player.getX() < 0 || player.getY() < 0 || player.getX() > BattlePanel.this.getWidth() + 28
                    || player.getY() > BattlePanel.this.getHeight() + 28;
        }
    }
}
