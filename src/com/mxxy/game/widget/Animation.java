package com.mxxy.game.widget;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Vector;

import com.mxxy.game.utils.GraphicsUtils;

public class Animation extends AbstractCanvas {
	/** 帧集合 */
	private Vector<AnimationFrame> frames;
	
	/** 当前帧 */
	public AnimationFrame currFrame;
	
	/** 动画总时间 */
	private long totalDuration;
	
	/** 一个动画周期内动画已播放时间 */
	public long animTime;
	
	/** 当前帧序号 */
	private int currentFrameIndex;

	/**播放次数  -1代表循环*/
	private int repeat = -1;

	private int frameCount;

	public Animation() {
		frames = new Vector<AnimationFrame>();
	}

	public synchronized void addFrame(Image image, long duration) {
		this.totalDuration += duration;
		AnimationFrame frame = new AnimationFrame(image, this.totalDuration);
		this.frames.add(frame);
		this.currFrame = frame;
		frameCount = frames.size();
	}

	public synchronized void addFrame(Image image, long duration, int centerX, int centerY) {
		this.totalDuration += duration;
		AnimationFrame frame = new AnimationFrame(image, this.totalDuration, centerX, centerY);
		this.frames.add(frame);
		this.currFrame = frame;
		frameCount = frames.size();
	}

	/**
	 * 重置动画
	 */
	public void reset() {
		animTime = 0;
		currentFrameIndex = 0;
	}

	/**
	 * 获取动画帧
	 * 
	 * @param i
	 * @return
	 */
	public AnimationFrame getFrame(int index) {
		if (index < 0) {
			return frames.get(0);
		} else if (index >= frames.size()) {
			return frames.get(frames.size() - 1);
		}
		return (AnimationFrame) frames.get(index);
	}

	public Vector<AnimationFrame> getFrames() {
		return frames;
	}

	private Object UPDATE_LOCK = new Object();

	@Override
	protected void draw(Graphics2D g, int x, int y, int width, int hight) {
//		g.drawImage(getFrame(currentFrameIndex).getImage(), x, y, null);
		this.currFrame.draw(g, x, y, width, hight);
	}

	/**
	 * 修改图片索引
	 * 
	 * @param elapsedTime
	 */
	public synchronized void update(long elapsedTime) {
		if (repeat == 0) {
			return;
		}
		// if(frames.size()>1){
		// animTime+=elapsedTime;
		// if(animTime>=totalDuration){
		// animTime=animTime%totalDuration;
		// currentFrameIndex=0;
		// currFrame=frames.get(0);
		// }
		// while(animTime>getFrame(currentFrameIndex).getEndTime()){
		// currentFrameIndex++;
		// currFrame=frames.get(currentFrameIndex);
		// }
		// }

		animTime += elapsedTime;
		this.updateToTime(animTime);
	}

	/**
	 * 动画是一帧 表现 所以 加锁等待更新结束唤醒锁
	 * 
	 * @param playTime
	 */
	public void updateToTime(long tiem) {
		synchronized (UPDATE_LOCK) {
			if (frames.size() > 1) {
				animTime = tiem;
				if (animTime >= totalDuration) {
					animTime %= totalDuration;
					repeat -= (repeat > 0) ? 1 : 0;
					if (repeat != 0) {
						currentFrameIndex = 0;
					} else {
						currentFrameIndex = frameCount - 1;
					}
					currFrame = frames.get(currentFrameIndex);
				}
				while (animTime > frames.get(currentFrameIndex).getEndTime()) {
					currentFrameIndex++;
				}
				currFrame = frames.get(currentFrameIndex);
			} else if (frames.size() > 0) {
				currFrame = frames.get(0);
			} else {
				currFrame = null;
			}
			UPDATE_LOCK.notifyAll();
		}
	}

	@Override
	public void dispose() {
		for (AnimationFrame f : this.frames) {
			f.dispose();
		}
		this.frames.clear();
	}

	@Override
	public boolean contains(int paramInt1, int paramInt2) {
		return false;
	}

	public void setRepeat(int repeat) {
		if (this.repeat != repeat) {
			this.repeat = repeat;
			reset();
		}
	}

	/**
	 * 指定文件转换为动画图像
	 * @param fileName 文件名
	 * @param row 行
	 * @param col 列
	 * @param timer 动画时长
	 * @return
	 */
	public Animation getDefaultAnimation(String fileName, int maxFrame, int row, int col, int timer) {
		return getDefaultAnimation(GraphicsUtils.getSplitImages(fileName, row, col, false), maxFrame, timer);
	}

	// TODO
	public Animation getDefaultAnimation(Image[] images, int maxFrame, int timer) {
		Animation animation = new Animation();
		if (maxFrame != -1) {
			for (int i = 0; i < maxFrame; i++) {
				// animation.addFrame(, timer);
			}
		} else {
			for (int i = 0; i < images.length; i++) {
				// animation.addFrame(, timer);
			}
		}
		return animation;
	}

	public synchronized Image getImage() {
		return this.currFrame == null ? null : this.currFrame.getImage();
	}

	public int getRepeat() {
		return repeat;
	}

	public synchronized int getWidth() {
		return this.currFrame == null ? 0 : this.currFrame.getImage().getWidth(null);
	}

	public synchronized int getHeight() {
		return this.currFrame == null ? 0 : this.currFrame.getImage().getHeight(null);
	}

	public synchronized int getCenterX() {
		return this.currFrame == null ? 0 : this.currFrame.getCenterX();
	}

	public synchronized int getCenterY() {
		return this.currFrame == null ? 0 : this.currFrame.getCenterY();
	}

	public void setIndex(int currentFrameIndex) {
		this.currentFrameIndex = currentFrameIndex;
	}

	public int getCurrentFrameIndex() {
		return currentFrameIndex;
	}
	
	/**
	 * 判断是否播放结束
	 */
	public void waitFor() {
		synchronized (UPDATE_LOCK) {
			while (true) {
				if(repeat==0 && currentFrameIndex == frameCount-1 || repeat ==-1) {
					break;
				}
				try {
					UPDATE_LOCK.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
