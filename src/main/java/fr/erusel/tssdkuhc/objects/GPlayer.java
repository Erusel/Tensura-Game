package fr.erusel.tssdkuhc.objects;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.enums.Skills;
import fr.erusel.tssdkuhc.threads.HarvestFestivalRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GPlayer {

    // Main information
    private final UUID playerUUID;
    private int playerKill;
    private Race race;
    private final ArrayList<Skill> playerSkills = new ArrayList<>();


    // Skill information
    private boolean glutonnyActivated;
    private boolean oppressorActivated;
    private boolean imperceptibleActivated;
    private boolean inHarvestFestival;
    private int oppressorTime;
    private int imperceptibleTime;
    private int mathematicianDodgeLeft;
    private UUID trackingPlayer;

    public GPlayer(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public UUID getUUID(){
        return playerUUID;
    }


    // Player Skills
    public ArrayList<Skill> getPlayerSkills(){
        return playerSkills;
    }
    public void addSkill(Skill skill){
        playerSkills.add(skill);
    }
    public void removeSkill(Skill skill){
        playerSkills.remove(skill);
    }
    public boolean haveSkill(Skills skill){
        for (Skill skills: getPlayerSkills()) {
            if (skills.getName().equals(skill.getSkillName())) return true;
        }
        return false;
    }

    // Player Kills
    public int getKills(){
        return playerKill;
    }
    public void addKill(int i){
        playerKill += i;
    }
    public void removeKill(int i){
        playerKill -= i;
    }
    public void resetKill(){
        playerKill = 0;
    }

    // Player Race
    public void setRace(Race race){
        this.race = race;
    }
    public Race getRace(){
        return race;
    }

    public boolean haveHarvestFestivalPrerequisite(){
        return true;
    }
    public boolean isInHarvestFestival(){
        return inHarvestFestival;
    }
    public void setInHarvestFestival(boolean b){
        inHarvestFestival = b;
    }

    public void launchHarvestFestival(){
        Player player = Bukkit.getPlayer(playerUUID);

        if (!haveHarvestFestivalPrerequisite()){
            player.sendMessage("§cVous n'avez pas les prérequis");
            return;
        }

        Location location = player.getLocation();
        player.playSound(location, "custom.sound", SoundCategory.MASTER, 1, 1);

        Skill skill;
        Skill sacrificeSkill = null;
        int i = new Random().nextInt(getPlayerSkills().size());
        skill = getPlayerSkills().get(i);

        while (!skill.getSkillTier().equals(SkillTier.UNIQUE)){
            i = new Random().nextInt(getPlayerSkills().size());
            skill = getPlayerSkills().get(i);
        }

        if (getPlayerSkills().size() > 1){
            int u = new Random().nextInt(getPlayerSkills().size());
            sacrificeSkill = getPlayerSkills().get(u);
            while (sacrificeSkill.getName().equals(skill.getName())){
                u = new Random().nextInt(getPlayerSkills().size());
                sacrificeSkill = getPlayerSkills().get(u);
            }
        }



        new HarvestFestivalRunnable(player, this, skill, sacrificeSkill).runTaskTimer(Main.getInstance(), 0, 20);
        setInHarvestFestival(true);





    }

    // Skills
    public void setGlutonny(boolean b){
        glutonnyActivated = b;
    }
    public boolean isGlutonnyActivated(){
        return glutonnyActivated;
    }

    public void setOppressor(boolean b){
        oppressorActivated = b;
    }
    public void setImperceptible(boolean b) { imperceptibleActivated = b;}
    public void setOppressorTime(int i){
        oppressorTime = i;
    }
    public void setImperceptibleTime(int i){
        imperceptibleTime = i;
    }
    public int getOppressorTime(){
        return oppressorTime;
    }
    public int getImperceptibleTime() { return imperceptibleTime;}
    public boolean isOppressorActivated(){
        return oppressorActivated;
    }
    public boolean isImperceptibleActivated() { return imperceptibleActivated;}
    public void setMathematicianDodgeLeft(int i){
        mathematicianDodgeLeft = i;
    }
    public int getMathematicianDodgeLeft(){
        return mathematicianDodgeLeft;
    }

    public void setTrackingPlayer(UUID uuid){
        trackingPlayer = uuid;
    }
    public UUID getTrackingPlayer(){
        return trackingPlayer;
    }

    public List<Skill> getPlayerUniqueSkills(){
        List<Skill> skills = new ArrayList<>();
        for (Skill skill : getPlayerSkills()){
            if (skill.getSkillTier().equals(SkillTier.UNIQUE)) skills.add(skill);
        }
        return skills;
    }
    public List<Skill> getPlayerResistance(){
        List<Skill> skills = new ArrayList<>();
        for (Skill skill : getPlayerSkills()){
            if (skill.getSkillTier().equals(SkillTier.RESISTANCE)) skills.add(skill);
        }
        return skills;
    }
    public List<Skill> getPlayerUltimateSkills(){
        List<Skill> skills = new ArrayList<>();
        for (Skill skill : getPlayerSkills()){
            if (skill.getSkillTier().equals(SkillTier.ULTIMATE)) skills.add(skill);
        }
        return skills;
    }

}
