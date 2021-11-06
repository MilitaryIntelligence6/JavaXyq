package com.mxxy.game.was;

import com.mxxy.game.utils.FileUtils;
import com.mxxy.game.widget.RichLabel;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Toolkit {
    private static Toolkit instance = new Toolkit();

    public static Toolkit getInstance() {
        return instance;
    }

    /**
     * 创建流
     *
     * @param filename 路径
     * @return
     */
    public static InputStream getInputStream(String filename) {
        InputStream is = Toolkit.class.getResourceAsStream(filename);
        if (is == null) {
            try {
                is = new FileInputStream(FileUtils.getPath(filename));
            } catch (FileNotFoundException e) {
                System.out.println(e);
                System.err.println("找不到文件");
            }
        }
        return is;
    }

    /**
     * 读取字节到byte
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] getResourceData(String filename) throws IOException {
        InputStream is = getInputStream(filename);
        if (is == null) {
            return null;
        }
        byte[] buf = new byte[is.available()];
        int count = 0;
        while (is.available() > 0) {
            count += is.read(buf, count, is.available());
        }
        return buf;
    }

    /**
     * 根据字节创建Image 对象
     *
     * @param filename
     * @return Image
     */
    public static Image createImageFromResource(String filename) {
        byte[] data = (byte[]) null;
        try {
            data = getResourceData(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data == null) {
            return null;
        }
        return java.awt.Toolkit.getDefaultToolkit().createImage(data);
    }

    public static void sleep(long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void sleep(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public RichLabel createRichLabel(int x, int y, int width, int height, String text) {
        RichLabel label = new RichLabel(text);
        label.setLocation(x, y);
        label.computeSize(width);
        label.setSize(width, 100);
        return label;
    }
}
