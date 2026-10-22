package net.digitalpear.gipples_galore.init.data.sets;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.world.level.block.*;
import net.digitalpear.gipples_galore.GGRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.PushReaction;


public class StoneSets {

    public static Block createBlockWithItem(String blockID, Block block){
        createBlockItem(blockID, block);
        return GGRegistry.register(Registries.BLOCK, new ResourceLocation(GipplesGalore.MOD_ID, blockID), block);
    }
    public static BlockItem createBlockItem(String blockID, Block block){
        return GGRegistry.register(Registries.ITEM, new ResourceLocation(GipplesGalore.MOD_ID, blockID), new BlockItem(block, new Item.Properties()));
    }

    public static Block createBlockWithoutItem(String blockID, Block block){
        return GGRegistry.register(Registries.BLOCK, new ResourceLocation(GipplesGalore.MOD_ID, blockID), block);
    }


    public static Block fullStoneSet(String name, BlockBehaviour.Properties settings) {
        Block baseBlock = createBlockWithItem(name, new Block(settings));
        stoneStairs(baseBlock);
        stoneSlab(baseBlock);
        stoneWall(baseBlock);
        stoneButton(baseBlock);
        stonePressurePlate(baseBlock);
        return baseBlock;
    }

    public static Block stoneStairs(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_stairs",
                new StairBlock(baseBlock.defaultBlockState(), BlockBehaviour.Properties.copy(baseBlock)));
    }
    public static Block stoneSlab(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_slab",
                new SlabBlock(BlockBehaviour.Properties.copy(baseBlock)));
    }
    public static Block stoneWall(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_wall",
                new WallBlock(BlockBehaviour.Properties.copy(baseBlock)));
    }
    public static Block stonePressurePlate(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_pressure_plate",
                new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.copy(baseBlock), BlockSetType.STONE));
    }
    public static Block stoneButton(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_button",
                new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.STONE, 20, false));
    }

    //If name ends with "Bricks" then shorten to "Brick" for use
    public static String getName(Block base){
        String name = GGRegistry.idOf(base).getPath();
        return name.endsWith("bricks") ? name.substring(0, name.length() - 1) : name;
    }
    public static String getName(String base){
        return base.endsWith("bricks") ? base.substring(0, base.length() - 1) : base;
    }
}
