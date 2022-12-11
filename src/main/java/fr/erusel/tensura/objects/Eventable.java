package fr.erusel.tensura.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

public interface Eventable {

    default void teleportPlayers(){

    };
    default void onPlayerSpawn(Player player){

    }

    default void onStart() {

    }

    default void onFinish() {

    }

    default void onPlayerJoin(PlayerJoinEvent event) {

    }

    default void onPlayerLeave(PlayerQuitEvent event) {

    }

    default void onPlayerDeath(PlayerDeathEvent event) {

    }

    default void onPlayerKill(Player killer, Player deadPlayer) {

    }

    default void onPlayerRespawn(PlayerRespawnEvent event) {

    }

    default void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

    }

    default void onEntityDamage(EntityDamageEvent event) {

    }

    default void onPlayerMove(PlayerMoveEvent event) {

    }

    default void onBlockBreak(BlockBreakEvent event) {

    }

    default void onChat(AsyncPlayerChatEvent event) {

    }

    default void onAdvancement(PlayerAdvancementDoneEvent event) {

    }

}
