package net.lstwo.elemental_tools.item.pickaxe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EarthPickaxe extends PickaxeItem {
    private static final float INSTANT_BREAK_SPEED = 30.0F;
    private static final float DEFAULT_BREAK_SPEED = 1.0F;

    public EarthPickaxe(ToolMaterial material) {
        super(material, 2, -1.8F, new Settings().group(ItemGroup.TOOLS).maxCount(1).maxDamage(2048)
                .rarity(Rarity.COMMON));
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        Block block = state.getBlock();
        if (block == Blocks.STONE || block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.GRANITE || block == Blocks.DIORITE
            || block == Blocks.ANDESITE || block == Blocks.WARPED_PLANKS || block == Blocks.CRIMSON_PLANKS) {
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