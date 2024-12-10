package net.killz.f1.item;

import net.killz.f1.blocks.ModBlocks;
import net.killz.f1.hype;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, hype.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ENDIUM_ITEMS_TAB = CREATIVE_MODE_TABS.register("endium_items_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.AspectOfTheEnd.get()))
            .title(Component.translatable("creativetab.hypemod.endium_items"))
            .displayItems((itemDisplayParameters, output) -> {

                output.accept(ModItems.AspectOfTheEnd.get());
                output.accept(ModItems.Endium.get());
                output.accept(ModItems.Refined_Endium.get());
                output.accept(ModItems.Summoner.get());

            }).build());

    public static final RegistryObject<CreativeModeTab> ENDIUM_BLOCKS_TAB = CREATIVE_MODE_TABS.register("endium_blocks_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(ENDIUM_ITEMS_TAB.getId())
            .icon(() -> new ItemStack(ModBlocks.ENDIUM_BLOCK.get()))
            .title(Component.translatable("creativetab.hypemod.endium_block"))
            .displayItems((itemDisplayParameters, output) -> {

                output.accept(ModBlocks.ENDIUM_BLOCK.get());
                output.accept(ModBlocks.ENDIUM_ORE.get());
            }).build());


    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
