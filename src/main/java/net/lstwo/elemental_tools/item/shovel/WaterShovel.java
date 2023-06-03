package net.lstwo.elemental_tools.item.shovel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class WaterShovel extends ShovelItem {
    public WaterShovel(ToolMaterial material) {
        super(material, 5, -2.8F, new Settings().group(ItemGroup.TOOLS).maxCount(1).maxDamage(768)
                .rarity(Rarity.UNCOMMON));
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {

        if(miner.isTouchingWater()) {
            world.playSound(null, pos, SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 1.0f, 1.0f);
            ((ServerWorld) world).spawnParticles(ParticleTypes.FALLING_WATER, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 10, 0.2, 0.2, 0.2, 0.1);
            ((ServerWorld) world).spawnParticles(ParticleTypes.DRIPPING_WATER, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 30, 0.1, 0.1, 0.1, 0.1);

            List<ItemStack> drops = Block.getDroppedStacks(state, (ServerWorld) world, pos, null, miner, stack);
            for(ItemStack drop : drops) {
                Block.dropStack(world, pos, drop);
            }
        }

        return super.postMine(stack, world, state, pos, miner);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (selected && entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            boolean isUnderwater = player.isInWater();
            if (isUnderwater) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1, 0, false, false));
            }
        }
    }
}
