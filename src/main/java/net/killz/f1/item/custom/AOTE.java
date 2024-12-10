package net.killz.f1.item.custom;

import net.killz.f1.blocks.ModBlocks;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class AOTE extends Item
{
    public static final Block boc = ModBlocks.ENDIUM_BLOCK.get();

    public AOTE(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext)
    {
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();
        Block blockcheck = level.getBlockState(pContext.getClickedPos()).getBlock();
        if(blockcheck == boc) {
            if (!level.isClientSide()) {
                Chicken chicken = new Chicken(EntityType.CHICKEN, level);
                chicken.moveTo(pContext.getClickedPos().above(), 1F, 1F);
                level.addFreshEntity(chicken);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
