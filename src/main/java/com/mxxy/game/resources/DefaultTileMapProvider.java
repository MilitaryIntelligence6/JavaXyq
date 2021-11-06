package com.mxxy.game.resources;

import com.mxxy.game.config.MapConfigImpl;
import com.mxxy.game.widget.TileMap;

import java.awt.*;
import java.io.*;

/**
 * 地图提供者
 *
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class DefaultTileMapProvider implements IMapProvider {
    private MyRandomAccessFile mapFile;
    private int[][] blockOffsetTable;
    private int width;
    private int height;
    private int xBlockCount;
    private int yBlockCount;
    private ImageLoadThread imageLoader;

    public DefaultTileMapProvider() {
        this.imageLoader = new ImageLoadThread();
        this.imageLoader.start();
    }

    /**
     * 加载JPG图片
     */
    public Image getBlock(int x, int y) {
        byte[] data = getJpegData(x, y);
        Image image = Toolkit.getDefaultToolkit().createImage(data);
        this.imageLoader.loadImage(image);
        return image;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * 获取JPG图片
     *
     * @param x
     * @param y
     * @return
     */
    public byte[] getJpegData(int x, int y) {
        byte[] jpegBuf = (byte[]) null;
        try {
            int len = 0;
            this.mapFile.seek(this.blockOffsetTable[x][y]);
            if (isJPEGData()) {
                len = this.mapFile.readInt2();
                jpegBuf = new byte[len];
                this.mapFile.readFully(jpegBuf);
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream(4096);
            boolean isFilled = false;
            bos.reset();
            bos.write(jpegBuf, 0, 2);
            isFilled = false;
            int p = 4;
            int start;
            for (start = 4; p < jpegBuf.length - 2; p++) {
                if ((!isFilled) && (jpegBuf[p] == -1)) {
                    p++;
                    if (jpegBuf[p] == -38) {
                        isFilled = true;
                        jpegBuf[(p + 2)] = 12;
                        bos.write(jpegBuf, start, p + 10 - start);
                        bos.write(0);
                        bos.write(63);
                        bos.write(0);
                        start = p + 10;
                        p += 9;
                    }
                }
                if ((isFilled) && (jpegBuf[p] == -1)) {
                    bos.write(jpegBuf, start, p + 1 - start);
                    bos.write(0);
                    start = p + 1;
                }
            }
            bos.write(jpegBuf, start, jpegBuf.length - start);
            jpegBuf = bos.toByteArray();
            bos.close();
        } catch (Exception e) {
            System.err.println("获取JPEG 数据块失败：" + e.getMessage());
        }
        return jpegBuf;
    }

    /**
     * 获取地图资源
     */
    public TileMap getResource(String resId) {
        return loadMap(resId);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    public int getXBlockCount() {
        return this.xBlockCount;
    }

    public int getYBlockCount() {
        return this.yBlockCount;
    }

    private boolean isJPEGData() {
        byte[] buf = new byte[4];
        try {
            int len = this.mapFile.read();
            this.mapFile.skipBytes(3 + len * 4);
            this.mapFile.read(buf);
            String str = new String(buf);
            return str.equals("GEPJ");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean isValidMapFile() {
        byte[] buf = new byte[4];
        try {
            this.mapFile.read(buf);
            String str = new String(buf);
            return str.equals("0.1M");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void loadHeader() {
        if (!isValidMapFile()) {
            throw new IllegalArgumentException("非梦幻地图格式文件!");
        }
        try {
            this.width = this.mapFile.readInt2();
            this.height = this.mapFile.readInt2();
            this.xBlockCount = (int) Math.ceil(this.width / 320.0D);
            this.yBlockCount = (int) Math.ceil(this.height / 240.0D);
            this.blockOffsetTable = new int[this.xBlockCount][this.yBlockCount];
            for (int y = 0; y < this.yBlockCount; y++) {
                for (int x = 0; x < this.xBlockCount; x++) {
                    this.blockOffsetTable[x][y] = this.mapFile.readInt2();
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("地图解码失败:" + e.getMessage());
        }
    }

    /**
     * 加载地图
     *
     * @param id
     * @return
     */
    private TileMap loadMap(String id) {
        if (this.mapFile != null) {
            try {
                this.mapFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.blockOffsetTable = null;
        }
        MapConfigImpl cfg = (MapConfigImpl) ResourceStores.getInstance().getMapConfig(id);
        if (cfg != null) {
            try {
                File file = new File(cfg.getPath());
                this.mapFile = new MyRandomAccessFile(file, "r");
                loadHeader();
                return new TileMap(this, cfg);
            } catch (Exception e) {
                System.err.println("create map decoder failed!");
                e.printStackTrace();
            }
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public void dispose() {
        try {
            this.mapFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.blockOffsetTable = null;
        this.imageLoader.stop();
    }

    /**
     * 图像加载线程
     */
    public class ImageLoadThread extends Thread {
        @SuppressWarnings("serial")
        protected Component component = new Component() {
        };
        protected MediaTracker tracker = new MediaTracker(this.component); // 图像跟踪器
        private Image image;
        private boolean isCompleted;
        private boolean isFinished;
        private int mediaTrackerID;

        public ImageLoadThread() {
            setDaemon(true);
            setName("ImageLoadThread");
        }

        private int getNextID() {
            return ++this.mediaTrackerID;
        }

        public boolean isCompleted() {
            return this.isCompleted;
        }

        public boolean isFinished() {
            return this.isFinished;
        }

        public void run() {
            synchronized (this) {
                if (this.image != null) {
                    this.isFinished = false;
                    this.isCompleted = false;
                    int id = getNextID();
                    this.tracker.addImage(this.image, id);
                    try {
                        this.tracker.waitForID(id, 0L);
                        this.isCompleted = true;
                    } catch (InterruptedException e) {
                        System.err.println("INTERRUPTED while loading Image");
                    }
                    this.tracker.removeImage(this.image, id);
                    this.isFinished = true;
                    this.image = null;
                    notifyAll();
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void loadImage(Image image) {
            this.image = image;
            synchronized (this) {
                notifyAll();
            }
        }
    }

    class MyRandomAccessFile extends RandomAccessFile {

        public MyRandomAccessFile(File file, String mode) throws FileNotFoundException {
            super(file, mode);

        }

        public MyRandomAccessFile(String name, String mode) throws FileNotFoundException {
            super(name, mode);
        }

        public int readInt2() throws IOException {
            int ch1 = this.read();
            int ch2 = this.read();
            int ch3 = this.read();
            int ch4 = this.read();
            if ((ch1 | ch2 | ch3 | ch4) < 0) {
                throw new EOFException();
            }
            return ((ch1 << 0) + (ch2 << 8) + (ch3 << 16) + (ch4 << 24));
        }
    }
}
