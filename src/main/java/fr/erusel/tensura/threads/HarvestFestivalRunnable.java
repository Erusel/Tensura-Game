package fr.erusel.tensura.threads;

import fr.erusel.tensura.enums.Prefixs;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.objects.Skill;
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
    Skill ultimateSkill;
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
        try {
            ultimateSkill = (Skill) skillEvolve.getUltimateSkillClass().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    int second = 0;

    @Override
    public void run() {

        if (second == 0) {
            Bukkit.broadcastMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§d『NOTICE』 §3The Harvest Festival has begun.");
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 9999, 255));
        }
        if (second == 3) {
            Bukkit.broadcastMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "The indiviual's physical constitution will be rebuilt, and he will evolve into a new race.");
        }
        if (second == 26) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Confirmed");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Super evolving from §6" + gPlayer.getRace().getName() + " §3to §6" + evolvedRace.getName());
        }
        if (second == 29) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§2Successful.");
        }
        if (second == 32) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "All Physical abilities have significantly improved.");
        }
        if (second == 35) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Next");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "all skills and resistances already acquired by the old form will be re-acquired.");
        }
        if (second == 38) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§2Successful");
        }
        if (second == 42) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "New Intrisic skills :");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§cCome later");
        }
        if (second == 45) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "New Resistances :");
        }
        if (second == 48) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§6Resistance 1");
        }
        if (second == 50) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§6Resistance 2");
        }
        if (second == 53) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§6Resistance 3");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Acquisition §3successful.");
        }
        if (second == 56) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "This complete the evolution.");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§d『NOTICE』");
        }
        if (second == 59) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Petition to evolve the unique skill §6" + skillEvolve.getName() + " §3to the voice of the world.");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Requesting evolution of §6" + skillEvolve.getName());
        }
        if (second == 61) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Understood");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Request for unique skill §6" + skillEvolve.getName() + " §3accepted");
        }
        if (second == 64) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Unique skill §6" + skillEvolve.getName() + " §3will attempt to evolve.");
        }
        if (second == 67) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§cFailed.");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Repeating attempt.");
        }
        if (second == 70) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§cFailed.");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Repeating attempt.");
        }
        if (second == 73) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§cFailed.");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Repeating attempt.");
        }
        if (second == 76) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§cFailed.");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Repeating attempt.");
        }
        if (second == 79) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§cFailed.");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Repeating attempt.");
        }
        if (second == 82) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§cFailed.");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Repeating attempt.");
        }
        if (second == 85) {
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§cFailed.");
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Repeating attempt.");
        }
        if (second == 88) {
            if (sacrificeSkill != null){
                player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Unique skill §6" + skillEvolve.getName() + " §3will sacrifice §6" + sacrificeSkill.getName() + " §3to receive the Harvest Festival gift and attempt evolution");
            }else {
                player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "§2Success");
            }
        }
        if (second == 91){
            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Unique skill §6" + skillEvolve.getName() + " §3has evolved into Ultimate skill §6§n§l" + ultimateSkill.getName());
        }

        if (second == 94){
            gPlayer.setRace(evolvedRace);
            gPlayer.removeSkill(sacrificeSkill);
            gPlayer.removeSkill(skillEvolve);
            gPlayer.addSkill(ultimateSkill);


            player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "The Harvest Festival is now finished");
            gPlayer.setInHarvestFestival(false);
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            this.cancel();
        }
        second++;
    }
}
