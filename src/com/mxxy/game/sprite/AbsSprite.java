package com.mxxy.game.sprite;

abstract public class AbsSprite implements IPlayersExtend {

	protected Sprite sprite;

	@Override
	public void resetFrames() {
		if (sprite != null)
			sprite.resetFrames();
	}

	@Override
	public void setDirection(int index) {
		if (sprite != null)
			sprite.setDirection(index);
	}

	@Override
	public void update(long elapsedTime) {
		if (sprite != null)
			sprite.update(elapsedTime);
	}

	public Sprite getSprite() {
		return sprite;
	}
}
