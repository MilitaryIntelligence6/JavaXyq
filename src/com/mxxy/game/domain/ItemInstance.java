package com.mxxy.game.domain;

import com.mxxy.game.utils.StringUtils;

public class ItemInstance {

    transient private Item item;

    private int amount;  //数量

    private String itemId = "-1";

    private String name;

    private String type;

    /**
     * @param itemVO
     * @param i
     */
    public ItemInstance(Item item, int amount) {
        this.setItem(item);
        this.setAmount(amount);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        this.itemId = item.getId();
        this.name = item.getName();
        this.type = item.getType();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }

    /**
     * 改变物品数量
     *
     * @param n 改变量
     */
    public int alterAmount(int n) {
        if (n < 0 && this.amount + n < 0) {
            n = -this.amount;
        }
        this.amount += n;
        return n;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ItemInstance) {
            ItemInstance it = (ItemInstance) obj;
            return getItemId() == it.getItemId() && StringUtils.equals(it.getName(), getName());
        }
        return false;
    }

    //---------- delegated methods ---------------//

    public String getId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return item.getDescription();
    }

    public short getLevel() {
        return item.getLevel();
    }

    public long getPrice() {
        return item.getPrice();
    }


}
