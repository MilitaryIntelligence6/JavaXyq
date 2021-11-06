package com.mxxy.game.utils;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * 游戏取色器
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class GameColor extends Color {
	
	private static Map<String, Color> colors = new HashMap<String, Color>();
	
	{
		colors.put("black", GameColor.black);
		colors.put("blue", GameColor.blue);
		colors.put("cyan", GameColor.cyan);
		colors.put("darkGray", GameColor.darkGray);
		colors.put("gray", GameColor.gray);
		colors.put("green",GameColor.green);
		colors.put("lightGray", GameColor.lightGray);
		colors.put("magenta", GameColor.magenta);
		colors.put("orange", GameColor.orange);
		colors.put("pink", GameColor.pink);
		colors.put("red", GameColor.red);
		colors.put("white", GameColor.white);
		colors.put("yellow", GameColor.yellow);
	}

	private static final long serialVersionUID = 1L;

	public final static GameColor white = new GameColor(255, 255, 255);

	public final static GameColor lightGray = new GameColor(192, 192, 192);

	public final static GameColor gray = new GameColor(128, 128, 128);

	public final static GameColor darkGray = new GameColor(64, 64, 64);

	public final static GameColor black = new GameColor(0, 0, 0);

	public final static GameColor red = new GameColor(255, 0, 0);

	public final static GameColor pink = new GameColor(255, 175, 175);

	public final static GameColor orange = new GameColor(255, 200, 0);

	public final static GameColor yellow = new GameColor(255, 255, 0);

	public final static GameColor green = new GameColor(0, 255, 0);

	public final static GameColor magenta = new GameColor(255, 0, 255);

	public final static GameColor cyan = new GameColor(0, 255, 255);

	public final static GameColor blue = new GameColor(0, 0, 255);

	public final static int transparent = 0xff000000;
	
	

	public GameColor(Color c) {
		super(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
	}

	public GameColor(int r, int g, int b) {
		super(r, g, b, 255);
	}

	public GameColor(int r, int g, int b, int a) {
		super(r, g, b, a);
	}

	public GameColor(int rgb) {
		super(rgb);
	}

	public GameColor(int rgba, boolean alpha) {
		super(rgba, alpha);
	}

	public GameColor(float r, float g, float b) {
		super(r, g, b);
	}

	public GameColor(float r, float g, float b, float a) {
		super(r, g, b, a);
	}

	/**
	 * 返回ARGB
	 * 
	 * @return
	 */
	public int getARGB() {
		return getARGB(getRed(), getGreen(), getBlue(), getAlpha());
	}

	/**
	 * 获得24位色
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @return
	 */
	public static int getRGB(int r, int g, int b) {
		return getARGB(r, g, b, 0xff);
	}

	/**
	 * 获得RGB颜色
	 * 
	 * @param pixels
	 * @return
	 */
	public static int getRGB(int pixels) {
		int r = (pixels >> 16) & 0xff;
		int g = (pixels >> 8) & 0xff;
		int b = pixels & 0xff;
		return getRGB(r, g, b);
	}

	/**
	 * 获得32位色
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @param alpha
	 * @return
	 */
	public static int getARGB(int r, int g, int b, int alpha) {
		return (alpha << 24) | (r << 16) | (g << 8) | b;
	}

	/**
	 * 获得Aplha
	 * 
	 * @param color
	 * @return
	 */
	public static int getAlpha(int color) {
		return color >>> 24;
	}

	/**
	 * 获得Red
	 * 
	 * @param color
	 * @return
	 */
	public static int getRed(int color) {
		return (color >> 16) & 0xff;
	}

	/**
	 * 获得Green
	 * 
	 * @param color
	 * @return
	 */
	public static int getGreen(int color) {
		return (color >> 8) & 0xff;
	}

	/**
	 * 获得Blud
	 * 
	 * @param color
	 * @return
	 */
	public static int getBlue(int color) {
		return color & 0xff;
	}

	/**
	 * 像素前乘
	 * 
	 * @param argbColor
	 * @return
	 */
	public static int premultiply(int argbColor) {
		int a = argbColor >>> 24;
		if (a == 0) {
			return 0;
		} else if (a == 255) {
			return argbColor;
		} else {
			int r = (argbColor >> 16) & 0xff;
			int g = (argbColor >> 8) & 0xff;
			int b = argbColor & 0xff;
			r = (a * r + 127) / 255;
			g = (a * g + 127) / 255;
			b = (a * b + 127) / 255;
			return (a << 24) | (r << 16) | (g << 8) | b;
		}
	}

	/**
	 * 像素前乘
	 * 
	 * @param rgbColor
	 * @param alpha
	 * @return
	 */
	public static int premultiply(int rgbColor, int alpha) {
		if (alpha <= 0) {
			return 0;
		} else if (alpha >= 255) {
			return 0xff000000 | rgbColor;
		} else {
			int r = (rgbColor >> 16) & 0xff;
			int g = (rgbColor >> 8) & 0xff;
			int b = rgbColor & 0xff;

			r = (alpha * r + 127) / 255;
			g = (alpha * g + 127) / 255;
			b = (alpha * b + 127) / 255;
			return (alpha << 24) | (r << 16) | (g << 8) | b;
		}
	}

	/**
	 * 消除前乘像素
	 * 
	 * @param preARGBColor
	 * @return
	 */
	public static int unpremultiply(int preARGBColor) {
		int a = preARGBColor >>> 24;
		if (a == 0) {
			return 0;
		} else if (a == 255) {
			return preARGBColor;
		} else {
			int r = (preARGBColor >> 16) & 0xff;
			int g = (preARGBColor >> 8) & 0xff;
			int b = preARGBColor & 0xff;

			r = 255 * r / a;
			g = 255 * g / a;
			b = 255 * b / a;
			return (a << 24) | (r << 16) | (g << 8) | b;
		}
	}

	/**
	 * 获得r,g,b
	 * 
	 * @param pixel
	 * @return
	 */
	public static int[] getRGBs(final int pixel) {
		int[] rgbs = new int[3];
		rgbs[0] = (pixel >> 16) & 0xff;
		rgbs[1] = (pixel >> 8) & 0xff;
		rgbs[2] = (pixel) & 0xff;
		return rgbs;
	}

	public Color getAWTColor() {
		return new Color(getRed(), getGreen(), getBlue(), getAlpha());
	}
	
	public static Color getColor(String color) {
		Color c = colors.get(color);
		if(c == null) {
			c = Color.getColor(color, Color.white);
		}
		return c;
	}
}
