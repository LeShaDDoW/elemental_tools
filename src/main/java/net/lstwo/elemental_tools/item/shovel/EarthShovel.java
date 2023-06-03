package net.lstwo.elemental_tools.item.shovel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EarthShovel extends ShovelItem {
    private static final float INSTANT_BREAK_SPEED = 50;
    private static final float DEFAULT_BREAK_SPEED = 7;
    int i = 0;

    public EarthShovel(ToolMaterial material) {
        super(material, 10, -2.2F, new Settings().group(ItemGroup.TOOLS).maxCount(1).maxDamage(1928)
                .rarity(Rarity.COMMON));
    }

    @Override
    public float getMiningSpeed(ItemStack stack, BlockState state) {
        Block block = state.getBlock();
        if (block == Blocks.OAK_PLANKS || block == Blocks.SPRUCE_PLANKS || block == Blocks.DARK_OAK_PLANKS || block == Blocks.BIRCH_PLANKS ||
                block == Blocks.ACACIA_PLANKS || block == Blocks.JUNGLE_PLANKS
                || block == Blocks.DIRT || block == Blocks.GRASS_BLOCK) {
            return INSTANT_BREAK_SPEED;
        }
        return DEFAULT_BREAK_SPEED;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        ((ServerWorld) world).spawnParticles(ParticleTypes.CRIT, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 10, 0.2, 0.2, 0.2, 0.1);
        ((ServerWorld) world).spawnParticles(ParticleTypes.FALLING_LAVA, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 30, 0.1, 0.1, 0.1, 0.1);
        return super.postMine(stack, world, state, pos, miner);
    }
}