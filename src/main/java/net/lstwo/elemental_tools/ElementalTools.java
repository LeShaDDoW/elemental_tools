package net.lstwo.elemental_tools;

import net.fabricmc.api.ModInitializer;
import net.lstwo.elemental_tools.item.ModItems;

public class ElementalTools implements ModInitializer {
    @Override
    public void onInitialize() {
        ModItems.registerItems();
    }
}
