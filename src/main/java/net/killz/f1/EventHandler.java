package net.killz.f1;

import net.killz.f1.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = hype.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {
    public static HashMap<UUID, Integer> mana = new HashMap<UUID, Integer>();
    public static int ticker = 0;
    public static int ticker2 = 0;
    public static UUID playeruid;
    public static boolean usedAOTE = false;

    @SubscribeEvent
    public static void onAOTEuse(PlayerInteractEvent.RightClickItem event)
    {
        Level level = event.getLevel();
        if(level.isClientSide)
        {
            Player player = event.getEntity();
            if (event.getItemStack().getItem() == ModItems.AspectOfTheEnd.get() && mana.get(player.getUUID()) >= 10) {
                double x = player.getLookAngle().x;
                double y = player.getLookAngle().y;
                double z = player.getLookAngle().z;
                double px = player.getX();
                double py = player.getY();
                double pz = player.getZ();
                double pxf = player.getX();
                double pyf = player.getY();
                double pzf = player.getZ();
                player.setPos(px + x * 10, py + y * 10, pz + z * 10);
                px = player.getX();
                py = player.getY();
                pz = player.getZ();
                for (int i = 0; i <= 6; i++) {
                    if (level.getBlockState(new BlockPos((int) px, (int) py, (int) pz)).getBlock() != Blocks.AIR) {
                        py++;
                        player.setPos(px, py, pz);
                        if (level.getBlockState(new BlockPos((int) px, (int) py, (int) pz)).getBlock() == Blocks.AIR) {
                            i = 101;
                        } else if (i == 6) {
                            PlayerChatMessage msg = PlayerChatMessage.unsigned(player.getUUID(), "§c There are blocks in the way !");
                            player.createCommandSourceStack().sendChatMessage(new OutgoingChatMessage.Player(msg), false, ChatType.bind(ChatType.CHAT, player));
                            player.setPos(pxf, pyf, pzf);
                        }
                    }
                }
                px = player.getX();
                py = player.getY();
                pz = player.getZ();
                player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 10.0F);
                for (int i = -15; i <= 15; i++) {
                    double rand = Math.random();
                    double rand1 = Math.random();
                    double rand2 = Math.random();
                    if(i < 0)
                    {
                        rand = rand * -1;
                    }
                    else if(i > 0)
                    {
                        rand1 = rand1 * -1;
                        rand2 = rand2 * -1;
                    }
                    level.addParticle(ParticleTypes.PORTAL, px-(rand)-1, py-(rand1), pz+(rand2)-1, 1, 1, 1);
                }
                int manareducer = mana.get(player.getUUID());
                mana.put(player.getUUID(), manareducer - 10);
                usedAOTE = true;
            }
            if (mana.get(player.getUUID()) < 10) {
                PlayerChatMessage msg = PlayerChatMessage.unsigned(player.getUUID(), "§c You don't have enough MANA !");
                player.createCommandSourceStack().sendChatMessage(new OutgoingChatMessage.Player(msg), false, ChatType.bind(ChatType.CHAT, player));
            }

        }
    }
    @SubscribeEvent
    public static void onJoin(PlayerEvent.PlayerLoggedInEvent event)
    {
        Player player = event.getEntity();
        mana.put(player.getUUID(), 100);
        playeruid = player.getUUID();
    }

    @SubscribeEvent
    public static void manaregenwalk(TickEvent.PlayerTickEvent event)
    {
        Player player = event.player;
        Level level = player.level();
        if(ticker == 200 && mana.get(player.getUUID()) <= 99)
        {
            int manaadder = mana.get(player.getUUID());
            int i;
            for(i = 1; i < 20; i ++)
            {
                if(100-manaadder == i)
                {
                    break;
                }
            }
            mana.put(player.getUUID(), manaadder+i);
            ticker = 0;
        }
        if(ticker <= 199 && mana.get(player.getUUID()) != 100)
        {
            ticker = ticker + 1;
        }
        if(ticker2 <= 199 && usedAOTE == true)
        {
            ticker2 = ticker2 + 1;
        }
        else if(ticker2 == 200)
        {
            ticker2 = 0;
            usedAOTE = false;
        }

    }

    @SubscribeEvent
    public static void aotefalldmgnegetion(LivingDamageEvent event)
    {
        if(event.getEntity().getMainHandItem().getItem() == ModItems.AspectOfTheEnd.get() && event.getEntity().fallDistance > 2)
        {
            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public static void render(CustomizeGuiOverlayEvent event)
    {
        GuiGraphics guigraphics = event.getGuiGraphics();
        guigraphics.drawString(Minecraft.getInstance().font ,"§dMana : §3" + mana.get(playeruid).toString() + "§8/100", 391, 470, 2554508);
        if(ticker2 > 1 && ticker2 < 201)
        {
            guigraphics.drawString(Minecraft.getInstance().font ,"§8AOTE Ability used ! §3-10 §dMANA", 10, 520, 2554508);
        }
    }
}