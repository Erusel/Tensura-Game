package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

public class AutoSmeltScenario extends Scenario {

    public AutoSmeltScenario() {
        super("Auto Smelt", "Auto smelt every mined ore.");
    }

    @Override
    public void onStart() {
        Bukkit.broadcastMessage("§6AUTO SMELT SCENARIO ACTIVATED");
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    @Override
    public void onPlayerLeave(PlayerQuitEvent event) {

    }

    @Override
    public void onPlayerDeath(PlayerDeathEvent event) {

    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event) {

    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {

    }

    @Override
    public void onChat(AsyncPlayerChatEvent event) {

    }

    @Override
    public void onAdvancement(PlayerAdvancementDoneEvent event) {

    }
}
