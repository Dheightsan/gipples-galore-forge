package net.digitalpear.gipples_galore.init.data;

import net.digitalpear.gipples_galore.init.GGItems;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;

public class GGData {

    public static void init(){
    }

    // Port a Forge: LootTableLoadEvent en lugar de LootTableEvents.MODIFY. Mismas pools.
    public static void onLootTableLoad(LootTableLoadEvent event){
        if (event.getName().equals(BuiltInLootTables.ANCIENT_CITY)){
            event.getTable().addPool(LootPool.lootPool()
                    .name("gipples_galore_ancient_city")
                    .add(LootItem.lootTableItem(GGItems.GELATIN).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(0f, 4.0f))))
                    .add(LootItem.lootTableItem(GGItems.GIPPLE_BANNER_PATTERN).setWeight(1))
                    .build());
        }
        else if (event.getName().equals(BuiltInLootTables.ANCIENT_CITY_ICE_BOX)){
            event.getTable().addPool(LootPool.lootPool()
                    .name("gipples_galore_ancient_city_ice_box")
                    .add(LootItem.lootTableItem(GGItems.GAPPLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(0f, 3f)).when(LootItemRandomChanceCondition.randomChance(0.75f))))
                    .build());
        }
    }
}
