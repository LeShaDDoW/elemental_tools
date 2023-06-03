package net.lstwo.elemental_tools;

import net.minecraft.item.Item;

public class FullItem {
    private Item item;
    private String itemName;

    public FullItem(Item item, String itemName) {
        this.item = item;
        this.itemName = itemName;
    }

    public Item getItem() {
        return item;
    }

    public String getName() {
        return itemName;
    }
}
