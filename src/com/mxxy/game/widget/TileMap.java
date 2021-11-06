package com.mxxy.game.widget;

import com.mxxy.game.config.MapConfigImpl;
import com.mxxy.game.resources.DefaultTileMapProvider;

import java.awt.*;
import java.lang.ref.SoftReference;

public class TileMap extends AbstractCanvas {
    private static final int MAP_BLOCK_WIDTH = 320;
    private static final int MAP_BLOCK_HEIGHT = 240;
    private DefaultTileMapProvider provider;
    private SoftReference<Image>[][] blockTable; // 软引用对象
    private int xBlockCount;
    private int yBlockCount;
    private int width;
    private int height;
    private MapConfigImpl config;

    @SuppressWarnings("unchecked")
    public TileMap(DefaultTileMapProvider provider, MapConfigImpl cfg) {
        this.config = cfg;
        this.xBlockCount = provider.getXBlockCount();
        this.yBlockCount = provider.getYBlockCount();
        this.width = provider.getWidth();
        this.height = provider.getHeight();
        this.blockTable = new SoftReference[this.xBlockCount][this.yBlockCount];
        this.provider = provider;
    }

    @Override
    protected void draw(Graphics2D g2, int x, int y, int width, int height) {
        Point pFirstBlock = viewToBlock(x, y);
        int dx = pFirstBlock.x * MAP_BLOCK_WIDTH - x;
        int dy = pFirstBlock.y * MAP_BLOCK_HEIGHT - y;
        g2.translate(dx, dy);

        int xCount = 1 + (width - dx - 1) / MAP_BLOCK_WIDTH;
        int yCount = 1 + (height - dy - 1) / MAP_BLOCK_HEIGHT;

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                Image b = getBlock(i + pFirstBlock.x, j + pFirstBlock.y);
                g2.drawImage(b, i * MAP_BLOCK_WIDTH, j * MAP_BLOCK_HEIGHT, null);
            }
        }
    }

    public void prepare(int x, int y, int width, int height) {
        Point pFirstBlock = viewToBlock(x, y);

        int dx = pFirstBlock.x * MAP_BLOCK_WIDTH - x;
        int dy = pFirstBlock.y * MAP_BLOCK_HEIGHT - y;

        int xCount = 1 + (width - dx - 1) / MAP_BLOCK_WIDTH;
        int yCount = 1 + (height - dy - 1) / MAP_BLOCK_HEIGHT;

        Image[][] images = new Image[xCount][yCount];
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                Image img = getBlock(i + pFirstBlock.x, j + pFirstBlock.y);
                images[i][j] = img;
            }
        }
    }

    private int checkTable() {
        int count = 0;
        int width = this.blockTable.length;
        int height = this.blockTable[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                SoftReference<Image> reference = this.blockTable[i][j];
                if ((reference != null) && (reference.get() != null)) {
                    count++;
                }
            }
        }
        return count;
    }

    private Image getBlock(int x, int y) {
        SoftReference<Image> reference = this.blockTable[x][y];
        if ((reference == null) || (reference.get() == null)) {
            reference = new SoftReference<Image>(this.provider.getBlock(x, y));
            this.blockTable[x][y] = reference;
        }
        checkTable();
        return (Image) reference.get();
    }

    public int getXBlockCount() {
        return this.xBlockCount;
    }

    public void setXBlockCount(int blockCount) {
        this.xBlockCount = blockCount;
    }

    public int getYBlockCount() {
        return this.yBlockCount;
    }

    public void setYBlockCount(int blockCount) {
        this.yBlockCount = blockCount;
    }

    private Point viewToBlock(int x, int y) {
        Point p = new Point();
        p.x = (x / MAP_BLOCK_WIDTH);
        p.y = (y / MAP_BLOCK_HEIGHT);
        if (p.x < 0) {
            p.x = 0;
        }
        if (p.y < 0) {
            p.y = 0;
        }
        return p;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void dispose() {
        this.provider.dispose();
        this.provider = null;
        for (SoftReference<Image>[] refs : this.blockTable) {
            for (SoftReference<Image> ref : refs) {
                if (ref != null) {
                    ref.clear();
                }
            }
        }
        this.blockTable = null;
    }

    public MapConfigImpl getConfig() {
        return this.config;
    }

    public void setConfig(MapConfigImpl config) {
        this.config = config;
    }

    @Override
    public boolean contains(int x, int y) {
        return true;
    }
}
