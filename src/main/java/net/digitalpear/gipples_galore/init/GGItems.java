package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.init.tags.GGBannerPatternItemTags;
import net.digitalpear.gipples_galore.GGRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

@SuppressWarnings("unused")
public class GGItems {

    public static Item register(String itemID, Item item){
        return GGRegistry.register(Registries.ITEM, new ResourceLocation(GipplesGalore.MOD_ID, itemID), item);
    }
    public static Item createSpawnEgg(EntityType<? extends Mob> type, int PrimaryColor, int SecondaryColor){
        return register(GGRegistry.idOf(type).getPath() + "_spawn_egg", new SpawnEggItem(type, PrimaryColor, SecondaryColor, new Item.Properties()));
    }
    public static Item createInvisibleSpawnEgg(EntityType<? extends Mob> type, int PrimaryColor, int SecondaryColor){
        return register(GGRegistry.idOf(type).getPath() + "_spawn_egg", new SpawnEggItem(type, PrimaryColor, SecondaryColor, new Item.Properties()));
    }
    // Port a Forge: RecordItem y MobBucketItem con Supplier; los constructores directos buscan el
    // sonido o la entidad ya registrados y fallan al crear el item.
    public static Item createDisc(String name, int output, SoundEvent sound, int length){
        return register("music_disc_" + name, new RecordItem(output, () -> sound, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), length));
    }
    public static Item createBucketedMob(EntityType<?> type){
        return register(GGRegistry.idOf(type).getPath() + "_bucket", new MobBucketItem(() -> type, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).craftRemainder(Items.WATER_BUCKET).stacksTo(1)));
    }

    public static final Item GIPPLE_BUCKET = createBucketedMob(GGEntities.GIPPLE);
    public static final Item GIPPLE_SPAWN_EGG = createSpawnEgg(GGEntities.GIPPLE, 13558777, 11642584);
    public static final Item ANEUPLOIDIAN_SPAWN_EGG = createInvisibleSpawnEgg(GGEntities.ANEUPLOIDIAN, 13558777, 9669861);
    public static final Item GELATIN = register("gelatin", new Item(new Item.Properties().food(GGFoodComponents.GELATIN)));
    public static final Item GAPPLE = register("gapple", new Item(new Item.Properties().food(GGFoodComponents.GAPPLE)));
    public static final Item MUSIC_DISC_GIPPLECORE = createDisc("gipplecore", 13, GGSoundEvents.MUSIC_DISC_GIPPLECORE, 113);
    public static final Item GIPPLEPAD = register("gipplepad", new PlaceOnWaterBlockItem(GGBlocks.GIPPLEPAD, new Item.Properties()));
    public static final Item GIPPLE_BANNER_PATTERN = register("gipple_banner_pattern", new BannerPatternItem(GGBannerPatternItemTags.GIPPLE_PATTERN_ITEM, new Item.Properties().stacksTo(1)));

    public static void init() {
    }

    // Port a Forge: BuildCreativeModeTabContentsEvent en lugar de ItemGroupEvents. Mismo orden.
    public static void onCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            addAfter(event, Items.HONEY_BOTTLE, GELATIN);
            addAfter(event, Items.ENCHANTED_GOLDEN_APPLE, GAPPLE);
            addAfter(event, GELATIN, GGBlocks.PLAIN_JELLY, GGBlocks.MILKY_JELLY, GGBlocks.BLAST_JELLY, GGBlocks.MUDDY_JELLY, GGBlocks.INKY_JELLY,
                    GGBlocks.COCOA_JELLY, GGBlocks.FRUITY_JELLY, GGBlocks.BRIGHT_JELLY, GGBlocks.FLORAL_JELLY, GGBlocks.BOUNCY_JELLY, GGBlocks.PRICKLY_JELLY,
                    GGBlocks.WARP_JELLY, GGBlocks.LUMINESCENT_JELLY, GGBlocks.ENCHANTING_JELLY, GGBlocks.FOAMY_JELLY, GGBlocks.SYMPHONIC_JELLY, GGBlocks.SWEET_JELLY

            );
        }
        if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
            addAfter(event, Items.PINK_BANNER, GGBlocks.PLAIN_JELLY, GGBlocks.MILKY_JELLY, GGBlocks.BLAST_JELLY, GGBlocks.MUDDY_JELLY, GGBlocks.INKY_JELLY,
                    GGBlocks.COCOA_JELLY, GGBlocks.FRUITY_JELLY, GGBlocks.BRIGHT_JELLY, GGBlocks.FLORAL_JELLY, GGBlocks.BOUNCY_JELLY, GGBlocks.PRICKLY_JELLY,
                    GGBlocks.WARP_JELLY, GGBlocks.LUMINESCENT_JELLY, GGBlocks.ENCHANTING_JELLY, GGBlocks.FOAMY_JELLY, GGBlocks.SYMPHONIC_JELLY, GGBlocks.SWEET_JELLY
            );
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            addAfter(event, Items.MUSIC_DISC_RELIC, MUSIC_DISC_GIPPLECORE);
            addAfter(event, Items.TADPOLE_BUCKET, GIPPLE_BUCKET);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            addAfter(event, Items.PIGLIN_BANNER_PATTERN, GIPPLE_BANNER_PATTERN);
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            addAfter(event, Items.ALLAY_SPAWN_EGG, ANEUPLOIDIAN_SPAWN_EGG);
            addAfter(event, Items.GLOW_SQUID_SPAWN_EGG, GIPPLE_SPAWN_EGG);
        }


        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            addAfter(event, Items.DEEPSLATE, GGBlocks.GELATITE, GGBlocks.AMOEBALITH);
            addAfter(event, Items.SNOW, GGBlocks.GELATIN_LAYER);
            addAfter(event, Items.GLOW_LICHEN, GGBlocks.GELATINOUS_GROWTH);
            event.accept(GGBlocks.HIBERNATING_GIPPLE);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(GGBlocks.GELATIN_BLOCK);
            addAfter(event, Items.REINFORCED_DEEPSLATE,
                    GGBlocks.GELATITE, GGBlocks.GELATITE_STAIRS, GGBlocks.GELATITE_SLAB, GGBlocks.GELATITE_WALL, GGBlocks.GELATITE_BUTTON,
                    GGBlocks.GELATITE_BRICKS, GGBlocks.GELATITE_BRICK_STAIRS, GGBlocks.GELATITE_BRICK_SLAB, GGBlocks.GELATITE_BRICK_WALL,
                    GGBlocks.AMOEBALITH, GGBlocks.AMOEBALITH_STAIRS, GGBlocks.AMOEBALITH_SLAB, GGBlocks.AMOEBALITH_WALL, GGBlocks.AMOEBALITH_BUTTON,
                    GGBlocks.AMOEBALITH_BRICKS, GGBlocks.AMOEBALITH_BRICK_STAIRS, GGBlocks.AMOEBALITH_BRICK_SLAB, GGBlocks.AMOEBALITH_BRICK_WALL
            );
        }

    }

    private static void addAfter(BuildCreativeModeTabContentsEvent event, ItemLike anchor, ItemLike... items) {
        ItemStack previous = new ItemStack(anchor);
        for (ItemLike item : items) {
            ItemStack stack = new ItemStack(item);
            if (event.getEntries().contains(previous)) {
                event.getEntries().putAfter(previous, stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            } else {
                event.accept(stack);
            }
            previous = stack;
        }
    }
}
