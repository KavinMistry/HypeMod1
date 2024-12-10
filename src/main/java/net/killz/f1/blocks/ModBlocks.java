package net.killz.f1.blocks;

import net.killz.f1.hype;
import net.killz.f1.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, hype.MOD_ID);

    public static final RegistryObject<Block> ENDIUM_BLOCK = registerBlock("endium_block", () -> new Block(BlockBehaviour.Properties.of()
            .strength(6f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> ENDIUM_ORE = registerBlock("endium_ore", () -> new DropExperienceBlock(UniformInt.of(3, 9), BlockBehaviour.Properties.of()
            .strength(5f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.AMETHYST_CLUSTER)));

    private static <T extends  Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> Treturn = BLOCKS.register(name, block);
        registerBlockItem(name, Treturn);
        return Treturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
