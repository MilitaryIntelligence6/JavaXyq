package com.mxxy.game.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * .Wav播放工具
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class MusicUitls {
	private static MusicUitls mMusicUitls;

	public static MusicUitls getMusicUitls() {
		if (mMusicUitls == null) {
			mMusicUitls = new MusicUitls();
		}
		return mMusicUitls;
	}

	static File file;
	static AudioInputStream stream;
	static AudioFormat format;
	static DataLine.Info info;
	static Clip clip;

	public static void loadSound(String filename) {
		URL url = MusicUitls.class.getClassLoader().getResource(filename);
		if (url != null) {
			try {
				stream = AudioSystem.getAudioInputStream(url);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			format = stream.getFormat();
		}
	}

	public static void playSound() {
		info = new DataLine.Info(Clip.class, format);
		try {
			clip = (Clip) AudioSystem.getLine(info);
		} catch (Exception e) {
		}
		try {
			clip.open(stream);
		} catch (Exception e) {
		}
		clip.start();
	}

	/**
	 * 获取音频控制器
	 * 
	 * @return
	 */
	public FloatControl getClipFloatControl() {
		return (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	}

	public void colse() {
		clip.stop();
	}
}
