package com.mxxy.game.base;

public class BasePresenter<V> {

	protected V view;

	public void setView(V view) {
		this.view = view;
	}

	public V getView() {
		return view;
	}

	public void destoryView() {
		this.view = null;
	}
}
