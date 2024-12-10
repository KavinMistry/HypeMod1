package net.killz.f1.item;

import net.killz.f1.hype;
import net.killz.f1.item.custom.AOTE;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, hype.MOD_ID);

    public static final RegistryObject<Item> AspectOfTheEnd = ITEMS.register("aspectoftheend", () -> new SwordItem(Tiers.NETHERITE, new Item.Properties().durability(200).attributes(SwordItem.createAttributes(Tiers.DIAMOND, 26, -2.4F))));//technically damage should not be edited here continue watching the goddam youtube series to know why

    public static final RegistryObject<Item> Hyperion = ITEMS.register("hyperion", () -> new Item(new Item.Properties()));//this is for default items and not swords or else they won't do da damage!!

    public static final RegistryObject<Item> Refined_Endium = ITEMS.register(("refined_endium"), () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Endium = ITEMS.register(("endium"), () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Summoner = ITEMS.register(("summoner"), () -> new AOTE(new Item.Properties().durability(1)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }

}
