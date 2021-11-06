package com.mxxy.game.resources;

import com.mxxy.game.widget.TileMap;

/**
 * Provider
 */
public interface IMapProvider extends ResourceProvider<TileMap> {

    public abstract int getWidth();

    public abstract int getHeight();
}
