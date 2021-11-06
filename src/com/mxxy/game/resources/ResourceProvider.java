package com.mxxy.game.resources;

public abstract interface ResourceProvider<E> {

	public abstract E getResource(String paramString);

	public abstract void dispose();
}