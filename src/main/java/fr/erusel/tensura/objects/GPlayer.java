package fr.erusel.tensura.objects;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.threads.HarvestFestivalRunnable;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GPlayer {

    private final GameManager gameManager;

    // Player Info
    private final UUID playerUUID;
    private int playerKill;
    private Race race;
    private final ArrayList<Skill> playerSkills = new ArrayList<>();


    // Skills
    private boolean glutonnyActivated;
    private boolean oppressorActivated;
    private boolean imperceptibleActivated;
    private boolean ReflectorActivated;
    private boolean gravityActivated;
    private boolean inHarvestFestival;
    private boolean canResurrect = true;
    private int oppressorTime;
    private int gravityTime;
    private int imperceptibleTime;
    private int ReflectorTime;
    private int mathematicianDodgeLeft;
    private UUID trackingPlayer;
    private Inventory stomachInventory;
    private Inventory pandoraInventory;
    private PotionEffectType fletcherEffect;

    public GPlayer(UUID playerUUID, GameManager gameManager) {
        this.playerUUID = playerUUID;
        this.gameManager = gameManager;
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


    /**
     * It checks if the player has a certain skill.
     *
     * @param skills The skill you want to check if the player has.
     * @return A boolean value.
     */
    public boolean haveSkill(Skills skills){
        return getPlayerSkills().stream().anyMatch(skill -> skill.getSkill() == skills);
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
    public boolean isRace(Races races){
        return race.getRace().equals(races);
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
    public void setGravity(boolean b) {
        gravityActivated = b;
    }
    public void setImperceptible(boolean b) { imperceptibleActivated = b;}
    public void setReflector(boolean b) { ReflectorActivated = b;}
    public boolean isOppressorActivated(){
        return oppressorActivated;
    }
    public boolean isImperceptibleActivated() { return imperceptibleActivated;}
    public boolean isReflectorActivated() { return ReflectorActivated;}
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
    public PotionEffectType getFletcherEffect() {
        return fletcherEffect;
    }
    public void setFletcherEffect(PotionEffectType fletcherEffect) {
        this.fletcherEffect = fletcherEffect;
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
    public List<Skill> getPlayerExtraSkill(){
        List<Skill> skills = new ArrayList<>();
        for (Skill skill : getPlayerSkills()){
            if (skill.getSkillTier().equals(SkillTier.EXTRA)) skills.add(skill);
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

    public void createStomachInventory(){
        if (stomachInventory != null) return;
        stomachInventory = Bukkit.createInventory(null, 27, "Stomach");
    }
    public Inventory getStomachInventory(){
        return stomachInventory;
    }

    public void createPandoraInventory(){
        if (pandoraInventory != null) return;

        pandoraInventory = Bukkit.createInventory(null, 45, "Pandora");
        if (stomachInventory != null) pandoraInventory.setContents(stomachInventory.getContents());
    }
    public Inventory getPandoraInventory(){
        return pandoraInventory;
    }

    public boolean canRessurect(){
        return canResurrect;
    }

    public void setCanResurrect(Boolean b){
        canResurrect = b;
    }

    public void ressurect(){
        Player player = Bukkit.getPlayer(playerUUID);
        gameManager.removeDeadPlayers(playerUUID);
        gameManager.addAlivePlayer(playerUUID);
        player.setGameMode(GameMode.SURVIVAL);
        player.teleport(player.getWorld().getSpawnLocation());
        player.sendMessage("You been be resurrected");
    }

}
