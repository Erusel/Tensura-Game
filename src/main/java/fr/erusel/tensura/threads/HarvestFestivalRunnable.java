package fr.erusel.tensura.threads;

import fr.erusel.tensura.enums.Prefixes;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class HarvestFestivalRunnable extends BukkitRunnable {

    private final Player player;
    private final GPlayer gPlayer;

    private final Skill skillEvolve;
    private final Skill sacrificeSkill;
    private final Skill ultimateSkill;
    private final Race evolvedRace;
    private final Skills resistance1;
    private Skills resistance2 ;
    private Skills resistance3;
    private final Skills extraSkill1;
    private Skills extraSkill2;
    private Skills extraSkill3;

    public HarvestFestivalRunnable(Player player, GPlayer gPlayer, Skill skillEvolve, Skill sacrificeSkill) {
        this.player = player;
        this.gPlayer = gPlayer;
        this.skillEvolve = skillEvolve;
        this.sacrificeSkill = sacrificeSkill;
        evolvedRace = gPlayer.getRaces().getEvolution().createInstance();
        ultimateSkill = skillEvolve.getUltimateSkill().createInstance();

        resistance1 = Skills.getRandomSkillByTier(SkillTier.RESISTANCE);
        resistance2 = Skills.getRandomSkillByTier(SkillTier.RESISTANCE);
        while (resistance2 == resistance1){
            resistance2 = Skills.getRandomSkillByTier(SkillTier.RESISTANCE);
        }
        resistance3 = Skills.getRandomSkillByTier(SkillTier.RESISTANCE);
        while (resistance3 == resistance2 && resistance3 == resistance1){
            resistance3 = Skills.getRandomSkillByTier(SkillTier.RESISTANCE);
        }

        extraSkill1 = Skills.getRandomSkillByTier(SkillTier.EXTRA);
        extraSkill2 = Skills.getRandomSkillByTier(SkillTier.EXTRA);
        while (extraSkill2 == extraSkill1){
            extraSkill2 = Skills.getRandomSkillByTier(SkillTier.EXTRA);
        }
        extraSkill3 = Skills.getRandomSkillByTier(SkillTier.EXTRA);
        while (extraSkill3 == extraSkill2 && resistance3 != extraSkill1){
            extraSkill3 = Skills.getRandomSkillByTier(SkillTier.EXTRA);
        }

    }


    int second = 0;

    @Override
    public void run() {

        if (second == 0) {
            Bukkit.broadcastMessage(Prefixes.VOICE_OF_THE_WORLD + "§d『NOTICE』 §3The Harvest Festival has begun.");
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 255));
        }
        if (second == 3) {
            Bukkit.broadcastMessage(Prefixes.VOICE_OF_THE_WORLD + "The indiviual's physical constitution will be rebuilt, and he will evolve into a new race.");
        }
        if (second == 8) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Confirmed");
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Super evolving from §6" + gPlayer.getRace().getName() + " §3to §6" + evolvedRace.getName());
        }
        if (second == 12) {
            gPlayer.setRace(evolvedRace);
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§2Successful.");
        }
        if (second == 17) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "All Physical abilities have significantly improved.");
        }
        if (second == 19) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Next");
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "all skills and resistances already acquired by the old form will be re-acquired.");
        }
        if (second == 25) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§2Successful");
        }
        if (second == 27) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "New Extra skills :");
        }
        if (second == 30) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§6" + extraSkill1.getSkillName());
        }
        if (second == 31) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§6" + extraSkill2.getSkillName());
        }
        if (second == 32) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§6" + extraSkill3.getSkillName());
        }
        if (second == 35) {
            gPlayer.addSkill(extraSkill1.createInstance());
            gPlayer.addSkill(extraSkill2.createInstance());
            gPlayer.addSkill(extraSkill3.createInstance());
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Acquisition §3successful.");
        }
        if (second == 40) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "New Resistances :");
        }
        if (second == 43) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§6" + resistance1.getSkillName());
        }
        if (second == 44) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§6" + resistance2.getSkillName());
        }
        if (second == 45) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§6" + resistance3.getSkillName());
        }
        if (second == 50) {
            gPlayer.addSkill(resistance1.createInstance());
            gPlayer.addSkill(resistance2.createInstance());
            gPlayer.addSkill(resistance3.createInstance());
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Acquisition §3successful.");
        }
        if (second == 56) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "This complete the evolution.");
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§d『NOTICE』");
        }
        if (second == 59) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Petition to evolve the unique skill §6" + skillEvolve.getName() + " §3to the voice of the world.");
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Requesting evolution of §6" + skillEvolve.getName());
        }
        if (second == 61) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Understood");
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Request for unique skill §6" + skillEvolve.getName() + " §3accepted");
        }
        if (second == 64) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Unique skill §6" + skillEvolve.getName() + " §3will attempt to evolve.");
        }
        if (second == 67) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§cFailed.");
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Repeating attempt.");
        }
        if (second == 69) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§cFailed.");
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Repeating attempt.");
        }
        if (second == 72) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§cFailed.");
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Repeating attempt.");
        }
        if (second == 74) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§cFailed.");
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Repeating attempt.");
        }
        if (second == 76) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§cFailed.");
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Repeating attempt.");
        }
        if (second == 78) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§cFailed.");
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Repeating attempt.");
        }
        if (second == 80) {
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§cFailed.");
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Repeating attempt.");
        }
        if (second == 84) {
            if (sacrificeSkill != null){
                player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Unique skill §6" + skillEvolve.getName() + " §3will sacrifice §6" + sacrificeSkill.getName() + " §3to receive the Harvest Festival gift and attempt evolution");
                gPlayer.removeSkill(sacrificeSkill);
            }else {
                player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "§2Success");
            }
            gPlayer.removeSkill(skillEvolve);
            gPlayer.addSkill(ultimateSkill);
        }
        if (second == 91){
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "Unique skill §6" + skillEvolve.getName() + " §3has evolved into Ultimate skill §6§n§l" + ultimateSkill.getName());
        }

        if (second == 94){
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD + "The Harvest Festival is now finished");
            gPlayer.setInHarvestFestival(false);
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            this.cancel();
        }
        second++;
    }
}
