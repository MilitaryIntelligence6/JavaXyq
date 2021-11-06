package com.mxxy.game.utils;

import com.mxxy.game.was.Toolkit;
import javazoom.jl.player.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Mp3播放工具
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class MP3Player {

    public static Player loopplayer;
    public static String loopfile;
    public static boolean isPlayer;
    static AudioInputStream stream;
    private static Map<String, Player> playersMap = new HashMap<String, Player>();
    private static Thread loopThread;

    public static void stopAll() {
        Collection<Player> players = playersMap.values();
        for (Player player : players) {
            player.close();
        }
    }

    public static void stop(String filename) {
        Player player = playersMap.get(filename);
        if (player != null) {
            player.close();
        }
    }

    /**
     * 播放指定音乐
     *
     * @param filename
     */
    public static void play(String filename) {
        try {
            BufferedInputStream bis = new BufferedInputStream(Toolkit.getInputStream(filename));
            final Player player = new Player(bis);
            playersMap.put(filename, player);
            new Thread() {
                public void run() {
                    try {
                        player.play();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }.start();
        } catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

    }

    public static AudioInputStream loadSound(String filename) {
        URL ui = MP3Player.class.getResource(filename);
        try {
            stream = AudioSystem.getAudioInputStream(ui);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }

    /**
     * 循环播放音乐
     *
     * @param filename
     */
    public static void loop(String filename) {
        try {
            BufferedInputStream bis = new BufferedInputStream(Toolkit.getInputStream(filename));
            if (loopplayer != null) {
                loopplayer.close();
            }
            loopplayer = new Player(bis);
            loopfile = filename;
            if (loopThread == null) {
                loopThread = new Thread() {
                    public void run() {
                        try {
                            while (true) {
                                loopplayer.play();
                                if (loopplayer.isComplete()) {
                                    loopplayer = new Player(new BufferedInputStream(new FileInputStream(loopfile)));
                                }
                                Thread.sleep(500);
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                };
                isPlayer = true;
                loopThread.start();
            }
        } catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }
    }

    /**
     * 停止
     */
    public static void stopLoop() {
        if (loopplayer != null) {
            loopplayer.close();
        }
        isPlayer = false;
    }
}
