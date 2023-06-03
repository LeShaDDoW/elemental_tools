package net.lstwo.elemental_tools.item.hoe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FireHoe extends HoeItem {
    public FireHoe(ToolMaterial material) {
        super(material, 13, new Settings().group(ItemGroup.TOOLS).maxCount(1).maxDamage(986).
                rarity(Rarity.RARE));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setOnFireFor(10);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient) {
            List<ItemStack> drops = Block.getDroppedStacks(state, (ServerWorld) world, pos, null, miner, stack);
            List<ItemStack> modifiedDrops = new ArrayList<>();

            for (ItemStack drop : drops) {
                ItemStack smeltedResult = getSmeltedResult(world, drop);
                if (!smeltedResult.isEmpty()) {
                    modifiedDrops.add(smeltedResult);
                    world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 10, 0.2, 0.2, 0.2, 0.1);
                    ((ServerWorld) world).spawnParticles(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 30, 0.1, 0.1, 0.1, 0.1);
                }
            }

            for (ItemStack modifiedDrop : modifiedDrops) {
                Block.dropStack(world, pos, modifiedDrop);
            }
        }

        return super.postMine(stack, world, state, pos, miner);
    }

    private ItemStack getSmeltedResult(World world, ItemStack itemStack) {
        Optional<SmeltingRecipe> recipe = world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, new BasicInventory(itemStack), world);
        if (recipe.isPresent()) {
            ItemStack result = recipe.get().getOutput().copy();
            if (result.getItem() instanceof BlockItem) {
                result.removeSubTag("BlockEntityTag");
            }
            return result;
        }
        return ItemStack.EMPTY;
    }
}