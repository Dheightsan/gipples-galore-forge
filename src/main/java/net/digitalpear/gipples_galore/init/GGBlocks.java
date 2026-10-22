package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.blocks.GelatinBlock;
import net.digitalpear.gipples_galore.common.blocks.GelatinLayerBlock;
import net.digitalpear.gipples_galore.common.blocks.GelatinousGrowthBlock;
import net.digitalpear.gipples_galore.common.blocks.HibernatingGippleBlock;
import net.digitalpear.gipples_galore.common.blocks.jelly.*;
import net.digitalpear.gipples_galore.init.data.GGBlockSoundGroups;
import net.digitalpear.gipples_galore.init.data.sets.StoneSets;
import net.minecraft.world.level.block.*;
import net.digitalpear.gipples_galore.GGRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("unused")
public class GGBlocks {

    public static final Map<Block, Item> JELLY = new HashMap<>();

    public static Block createBlockWithItem(String blockID, Block block){
        createBlockItem(blockID, block);
        return GGRegistry.register(Registries.BLOCK, new ResourceLocation(GipplesGalore.MOD_ID, blockID), block);
    }
    public static BlockItem createBlockItem(String blockID, Block block){
        return GGRegistry.register(Registries.ITEM, new ResourceLocation(GipplesGalore.MOD_ID, blockID), new BlockItem(block, new Item.Properties()));
    }
    public static Block createJellyBlockWithItem(String blockID, Block block, Item ingredient){
        createBlockItem(blockID, block);
        Block jelly = GGRegistry.register(Registries.BLOCK, new ResourceLocation(GipplesGalore.MOD_ID, blockID), block);
        JELLY.put(jelly,ingredient);
        return jelly;
    }


    public static Block createBlockWithoutItem(String blockID, Block block){
        return GGRegistry.register(Registries.BLOCK, new ResourceLocation(GipplesGalore.MOD_ID, blockID), block);
    }

    public static BlockBehaviour.Properties jellySettings(MapColor color){
        return BlockBehaviour.Properties.of().mapColor(color).strength(0.1f, 0.2f).sound(SoundType.SLIME_BLOCK).pushReaction(PushReaction.DESTROY).noOcclusion();
    }
    public static BlockBehaviour.Properties jellySettings(MapColor color, int luminance){
        return jellySettings(color).lightLevel((state) -> {
            if(state.getValue(JellyBlock.HALVED)) return (int)(luminance / 1.5);
            return luminance;
        });
    }

    // Port a Forge: FlowerPotBlock con Supplier; la planta se anota en la maceta vacia en GipplesGalore.
    public static Block makePottedPlant(Block base){
        return createBlockWithoutItem("potted_" + GGRegistry.idOf(base).getPath(), new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> base, BlockBehaviour.Properties.copy(Blocks.POTTED_WARPED_FUNGUS).lightLevel(state -> base.defaultBlockState().getLightEmission()).mapColor(base.defaultMapColor())));
    }

    public static final Block PLAIN_JELLY = createJellyBlockWithItem("plain_jelly", new JellyBlock(jellySettings(MapColor.ICE)),Items.SUGAR);
    public static final Block MILKY_JELLY = createJellyBlockWithItem("milky_jelly", new MilkyJellyBlock(jellySettings(MapColor.SNOW)), Items.MILK_BUCKET);
    public static final Block BRIGHT_JELLY = createJellyBlockWithItem( "bright_jelly", new BrightJellyBlock(jellySettings(MapColor.COLOR_ORANGE,14)),Items.GLOW_BERRIES);
    public static final Block SYMPHONIC_JELLY = createJellyBlockWithItem("symphonic_jelly", new SymphonicJellyBlock(jellySettings(MapColor.COLOR_PURPLE)), Items.CHORUS_FRUIT);
    public static final Block LUMINESCENT_JELLY = createJellyBlockWithItem( "luminescent_jelly", new LuminescentJellyBlock(jellySettings(MapColor.COLOR_LIGHT_BLUE,12)), Items.GLOW_INK_SAC);
    public static final Block FLORAL_JELLY = createJellyBlockWithItem("floral_jelly", new FloralJellyBlock(jellySettings(MapColor.COLOR_YELLOW)), Items.HONEY_BOTTLE);
    public static final Block BOUNCY_JELLY = createJellyBlockWithItem("bouncy_jelly", new BouncyJellyBlock(jellySettings(MapColor.COLOR_LIGHT_GREEN)), Items.SLIME_BALL);
    public static final Block SWEET_JELLY = createJellyBlockWithItem("sweet_jelly", new SweetJellyBlock(jellySettings(MapColor.COLOR_PINK)), Items.SWEET_BERRIES);
    public static final Block MUDDY_JELLY = createJellyBlockWithItem("muddy_jelly", new JellyBlock(jellySettings(MapColor.COLOR_GRAY)), Items.MUD);
    public static final Block BLAST_JELLY = createJellyBlockWithItem("blast_jelly", new JellyBlock(jellySettings(MapColor.COLOR_LIGHT_GRAY)), Items.GUNPOWDER);
    public static final Block WARP_JELLY = createJellyBlockWithItem("warp_jelly", new WarpJellyBlock(jellySettings(MapColor.COLOR_CYAN)), Items.ENDER_PEARL);
    public static final Block FOAMY_JELLY = createJellyBlockWithItem("foamy_jelly", new JellyBlock(jellySettings(MapColor.COLOR_PURPLE)), Items.DRAGON_BREATH);
    public static final Block ENCHANTING_JELLY = createJellyBlockWithItem("enchanting_jelly", new JellyBlock(jellySettings(MapColor.COLOR_BLUE)), Items.LAPIS_LAZULI);
    public static final Block COCOA_JELLY = createJellyBlockWithItem("cocoa_jelly", new SweetJellyBlock(jellySettings(MapColor.COLOR_BROWN)), Items.COCOA_BEANS);
    public static final Block PRICKLY_JELLY = createJellyBlockWithItem("prickly_jelly", new BouncyJellyBlock(jellySettings(MapColor.COLOR_GREEN)), Items.CACTUS);
    public static final Block FRUITY_JELLY = createJellyBlockWithItem("fruity_jelly", new SweetJellyBlock(jellySettings(MapColor.FIRE)), Items.MELON_SLICE);
    public static final Block INKY_JELLY = createJellyBlockWithItem( "inky_jelly", new InkyJellyBlock(jellySettings(MapColor.COLOR_BLACK)), Items.INK_SAC);


    public static final Block GIPPLEPAD = createBlockWithoutItem("gipplepad", new WaterlilyBlock(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).instabreak().sound(SoundType.LILY_PAD).noOcclusion()));

    public static final Block GELATIN_LAYER = createBlockWithItem("gelatin_layer",
            new GelatinLayerBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).mapColor(MapColor.ICE)));

    public static final Block GELATITE = createBlockWithItem("gelatite",
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .mapColor(MapColor.COLOR_CYAN)
                    .sound(GGBlockSoundGroups.GELATITE)));

    public static final Block GELATITE_STAIRS = StoneSets.stoneStairs(GELATITE);
    public static final Block GELATITE_SLAB = StoneSets.stoneSlab(GELATITE);
    public static final Block GELATITE_WALL = StoneSets.stoneWall(GELATITE);
    public static final Block GELATITE_PRESSURE_PLATE = StoneSets.stonePressurePlate(GELATITE);
    public static final Block GELATITE_BUTTON = StoneSets.stoneButton(GELATITE);

    public static final Block GELATITE_BRICKS = createBlockWithItem("gelatite_bricks",
            new Block(BlockBehaviour.Properties.copy(GELATITE)));
    public static final Block GELATITE_BRICK_STAIRS = StoneSets.stoneStairs(GELATITE_BRICKS);
    public static final Block GELATITE_BRICK_SLAB = StoneSets.stoneSlab(GELATITE_BRICKS);
    public static final Block GELATITE_BRICK_WALL = StoneSets.stoneWall(GELATITE_BRICKS);

    public static final Block CHISELED_GELATITE_BRICKS = createBlockWithItem("chiseled_gelatite_bricks",
            new Block(BlockBehaviour.Properties.copy(GELATITE)));


    public static final Block AMOEBALITH = createBlockWithItem("amoebalith",
            new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
            .sound(GGBlockSoundGroups.AMOEBALITH)
            .mapColor(MapColor.COLOR_PURPLE)));

    public static final Block AMOEBALITH_STAIRS = StoneSets.stoneStairs(AMOEBALITH);
    public static final Block AMOEBALITH_SLAB = StoneSets.stoneSlab(AMOEBALITH);
    public static final Block AMOEBALITH_WALL = StoneSets.stoneWall(AMOEBALITH);
    public static final Block AMOEBALITH_PRESSURE_PLATE = StoneSets.stonePressurePlate(AMOEBALITH);
    public static final Block AMOEBALITH_BUTTON = StoneSets.stoneButton(AMOEBALITH);

    public static final Block CHISELED_AMOEBALITH_BRICKS = createBlockWithItem("chiseled_amoebalith_bricks",
            new Block(BlockBehaviour.Properties.copy(AMOEBALITH)));

    public static final Block AMOEBALITH_BRICKS = createBlockWithItem("amoebalith_bricks",
            new Block(BlockBehaviour.Properties.copy(AMOEBALITH)));
    public static final Block AMOEBALITH_BRICK_STAIRS = StoneSets.stoneStairs(AMOEBALITH_BRICKS);
    public static final Block AMOEBALITH_BRICK_SLAB = StoneSets.stoneSlab(AMOEBALITH_BRICKS);
    public static final Block AMOEBALITH_BRICK_WALL = StoneSets.stoneWall(AMOEBALITH_BRICKS);

    public static final Block HIBERNATING_GIPPLE = createBlockWithItem("hibernating_gipple",
            new HibernatingGippleBlock(BlockBehaviour.Properties.of()
                .sound(SoundType.HONEY_BLOCK)
                .noOcclusion()
                .mapColor(GELATIN_LAYER.defaultMapColor())
                .destroyTime(0.8f)
                .pushReaction(PushReaction.DESTROY)
                .noLootTable()
                .randomTicks()
            ));

    public static final Block GELATIN_BLOCK = createBlockWithItem("gelatin_block",
            new GelatinBlock(BlockBehaviour.Properties.of()
                    .destroyTime(0.4f)
                    .mapColor(GELATIN_LAYER.defaultMapColor())
                    .sound(SoundType.HONEY_BLOCK)

            ));

    public static final Block GELATINOUS_GROWTH = createBlockWithItem("gelatinous_growth",
            new GelatinousGrowthBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
                    .mapColor(GELATIN_LAYER.defaultMapColor())
                    .sound(SoundType.HONEY_BLOCK)
            ));

    public static final Block POTTED_GELATINOUS_GROWTH = createBlockWithoutItem("potted_gelatinous_growth",new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> GELATINOUS_GROWTH, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));


    public static void init(){

    }
}
