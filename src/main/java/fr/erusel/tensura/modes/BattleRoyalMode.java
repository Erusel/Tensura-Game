package fr.erusel.tensura.modes;

import fr.erusel.tensura.enums.Modes;
import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Mode;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.scoreboards.BattleRoyalScoreboard;
import fr.erusel.tensura.utils.Utils;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BattleRoyalMode extends Mode {
    public BattleRoyalMode() {
        super("Battle Royal", Modes.BATTLE_ROYAL, new BattleRoyalScoreboard(), false);
    }

    @Override
    public void teleportPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()){
            //spawn player at random location in the map
            int x = new Random().nextInt(getGameManager().getBorderRadius())-500;
            int z = new Random().nextInt(getGameManager().getBorderRadius())-500;
            player.teleport(getWorldManager().getMap().getHighestBlockAt(x, z).getLocation());
            onPlayerSpawn(player);
        }
    }

    @Override
    public void onPlayerSpawn(Player player) {

        // Give Skills
        for (int z = 0; z < getGameManager().getSkillOnStart(); z++) {
            int i = new Random().nextInt(getGameManager().getUniqueSkillAvailable().size());
            getPlayerManager().getGPlayerByUUID(player.getUniqueId()).addSkill(getGameManager().getUniqueSkillAvailable().get(i));
            getGameManager().getUniqueSkillAvailable().remove(getGameManager().getUniqueSkillAvailable().get(i));
        }

        // Give Races
        if (getGameManager().isRaceActivated()){
            Race race = Races.getRandomRaceByStage(RaceStages.FIRSTSTAGE).createInstance();
            getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setRace(race);
            getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getRace().onGive(player);
            player.sendMessage("§aYou have been resurrected as a " + race.getName());
        }
    }

    @Override
    public void onStart() {
        Bukkit.broadcastMessage("§5Battle Royal");
        // Change the world border size to 10000 blocks max
        getWorldManager().getMap().getWorldBorder().setSize(20000);
        // place chest in random position in the map with random stuff

        for (int i = 0; i < 100; i++) {

            int randomLvl = new Random().nextInt(5);
            int randomAmountStuff = new Random().nextInt(15);

            ItemStack[] rareItems = {
                    new ItemBuilder(Material.TRIDENT).name("§ePoseidon's Fork").enchant(Enchantment.IMPALING, randomLvl).build(),
                    new ItemBuilder(Material.IRON_SWORD).enchant(Enchantment.DAMAGE_ALL, randomLvl).build(),
                    new ItemBuilder(Material.STICK).enchant(Enchantment.KNOCKBACK, randomLvl).build(),
                    new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE).build(),
                    new ItemBuilder(Material.CREEPER_SPAWN_EGG).build(),
            };

            ItemStack[] items = {
                    new ItemBuilder(Material.DIAMOND).build(),
                    new ItemBuilder(Material.IRON_INGOT).build(),
                    new ItemBuilder(Material.GOLD_INGOT).build(),
                    new ItemBuilder(Material.GOLDEN_APPLE).build(),
                    new ItemBuilder(Material.ENDER_PEARL).build(),
                    new ItemBuilder(Material.BOW).build(),
                    new ItemBuilder(Material.ARROW).amount(randomAmountStuff).build(),
                    new ItemBuilder(Material.IRON_HELMET).build(),
                    new ItemBuilder(Material.IRON_CHESTPLATE).build(),
                    new ItemBuilder(Material.IRON_LEGGINGS).build(),
                    new ItemBuilder(Material.IRON_BOOTS).build(),
                    new ItemBuilder(Material.FURNACE_MINECART).name("§elol useless").build(),
                    new ItemBuilder(Material.WATER_BUCKET).build(),
                    new ItemBuilder(Material.LAVA_BUCKET).build(),
                    new ItemBuilder(Material.CAKE).name("§eThe lie").build(),
            };

            int x = new Random().nextInt(getGameManager().getBorderRadius())-500;
            int z = new Random().nextInt(getGameManager().getBorderRadius())-500;
            Location loc = getWorldManager().getMap().getHighestBlockAt(x, z).getLocation();
            getWorldManager().getMap().getBlockAt(loc).setType(Material.CHEST);
            Chest chest = (Chest) loc.getBlock().getState();
            chest.setCustomName("§6Loot Crate");
            System.out.println(loc);
            for (int j = 0; j < 15; j++) {
                int randomSlot = new Random().nextInt(27);
                if (chest.getInventory().getItem(randomSlot) == null){
                    int rng = new Random().nextInt(100);
                    if (rng <= 2){
                        int randomRareItem = new Random().nextInt(rareItems.length);
                        chest.getInventory().setItem(randomSlot, rareItems[randomRareItem]);
                    } else {
                        int randomItem = new Random().nextInt(items.length);
                        chest.getInventory().setItem(randomSlot, items[randomItem]);
                    }
                }
            }
        }
    }

    @Override
    public void onFinish() {
        Utils.VoiceOfTheWorldBroadcast("GAME ENDED, 1 ALIVE PLAYER");
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    @Override
    public void onPlayerLeave(PlayerQuitEvent event) {

    }

    @Override
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (getGameManager().getAlivePlayers().size() == 1){
            onFinish();
        }
    }

    @Override
    public void onPlayerRespawn(PlayerRespawnEvent event) {
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
