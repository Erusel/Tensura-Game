package fr.erusel.tensura.objects;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;

public abstract class Scenario {

    private final String name;
    private final String lore;

    public Scenario(String name, String lore) {
        this.name = name;
        this.lore = lore;
    }

    public abstract void onStart();
    public abstract void onFinish();
    public abstract void onPlayerJoin(PlayerJoinEvent event);
    public abstract void onPlayerLeave(PlayerQuitEvent event);
    public abstract void onPlayerDeath(PlayerDeathEvent event);
    public abstract void onEntityDamageByEntity(EntityDamageByEntityEvent event);
    public abstract void onPlayerMove(PlayerMoveEvent event);
    public abstract void onBlockBreak(BlockBreakEvent event);
    public abstract void onChat(AsyncPlayerChatEvent event);
    public abstract void onAdvancement(PlayerAdvancementDoneEvent event);
    public abstract void onCraft(CraftItemEvent event);
    public abstract void onDamage(EntityDamageEvent event);


    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
    }
}
