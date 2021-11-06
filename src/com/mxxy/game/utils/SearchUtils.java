/*
 * JavaXYQ Source Code
 * by kylixs
 * at 2009-11-25
 * please visit http://javaxyq.googlecode.com
 * or mail to kylixs@qq.com
 */
package com.mxxy.game.utils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class SearchUtils {

	/**
	 * 计算两点间的直线路径
	 * @param x1 目前人物所在的x
	 * @param y1 目前人物所在的y
	 * @param x2 点击的x
	 * @param y2 点击的y
	 * @return
	 */
	public static List<Point> getLinePath(int x1, int y1, int x2, int y2) {
		List<Point> path = new ArrayList<Point>();
		int x, y;
		int dx, dy;
		int incx, incy;
		int balance;
		if (x2 >= x1) {
			dx = x2 - x1;
			incx = 1;
		} else {
			dx = x1 - x2;
			incx = -1;
		}
		if (y2 >= y1) {
			dy = y2 - y1;
			incy = 1;
		} else {
			dy = y1 - y2;
			incy = -1;
		}
		x = x1;
		y = y1;
		if (dx >= dy) {
			dy <<= 1;
			balance = dy - dx;
			dx <<= 1;
			while (x != x2) {
				path.add(new Point(x, y));
				if (balance >= 0) {
					y += incy;
					balance -= dx;
				}
				balance += dy;
				x += incx;
			}
			path.add(new Point(x, y));
		} else {
			dx <<= 1;
			balance = dx - dy;
			dy <<= 1;
			while (y != y2) {
				path.add(new Point(x, y));
				if (balance >= 0) {
					x += incx;
					balance -= dy;
				}
				balance += dx;
				y += incy;
			}
			path.add(new Point(x, y));
		}
		return path;
	}
}
