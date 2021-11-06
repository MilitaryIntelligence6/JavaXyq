package com.mxxy.game.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

public class GraphicsUtils {

	/**
	 * 清晰过滤开
	 */
	final static private RenderingHints VALUE_ANTIALIAS_ON = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);

	/**
	 * 清晰过滤关
	 */
	final static private RenderingHints VALUE_ANTIALIAS_OFF = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_OFF);

	/**
	 * 文字清晰过滤开
	 */
	final static private RenderingHints VALUE_TEXT_ANTIALIAS_ON = new RenderingHints(
			RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

	/**
	 * 文字清晰过滤关
	 */
	final static private RenderingHints VALUE_TEXT_ANTIALIAS_OFF = new RenderingHints(
			RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

	private static Toolkit toolkit = Toolkit.getDefaultToolkit();

	/**
	 * 设定图像解析度
	 * 
	 * @param g
	 * @param smooth
	 * @param antialiasing
	 */
	public static void setRenderingHints(Graphics g, boolean smooth, boolean antialiasing) {
		if (smooth) {
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		} else {
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		}
		if (antialiasing) {
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		} else {
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		}
	}

	/**
	 * 设定全局抗锯齿
	 * 
	 * @param g
	 * @param flag
	 */
	public static void setAntialiasAll(Graphics g, boolean flag) {
		if (flag) {
			((Graphics2D) g).setRenderingHints(VALUE_ANTIALIAS_ON);
		} else {
			((Graphics2D) g).setRenderingHints(VALUE_ANTIALIAS_OFF);
		}
	}

	/**
	 * 设置文字抗锯齿
	 * 
	 * @param g
	 * @param flag
	 */
	public static void setAntialias(Graphics g, boolean flag) {
		if (flag) {
			((Graphics2D) g).setRenderingHints(VALUE_TEXT_ANTIALIAS_ON);
		} else {
			((Graphics2D) g).setRenderingHints(VALUE_TEXT_ANTIALIAS_OFF);
		}
	}

	/**
	 * 是指透明度
	 * 
	 * @param g2d
	 * @param d
	 */
	final static public void setAlpha(Graphics2D g2d, float d) {
		AlphaComposite alphacomposite = AlphaComposite.getInstance(3, d);
		g2d.setComposite(alphacomposite);
	}

	/**
	 * 分解整图为图片数组
	 * 
	 * @param fileName
	 * @param row
	 * @param col
	 * @return
	 */
	public static Image[] getSplitImages(String fileName, int row, int col, boolean isFiltrate) {
		Image image = com.mxxy.game.was.Toolkit.createImageFromResource(fileName);
		return getSplitImages(image, row, col, isFiltrate);
	}

	/**
	 * 分割指定图像为image[]
	 * 
	 * @param image
	 * @param row
	 * @param col
	 * @return
	 */
	public static Image[] getSplitImages(Image image, int row, int col, boolean isFiltrate) {
		int index = 0;
		int wlength = image.getWidth(null) / row;
		int hlength = image.getHeight(null) / col;
		int l = wlength * hlength;
		Image[] abufferedimage = new Image[l];
		for (int y = 0; y < hlength; y++) {
			for (int x = 0; x < wlength; x++) {
				abufferedimage[index] = GraphicsUtils.createImage(row, col, true);
				Graphics g = abufferedimage[index].getGraphics();
				g.drawImage(image, 0, 0, row, col, (x * row), (y * col), row + (x * row), col + (y * col), null);
				g.dispose();
				g = null;
				PixelGrabber pgr = new PixelGrabber(abufferedimage[index], 0, 0, -1, -1, true);
				try {
					pgr.grabPixels();
				} catch (InterruptedException ex) {
				}
				int pixels[] = (int[]) pgr.getPixels();
				if (isFiltrate) {
					for (int i = 0; i < pixels.length; i++) {
						int[] rgbs = GameColor.getRGBs(pixels[i]);
						if ((rgbs[0] == 247 && rgbs[1] == 0 && rgbs[2] == 255)
								|| (rgbs[0] == 255 && rgbs[1] == 255 && rgbs[2] == 255)) {
							pixels[i] = 0;
						}
					}
				}
				ImageProducer ip = new MemoryImageSource(pgr.getWidth(), pgr.getHeight(), pixels, 0, pgr.getWidth());
				abufferedimage[index] = toolkit.createImage(ip);
				index++;
			}
		}
		return abufferedimage;
	}

	private static BufferedImage createImage(int w, int h, boolean flag) {
		if (flag) {
			return new BufferedImage(w, h, BufferedImage.TYPE_USHORT_565_RGB);
		}
		return null;
	}

}
