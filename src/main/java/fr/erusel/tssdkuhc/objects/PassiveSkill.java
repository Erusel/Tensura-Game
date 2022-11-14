package fr.erusel.tssdkuhc.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public interface PassiveSkill {

    void eachSecond(Player player);
    void onDeath(PlayerDeathEvent event);
    void onKill(Player killer, Player deadPlayer);
    void onDamage(EntityDamageEvent event);
    void onDamageByEntity(EntityDamageByEntityEvent event);
    void onBlockBreak(BlockBreakEvent event);

}
