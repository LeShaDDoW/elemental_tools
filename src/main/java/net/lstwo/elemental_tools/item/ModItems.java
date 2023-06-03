package net.lstwo.elemental_tools.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lstwo.elemental_tools.FullItem;
import net.lstwo.elemental_tools.item.axe.AirAxe;
import net.lstwo.elemental_tools.item.axe.EarthAxe;
import net.lstwo.elemental_tools.item.axe.FireAxe;
import net.lstwo.elemental_tools.item.axe.WaterAxe;
import net.lstwo.elemental_tools.item.hoe.AirHoe;
import net.lstwo.elemental_tools.item.hoe.EarthHoe;
import net.lstwo.elemental_tools.item.hoe.FireHoe;
import net.lstwo.elemental_tools.item.hoe.WaterHoe;
import net.lstwo.elemental_tools.item.pickaxe.AirPickaxe;
import net.lstwo.elemental_tools.item.pickaxe.EarthPickaxe;
import net.lstwo.elemental_tools.item.pickaxe.FirePickaxe;
import net.lstwo.elemental_tools.item.pickaxe.WaterPickaxe;
import net.lstwo.elemental_tools.item.shovel.AirShovel;
import net.lstwo.elemental_tools.item.shovel.EarthShovel;
import net.lstwo.elemental_tools.item.shovel.FireShovel;
import net.lstwo.elemental_tools.item.shovel.WaterShovel;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    static List<FullItem> itemList = new ArrayList<>();

    public static final Item FIRE_SHOVEL = createCustomItem("fire_shovel", new FireShovel(ToolMaterials.DIAMOND));
    public static final Item FIRE_PICKAXE = createCustomItem("fire_pickaxe", new FirePickaxe(ToolMaterials.DIAMOND));
    public static final Item FIRE_AXE = createCustomItem("fire_axe", new FireAxe(ToolMaterials.DIAMOND));
    public static final Item FIRE_HOE = createCustomItem("fire_hoe", new FireHoe(ToolMaterials.DIAMOND));

    public static final Item WATER_SHOVEL = createCustomItem("water_shovel", new WaterShovel(ToolMaterials.GOLD));
    public static final Item WATER_PICKAXE = createCustomItem("water_pickaxe", new WaterPickaxe(ToolMaterials.GOLD));
    public static final Item WATER_AXE = createCustomItem("water_axe", new WaterAxe(ToolMaterials.GOLD));
    public static final Item WATER_HOE = createCustomItem("water_hoe", new WaterHoe(ToolMaterials.GOLD));

    public static final Item EARTH_SHOVEL = createCustomItem("earth_shovel", new EarthShovel(ToolMaterials.IRON));
    public static final Item EARTH_PICKAXE = createCustomItem("earth_pickaxe", new EarthPickaxe(ToolMaterials.IRON));
    public static final Item EARTH_AXE = createCustomItem("earth_axe", new EarthAxe(ToolMaterials.IRON));
    public static final Item EARTH_HOE = createCustomItem("earth_hoe", new EarthHoe(ToolMaterials.IRON));

    public static final Item AIR_SHOVEL = createCustomItem("air_shovel", new AirShovel(ToolMaterials.DIAMOND));
    public static final Item AIR_PICKAXE = createCustomItem("air_pickaxe", new AirPickaxe(ToolMaterials.DIAMOND));
    public static final Item AIR_AXE = createCustomItem("air_axe", new AirAxe(ToolMaterials.DIAMOND));
    public static final Item AIR_HOE = createCustomItem("air_hoe", new AirHoe(ToolMaterials.DIAMOND));


    public static Item createItem(String name, FabricItemSettings itemSettings) {
        Item item = new Item(itemSettings);
        itemList.add(new FullItem(item, name));
        return item;
    }

    public static Item createCustomItem(String name, Item item) {
        itemList.add(new FullItem(item, name));
        return item;
    }

    public static void registerItems() {
        for(FullItem item : itemList) {
            Registry.register(Registry.ITEM, new Identifier("elemental_tools", item.getName()), item.getItem());
        }
    }
}
