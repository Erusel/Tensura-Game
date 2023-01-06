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
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BattleRoyalMode extends Mode {

    private final Random random = new Random();

    public BattleRoyalMode() {
        super("Battle Royal", Modes.BATTLE_ROYAL, new BattleRoyalScoreboard(), false);
    }

    @Override
    public void teleportPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()){
            //spawn player at random location in the map
            int x = random.nextInt(getGameSettingManager().getBorderRadius())-500;
            int z = random.nextInt(getGameSettingManager().getBorderRadius())-500;
            player.teleport(getWorldManager().getMap().getHighestBlockAt(x, z).getLocation());
            onPlayerSpawn(player);
        }
    }

    @Override
    public void onPlayerSpawn(Player player) {

        // Give Skills
        for (int z = 0; z < getGameSettingManager().getSkillOnStart(); z++) {
            int i = random.nextInt(getGameManager().getUniqueSkillAvailable().size());
            getPlayerManager().getGPlayerByUUID(player.getUniqueId()).addSkill(getGameManager().getUniqueSkillAvailable().get(i));
            getGameManager().getUniqueSkillAvailable().remove(getGameManager().getUniqueSkillAvailable().get(i));
        }

        // Give Races
        if (getGameSettingManager().isRaceActivated()){
            Race race = Races.getRandomRaceByStage(RaceStages.FIRSTSTAGE).createInstance();
            getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setRace(race);
            getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getRace().onGive(player);
            player.sendMessage("§aYou have been resurrected as a " + race.getName());
        }
    }

    @Override
    public void onStart() {
        Bukkit.broadcastMessage("§5Battle Royal");
        // Change the world border size to 20000 blocks max
        getWorldManager().getMap().getWorldBorder().setSize(20000);
        for (int i = 0; i < getGameSettingManager().getAmountCrates(); i++) {
            int x = random.nextInt(getGameSettingManager().getBorderRadius())-500;
            int z = random.nextInt(getGameSettingManager().getBorderRadius())-500;
            Location loc = getWorldManager().getMap().getHighestBlockAt(x, z).getLocation();
            getWorldManager().getMap().getBlockAt(loc).setType(Material.CHEST);
            Chest chest = (Chest) loc.getBlock().getState();
            getGameManager().addCrateLocation(chest.getLocation());
        }
    }

    @Override
    public void onFinish() {
        Utils.VoiceOfTheWorldBroadcast("GAME ENDED, 1 ALIVE PLAYER");
    }

    @Override
    public void onPlayerDeath(PlayerDeathEvent event) {
        if ((getGameManager().getPlayerList().size() - getGameManager().getDeadPlayers().size()) == 1){
            onFinish();
        }
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.CHEST)){
            Block block = event.getBlock();
            Chest chest = (Chest) block.getState();

            if (getGameManager().getCrateLocations().contains(block.getLocation())) {
                return;
            }

            getGameManager().removeCrateLocation(block.getLocation());
            int randomLvl = random.nextInt(5);
            int randomAmountStuff = random.nextInt(15);

            ItemStack[] rareItems = {
                    new ItemBuilder(Material.TRIDENT).name("§ePoseidon's Fork").enchant(Enchantment.IMPALING, randomLvl).build(),
                    new ItemBuilder(Material.IRON_SWORD).enchant(Enchantment.DAMAGE_ALL, randomLvl).build(),
                    new ItemBuilder(Material.STICK).enchant(Enchantment.KNOCKBACK, randomLvl).build(),
                    new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE).build(),
                    new ItemBuilder(Material.CREEPER_SPAWN_EGG).build(),
            };

            for (int j = 0; j < 15; j++) {
                int randomSlot = random.nextInt(27);
                int chance = random.nextInt(100);
                if (chest.getInventory().getItem(randomSlot) != null){
                    continue;
                }

                if (chance <= 2) {
                    int randomRareItem = random.nextInt(rareItems.length);
                    chest.getInventory().setItem(randomSlot, rareItems[randomRareItem]);
                    continue;
                }
                int randomItem = new Random().nextInt(Utils.items.length);
                ItemStack itemStack = Utils.items[randomItem];
                itemStack.setAmount(randomAmountStuff);
                chest.getInventory().setItem(randomSlot, itemStack);

            }

        }
    }
}
