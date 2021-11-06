package com.mxxy.game.widget;

import com.mxxy.game.utils.GraphicsStore;
import com.mxxy.game.utils.GraphicsUtils;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class GameGraphics extends Graphics2D {

    public static final int HCENTER = 1;
    public static final int VCENTER = 2;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int TOP = 16;
    public static final int BOTTOM = 32;
    public static final int BASELINE = 64;
    public static final int TRANS_NONE = 0;
    public static final int TRANS_ROT90 = 5;
    public static final int TRANS_ROT180 = 3;
    public static final int TRANS_ROT270 = 6;
    public static final int TRANS_MIRROR = 2;
    public static final int TRANS_MIRROR_ROT90 = 7;
    public static final int TRANS_MIRROR_ROT180 = 1;
    public static final int TRANS_MIRROR_ROT270 = 4;
    private Graphics2D g2d;
    private GraphicsStore store = new GraphicsStore();
    private int width, height;
    private boolean isClose;

    public GameGraphics(BufferedImage awtImage) {
        this.width = awtImage.getWidth();
        this.height = awtImage.getHeight();
        this.g2d = awtImage.createGraphics();
        this.g2d.setClip(0, 0, width, height);
        this.store.save(g2d);
    }

    @Override
    public void addRenderingHints(Map<?, ?> hints) {
        g2d.addRenderingHints(hints);
    }

    @Override
    public void clip(Shape s) {
        g2d.clip(s);
    }

    @Override
    public void draw(Shape s) {
        g2d.draw(s);
    }

    @Override
    public void drawGlyphVector(GlyphVector g, float x, float y) {
        g2d.drawGlyphVector(g, x, y);
    }

    @Override
    public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
        g2d.drawImage(img, xform, obs);
        return false;
    }

    @Override
    public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
        g2d.drawImage(img, op, x, y);
    }

    @Override
    public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
        g2d.drawRenderableImage(img, xform);
    }

    @Override
    public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
        g2d.drawRenderedImage(img, xform);
    }

    @Override
    public void drawString(String str, int x, int y) {
        g2d.drawString(str, x, y);
    }

    @Override
    public void drawString(String str, float x, float y) {
        g2d.drawString(str, x, y);
    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {
        g2d.drawString(iterator, x, y);
    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, float x, float y) {
        g2d.drawString(iterator, x, y);
    }

    @Override
    public void fill(Shape s) {
        g2d.fill(s);
    }

    @Override
    public Color getBackground() {
        return g2d.getBackground();
    }

    @Override
    public void setBackground(Color color) {
        g2d.setBackground(color);
    }

    @Override
    public Composite getComposite() {
        return g2d.getComposite();
    }

    @Override
    public void setComposite(Composite comp) {
        g2d.setComposite(comp);
    }

    @Override
    public GraphicsConfiguration getDeviceConfiguration() {
        return g2d.getDeviceConfiguration();
    }

    @Override
    public FontRenderContext getFontRenderContext() {
        return g2d.getFontRenderContext();
    }

    @Override
    public Paint getPaint() {
        return g2d.getPaint();
    }

    @Override
    public void setPaint(Paint paint) {
        g2d.setPaint(paint);
    }

    @Override
    public Object getRenderingHint(Key hintKey) {
        return g2d.getRenderingHint(hintKey);
    }

    @Override
    public RenderingHints getRenderingHints() {
        return g2d.getRenderingHints();
    }

    @Override
    public void setRenderingHints(Map<?, ?> hints) {
        g2d.setRenderingHints(hints);
    }

    @Override
    public Stroke getStroke() {
        return g2d.getStroke();
    }

    @Override
    public void setStroke(Stroke s) {
        g2d.setStroke(s);
    }

    @Override
    public AffineTransform getTransform() {
        return g2d.getTransform();
    }

    @Override
    public void setTransform(AffineTransform Tx) {
        g2d.setTransform(Tx);
    }

    @Override
    public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
        g2d.hit(rect, s, onStroke);
        return false;
    }

    @Override
    public void rotate(double theta) {
        g2d.rotate(theta);
    }

    @Override
    public void rotate(double theta, double x, double y) {
        g2d.rotate(theta, x, y);
    }

    @Override
    public void scale(double sx, double sy) {
        g2d.scale(sx, sy);
    }

    @Override
    public void setRenderingHint(Key hintKey, Object hintValue) {
        g2d.setRenderingHint(hintKey, hintValue);
    }

    @Override
    public void shear(double shx, double shy) {
        g2d.shear(shx, shy);
    }

    @Override
    public void transform(AffineTransform Tx) {
        g2d.transform(Tx);
    }

    @Override
    public void translate(int x, int y) {
        g2d.translate(x, y);
    }

    @Override
    public void translate(double tx, double ty) {
        g2d.translate(tx, ty);
    }

    @Override
    public void clearRect(int x, int y, int width, int height) {
        g2d.clearRect(x, y, width, height);
    }

    @Override
    public void clipRect(int x, int y, int width, int height) {
        g2d.clipRect(x, y, width, height);
    }

    @Override
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {

        g2d.copyArea(x, y, width, height, dx, dy);
    }

    @Override
    public Graphics create() {
        return getGraphics();
    }

    @Override
    public void dispose() {
        g2d.dispose();
        isClose = true;
    }

    @Override
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        g2d.drawArc(x, y, width, height, startAngle, arcAngle);
    }

    @Override
    public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
        g2d.drawImage(img, x, y, observer);
        return false;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
        g2d.drawImage(img, x, y, bgcolor, observer);
        return false;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
        g2d.drawImage(img, x, y, width, height, observer);
        return false;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
        g2d.drawImage(img, x, y, width, height, bgcolor, observer);
        return false;
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
                             ImageObserver observer) {
        g2d.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
        return false;
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
                             Color bgcolor, ImageObserver observer) {
        g2d.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
        return false;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        g2d.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        g2d.drawOval(x, y, width, height);
    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        g2d.drawPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
        g2d.drawPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        g2d.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        g2d.fillArc(x, y, width, height, startAngle, arcAngle);
    }

    @Override
    public void fillOval(int x, int y, int width, int height) {
        g2d.fillOval(x, y, width, height);
    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        g2d.fillPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        g2d.fillRect(x, y, width, height);
    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        g2d.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    @Override
    public Shape getClip() {
        return g2d.getClip();
    }

    @Override
    public void setClip(Shape clip) {
        g2d.setClip(clip);
    }

    @Override
    public Rectangle getClipBounds() {
        return g2d.getClipBounds();
    }

    @Override
    public Color getColor() {
        return g2d.getColor();
    }

    @Override
    public void setColor(Color c) {
        g2d.setColor(c);
    }

    @Override
    public Font getFont() {
        return g2d.getFont();
    }

    @Override
    public void setFont(Font font) {
        g2d.setFont(font);
    }

    @Override
    public FontMetrics getFontMetrics(Font f) {
        return g2d.getFontMetrics(f);
    }

    @Override
    public void setClip(int x, int y, int width, int height) {
        g2d.setClip(x, y, width, height);
    }

    /**
     * ���ƾ�������
     *
     * @param s
     * @param x
     * @param y
     */
    public void drawCenterString(String s, int x, int y) {
        FontMetrics fontmetrics = g2d.getFontMetrics();
        x -= fontmetrics.stringWidth(s) >> 1;
        y += fontmetrics.getAscent() - fontmetrics.getDescent() >> 1;
        g2d.drawString(s, x, y);
    }

    /***
     * ������Ӱ����
     *
     * @param s
     * @param x
     * @param y
     * @param color
     * @param color1
     * @param k
     */
    public void drawShadeString(String s, int x, int y, Color color, Color color1, int k) {
        g2d.setColor(color);
        g2d.drawString(s, x + k, y + k);
        g2d.setColor(color1);
        g2d.drawString(s, x, y);
    }

    /**
     * ���ƾ�����Ӱ����
     *
     * @param s
     * @param x
     * @param y
     * @param color
     * @param color1
     * @param k
     */
    public void drawCenterShadeString(String s, int x, int y, Color color, Color color1, int k) {
        FontMetrics fontmetrics = g2d.getFontMetrics();
        x -= fontmetrics.stringWidth(s) >> 1;
        y += fontmetrics.getAscent() - fontmetrics.getDescent() >> 1;
        drawShadeString(s, x, y, color, color1, k);
    }

    /**
     * ����3D
     *
     * @param s
     * @param x
     * @param y
     * @param c
     */
    public void draw3DString(String s, int x, int y, Color c) {
        g2d.setColor(Color.black);
        for (int i = -2; i < 40; i++) {
            for (int j = -2; j < 40; j++) {
                g2d.drawString(s, x + i, y + j);
            }
        }
        g2d.setColor(c);
        g2d.drawString(s, x, y);
    }

    public void setAntialiasAll(boolean flag) {
        GraphicsUtils.setAntialiasAll(g2d, flag);
    }

    public void setAntiAlias(boolean flag) {
        GraphicsUtils.setAntialias(g2d, flag);
    }

    public void setAlpha(double alpha) {
        GraphicsUtils.setAlpha(g2d, (float) alpha);
    }

    public void drawSubString(String str, int offset, int len, int x, int y, int anchor) {
        drawString(str.substring(offset, offset + len), x, y, anchor);
    }

    public void drawString(String str, int x, int y, int anchor) {
        int newx = x;
        int newy = y;
        if (anchor == 0) {
            anchor = TOP | LEFT;
        }
        if ((anchor & TOP) != 0) {
            newy += g2d.getFontMetrics().getAscent();
        } else if ((anchor & BOTTOM) != 0) {
            newy -= g2d.getFontMetrics().getDescent();
        }
        if ((anchor & HCENTER) != 0) {
            newx -= g2d.getFontMetrics().stringWidth(str) / 2;
        } else if ((anchor & RIGHT) != 0) {
            newx -= g2d.getFontMetrics().stringWidth(str);
        }
        g2d.drawString(str, newx, newy);
    }

    public void drawRegion(Image img, int x_src, int y_src, int width, int height, int transform, int x_dst, int y_dst,
                           int anchor) {

        if (x_src + width > img.getWidth(null) || y_src + height > img.getHeight(null) || width <= 0 || height <= 0
                || x_src < 0 || y_src < 0) {
            throw new IllegalArgumentException("Image size Exception !");
        }

        AffineTransform t = new AffineTransform();

        int dW = width, dH = height;
        switch (transform) {
            case TRANS_NONE: {
                break;
            }
            case TRANS_ROT90: {
                t.translate(height, 0);
                t.rotate(Math.PI / 2);
                dW = height;
                dH = width;
                break;
            }
            case TRANS_ROT180: {
                t.translate(width, height);
                t.rotate(Math.PI);
                break;
            }
            case TRANS_ROT270: {
                t.translate(0, width);
                t.rotate(Math.PI * 3 / 2);
                dW = height;
                dH = width;
                break;
            }
            case TRANS_MIRROR: {
                t.translate(width, 0);
                t.scale(-1, 1);
                break;
            }
            case TRANS_MIRROR_ROT90: {
                t.translate(height, 0);
                t.rotate(Math.PI / 2);
                t.translate(width, 0);
                t.scale(-1, 1);
                dW = height;
                dH = width;
                break;
            }
            case TRANS_MIRROR_ROT180: {
                t.translate(width, 0);
                t.scale(-1, 1);
                t.translate(width, height);
                t.rotate(Math.PI);
                break;
            }
            case TRANS_MIRROR_ROT270: {
                t.rotate(Math.PI * 3 / 2);
                t.scale(-1, 1);
                dW = height;
                dH = width;
                break;
            }
            default:
                throw new IllegalArgumentException("Bad transform !");
        }

        boolean badAnchor = false;
        if (anchor == 0) {
            anchor = TOP | LEFT;
        }
        if ((anchor & 0x7f) != anchor || (anchor & BASELINE) != 0) {
            badAnchor = true;
        }
        if ((anchor & TOP) != 0) {
            if ((anchor & (VCENTER | BOTTOM)) != 0) {
                badAnchor = true;
            }
        } else if ((anchor & BOTTOM) != 0) {
            if ((anchor & VCENTER) != 0) {
                badAnchor = true;
            } else {
                y_dst -= dH - 1;
            }
        } else if ((anchor & VCENTER) != 0) {
            y_dst -= (dH - 1) >>> 1;
        } else {
            badAnchor = true;
        }
        if ((anchor & LEFT) != 0) {
            if ((anchor & (HCENTER | RIGHT)) != 0) {
                badAnchor = true;
            }
        } else if ((anchor & RIGHT) != 0) {
            if ((anchor & HCENTER) != 0) {
                badAnchor = true;
            } else {
                x_dst -= dW - 1;
            }
        } else if ((anchor & HCENTER) != 0) {
            x_dst -= (dW - 1) >>> 1;
        } else {
            badAnchor = true;
        }

        if (badAnchor) {
            throw new IllegalArgumentException("Bad Anchor");
        }

        AffineTransform savedT = g2d.getTransform();

        g2d.translate(x_dst, y_dst);
        g2d.transform(t);

        g2d.drawImage(img, 0, 0, width, height, x_src, y_src, x_src + width, y_src + height, null);

        g2d.setTransform(savedT);
    }

    @Override
    public void setPaintMode() {
        g2d.setPaintMode();
    }

    @Override
    public void setXORMode(Color c1) {
        g2d.setXORMode(c1);
    }

    public Graphics getGraphics() {
        return g2d;
    }

    public boolean isClose() {
        return isClose;
    }

}
