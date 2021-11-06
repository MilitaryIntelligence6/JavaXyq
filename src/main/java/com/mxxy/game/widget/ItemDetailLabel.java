package com.mxxy.game.widget;

import com.mxxy.game.domain.Item;
import com.mxxy.game.domain.ItemInstance;
import com.mxxy.game.domain.MedicineItem;
import com.mxxy.game.utils.GraphicsUtils;
import com.mxxy.game.utils.SpriteFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZAB 道具提示框 2018年5月28日
 */
public class ItemDetailLabel extends PromptLabel {

    private static final int IMG_WIDTH = 120;

    private static final int IMG_TOP = 10;

    private static final int TITLE_POS = 30;

    private static Font titleFont = new Font("宋体", Font.BOLD, 18);

    private ItemInstance item;

    private Animation anim;

    public ItemDetailLabel() {
        super("");
        setSize(310, 170);
    }

    public ItemDetailLabel(ItemInstance item) {
        super("");
        this.setItem(item);
        setSize(310, 170);
    }

    public void setItem(ItemInstance item) {
        this.item = item;
        this.anim = SpriteFactory.loadAnimation("res/item/item120/" + item.getId() + ".tcp");
    }

    protected void paintComponent(Graphics g) {
        if (this.item == null) {
            return;
        }
        int imgX = (IMG_WIDTH - this.anim.getWidth()) / 2;
        if (this.anim != null) {
            this.anim.drawBitmap(g, imgX + this.anim.getCenterX(), IMG_TOP + this.anim.getCenterY());
        }
        g.translate(IMG_WIDTH, TITLE_POS);
        g.setColor(Color.YELLOW);
        g.setFont(titleFont);
        g.drawString(item.getName(), 0, 0);// title
        List<String> strs = new ArrayList<String>();
        // 说明
        strs.add(item.getDescription());
        strs.add("【等级】" + item.getLevel());
        // 功效
        Item _item = item.getItem();
        if (_item instanceof MedicineItem) {
            MedicineItem mitem = (MedicineItem) _item;
            String efficacy = mitem.getEfficacy();
            if (efficacy != null) {
                strs.add("【功效】" + efficacy);
                strs.add("#Y" + efficacy);
            }
            if (mitem.getLevel() == 3) {
                strs.add("#Y" + mitem.actualEfficacy());
            }
        }
        GraphicsUtils.setAntialias(g, true);
        drawStrings(g, strs);
        g.translate(-IMG_WIDTH, -TITLE_POS);
    }

    private void drawStrings(Graphics g, List<String> lines) {
        FontMetrics fm = g.getFontMetrics();
        int fw = fm.charWidth("中".charAt(0));
        int fh = fm.getHeight();
        int textWidth = getWidth() - IMG_WIDTH;
        int lineLen = textWidth / fw;
        int dy = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith("#Y")) {
                g.setColor(Color.YELLOW);
                line = line.substring(2);
            } else {
                g.setColor(Color.WHITE);
            }
            int index = 0;
            int count = line.length();
            String str;
            while (index < count) {
                dy += fh;
                str = line.substring(index, index + Math.min(lineLen, count - index));
                index += str.length();
                g.drawString(str, 0, dy);
            }
        }
    }
}
