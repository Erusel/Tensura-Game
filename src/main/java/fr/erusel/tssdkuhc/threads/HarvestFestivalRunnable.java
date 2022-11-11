package fr.erusel.tssdkuhc.threads;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.objects.GPlayer;
import fr.erusel.tssdkuhc.objects.Race;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;

public class HarvestFestivalRunnable extends BukkitRunnable {

    Player player;
    GPlayer gPlayer;
    Skill skillEvolve;
    Skill sacrificeSkill;
    Race evolvedRace;

    public HarvestFestivalRunnable(Player player, GPlayer gPlayer, Skill skillEvolve, Skill sacrificeSkill) {
        this.player = player;
        this.gPlayer = gPlayer;
        this.skillEvolve = skillEvolve;
        this.sacrificeSkill = sacrificeSkill;
        try {
            this.evolvedRace = (Race) gPlayer.getRace().getDemonLordStage().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    int second = 0;

    @Override
    public void run() {

        if (second == 0) {
            Bukkit.broadcastMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§d『NOTICE』 §3The Harvest Festival has begun.");
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 9999, 255));
        }
        if (second == 3) {
            Bukkit.broadcastMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "The indiviual's physical constitution will be rebuilt, and he will evolve into a new race.");
        }
        if (second == 6) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Confirmed");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Super evolving from §6" + gPlayer.getRace().getName() + " §3to §6" + evolvedRace.getName());
        }
        if (second == 8) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§2Successful.");
        }
        if (second == 10) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "All Physical abilities have significantly improved.");
        }
        if (second == 12) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Next");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "all skills and resistances already acquired by the old form will be re-acquired.");
        }
        if (second == 15) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§2Successful");
        }
        if (second == 17) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "New Intrisic skills :");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§cCome later");
        }
        if (second == 19) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "New Resistances :");
        }
        if (second == 21) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§6Resistance 1");
        }
        if (second == 22) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§6Resistance 2");
        }
        if (second == 24) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§6Resistance 3");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Acquisition §3successful.");
        }
        if (second == 27) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "This complete the evolution.");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§d『NOTICE』");
        }
        if (second == 33) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Petition to evolve the unique skill §6" + skillEvolve.getName() + " §3to the voice of the world.");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Requesting evolution of §6" + skillEvolve.getName());
        }
        if (second == 35) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Understood");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Request for unique skill §6" + skillEvolve.getName() + " §3accepted");
        }
        if (second == 38) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Unique skill §6" + skillEvolve.getName() + " §3will attempt to evolve.");
        }
        if (second == 40) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§cFailed.");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Repeating attempt.");
        }
        if (second == 42) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§cFailed.");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Repeating attempt.");
        }
        if (second == 44) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§cFailed.");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Repeating attempt.");
        }
        if (second == 46) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§cFailed.");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Repeating attempt.");
        }
        if (second == 48) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§cFailed.");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Repeating attempt.");
        }
        if (second == 50) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§cFailed.");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Repeating attempt.");
        }
        if (second == 52) {
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§cFailed.");
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Repeating attempt.");
        }
        if (second == 54) {
            if (sacrificeSkill != null){
                player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Unique skill §6" + skillEvolve.getName() + " §3will sacrifice §6" + sacrificeSkill.getName() + " §3to receive the Harvest Festival gift and attempt evolution");
            }else {
                player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "§2Success");
            }
        }
        if (second == 57){
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Unique skill §6" + skillEvolve.getName() + " §3has evolved into Ultimate skill §6§n§ltkt sa vien apres");
        }

        if (second == 60){
            player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "The Harvest Festival is now finished");
            gPlayer.setInHarvestFestival(false);
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            this.cancel();
        }
        second++;
    }
}
