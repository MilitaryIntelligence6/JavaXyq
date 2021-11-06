package com.mxxy.game.ui.battle;

import com.mxxy.game.resources.Constant;
import com.mxxy.game.ui.BattlePanel;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.Label;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 回合时间管理器
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class TimeManager {

    private ImageIcon wirtImage;

    private Label secLeft, secRight, wirtLabel;

    private Timer timer;

    private BattlePanel battlePanel;

    private int countDownTime = 8;
    private ImageIcon[] timerImge;

    public TimeManager(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
    }

    /**
     * 开始倒计时
     */
    public void countDown() {
        timer = new Timer();
        secLeft = new Label(null, timerImge[3], 0);
        secRight = new Label(null, timerImge[0], 0);
        wirtLabel = new Label(null, wirtImage, 0);
        secLeft.setBounds(Constant.WINDOW_WIDTH / 2 - timerImge[0].getImage().getWidth(null), 27,
                timerImge[0].getIconWidth(), timerImge[0].getIconHeight());
        secRight.setBounds(Constant.WINDOW_WIDTH / 2, 27, timerImge[0].getIconWidth(), timerImge[0].getIconHeight());
        wirtLabel.setBounds(Constant.WINDOW_WIDTH / 2 - wirtImage.getIconWidth() / 2, 30, wirtImage.getIconWidth(),
                wirtImage.getIconHeight());
        battlePanel.add(secLeft, 0);
        battlePanel.add(secRight, 0);
        startTimer();
    }

    /**
     * 开启定时任务
     */
    public void startTimer() {
        timer.schedule(new CountDown(), 0, 1000);
    }

    /**
     * 关闭定时任务
     */
    public void cleanTimer() {
        timer.cancel();
    }

    public void initTimes() {
        timerImge = new ImageIcon[10];
        for (int i = 0; i < timerImge.length; i++) {
            timerImge[i] = new ImageIcon(SpriteFactory.loadImage("res/componentsRes/timer/" + i + ".png"));
        }
        wirtImage = new ImageIcon(SpriteFactory.loadImage("res/componentsRes/timer/wait.png"));
    }

    /**
     * 倒计时
     *
     * @param number
     */
    private void showNumber(int number) {
        int temp = Math.abs(number);
        DecimalFormat df = new DecimalFormat("00");
        String sec = df.format(temp % 60);
        secLeft.setIcon(timerImge[sec.charAt(0) - 48]);
        secRight.setIcon(timerImge[sec.charAt(1) - 48]);
    }

    /**
     * 结束倒计时
     */
    public void cleanCountDown() {
        battlePanel.remove(secLeft);
        battlePanel.remove(secRight);
        battlePanel.add(wirtLabel, 0);
        battlePanel.remove(wirtLabel);
        cleanTimer();
    }

    public void setTime(int seconds) {
        if (seconds > 0) {
            showNumber(seconds);
        } else {
            battlePanel.attackCmd();
            cleanCountDown();
        }
    }

    /**
     * 倒计时线程
     *
     * @author dell
     */
    public class CountDown extends TimerTask {
        int time = countDownTime;

        @Override
        public void run() {
            setTime(time--);
        }
    }
}
