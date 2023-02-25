package fr.erusel.tensura.modes;

import fr.erusel.tensura.enums.Modes;
import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.items.charybdis.CharybdisCoreItem;
import fr.erusel.tensura.objects.Mode;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.scoreboards.CharybdisScoreboard;
import fr.erusel.tensura.threads.PlayerLeaveRunnable;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Random;


public class CharybdisMode extends Mode {


    public CharybdisMode() {
        super("Charybdis Hunt", Modes.CHARYBDIS, new CharybdisScoreboard(), true);
    }

    private final Random random = new Random();

    @Override
    public void teleportPlayers() {
        int borderRadius = getGameSettingManager().getBorderRadius();
        int redTeamX = random.nextInt(borderRadius);
        int redTeamZ = random.nextInt(borderRadius);
        Location redTeamSpawn = getWorldManager().getMap().getHighestBlockAt(redTeamX, redTeamZ).getLocation();
        int blueTeamX = random.nextInt(borderRadius);
        int blueTeamZ = random.nextInt(borderRadius);
        Location blueTeamSpawn = getWorldManager().getMap().getHighestBlockAt(blueTeamX, blueTeamZ).getLocation();
        int greenTeamX = random.nextInt(borderRadius);
        int greenTeamZ = random.nextInt(borderRadius);
        Location greenTeamSpawn = getWorldManager().getMap().getHighestBlockAt(greenTeamX, greenTeamZ).getLocation();
        int yellowTeamX = random.nextInt(borderRadius);
        int yellowTeamZ = random.nextInt(borderRadius);
        Location yellowTeamSpawn = getWorldManager().getMap().getHighestBlockAt(yellowTeamX, yellowTeamZ).getLocation();

        // Teleport Players with teams
        for (Player player : Bukkit.getOnlinePlayers()){
            getPlayerManager().createPlayerGPlayer(player);
            if (!getGameManager().getPlayerList().contains(player.getUniqueId())){
                player.setGameMode(GameMode.SPECTATOR);
            }else {
                player.setGameMode(GameMode.SURVIVAL);
            }
            switch (getTeamManager().getPlayerTeam(player.getUniqueId())){
                case RED:
                    player.teleport(redTeamSpawn);
                case BLUE:
                    player.teleport(blueTeamSpawn);
                case GREEN:
                    player.teleport(greenTeamSpawn);
                case YELLOW:
                    player.teleport(yellowTeamSpawn);
                case NONE:
                    player.teleport(getWorldManager().getMap().getSpawnLocation());
            }
            onPlayerSpawn(player);
        }
    }
    @Override
    public void onPlayerSpawn(Player player) {

        // Give Skill
        for (int z = 0; z < getGameSettingManager().getSkillOnStart(); z++) {
            int i = random.nextInt(getGameManager().getUniqueSkillAvailable().size());
            getPlayerManager().getGPlayerByUUID(player.getUniqueId()).addSkill(getGameManager().getUniqueSkillAvailable().get(i));
            getGameManager().getUniqueSkillAvailable().remove(getGameManager().getUniqueSkillAvailable().get(i));
        }

        Race race = Races.getRandomRaceByStage(RaceStages.FIRSTSTAGE).createInstance();
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setRace(race);
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getRace().onGive(player);

        player.sendMessage("§8-------------------------------");
        player.sendMessage("§7You have been resurrected in : §6§l" + race.getName());
        player.sendMessage("§7Objective :§r Find all the parts of §3Charybdis§r and bring him back to life!");
        player.sendMessage("  ");
        player.sendMessage("§7Your skills are :");
        for (Skill skill : getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getPlayerSkills()){
            player.sendMessage("§7-§6 " + skill.getName());
        }
        player.sendMessage("§8-------------------------------");
    }

    @Override
    public void onPlayerLeave(PlayerQuitEvent event) {
        getPlayerManager()
                .getGPlayerByUUID(event.getPlayer().getUniqueId()).setLeaveRunnable(new PlayerLeaveRunnable(event.getPlayer(), getGameManager(), getPlayerManager().getGPlayerByUUID(event.getPlayer().getUniqueId()))
                .runTaskTimer(getMain(), 0, 20));
    }

    @Override
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (getPlayerManager().getGPlayerByUUID(event.getEntity().getUniqueId()).isCharibdised()){
            event.getDrops().add(new CharybdisCoreItem().getItemstack());
        }
        getPlayerManager().getGPlayerByUUID(event.getEntity().getUniqueId()).setDead(true);
    }

    @Override
    public void onPlayerRespawn(PlayerRespawnEvent event) {

    }

    @Override
    public void onStart() {

    }

}
