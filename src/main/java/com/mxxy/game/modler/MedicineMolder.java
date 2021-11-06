package com.mxxy.game.modler;

import com.mxxy.game.domain.Item;
import com.mxxy.game.domain.ItemInstance;
import com.mxxy.game.domain.MedicineItem;
import com.mxxy.game.domain.MedicineList;

import java.util.List;

public class MedicineMolder {

    public ItemInstance createItem(String name, MedicineList parses) {
        return this.createItem(name, 1, parses);
    }

    public ItemInstance createItem(String name, int amount, MedicineList parses) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        name = name.trim();
        Item item = this.findItemByName(name, parses);
        return new ItemInstance(item, amount);
    }

    private Item findItemByName(String name, MedicineList parses) {
        return this.getMedicineItem(parses.getData(), name);
    }

    public MedicineItem getMedicineItem(List<MedicineItem> medicineItems, String name) {
        for (MedicineItem medicineItem : medicineItems) {
            if (medicineItem.getName().equals(name)) {
                return medicineItem;
            }
        }
        return null;
    }
}
