package fr.erusel.tensura.objects;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.threads.HarvestFestivalRunnable;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GPlayer extends GameElement {

    Random random = new Random();


    // Player Info
    private final UUID playerUUID;
    private int playerKill;
    private Race race;
    private final ArrayList<Skill> playerSkills = new ArrayList<>();
    private BukkitTask leaveRunnable;
    private boolean leaved = false;


    // Skills
    private boolean glutonnyActivated;
    private boolean oppressorActivated;
    private boolean imperceptibleActivated;
    private boolean ReflectorActivated;
    private boolean gravityActivated;
    private boolean inHarvestFestival;
    private boolean canResurrect = true;
    private int mathematicianDodgeLeft;
    private UUID trackingPlayer;
    private Inventory stomachInventory;
    private Inventory pandoraInventory;
    private PotionEffectType fletcherEffect;
    private boolean fletcherBurst;
    private boolean frozenHit;
    private boolean vampirism;
    private boolean explosion;

    public GPlayer(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
    public UUID getUUID(){
        return playerUUID;
    }
    public void setLeaveRunnable(BukkitTask runnable){
        leaveRunnable = runnable;
    }
    public BukkitTask getLeaveRunnable(){
        return leaveRunnable;
    }
    public void setLeaved(boolean leaved){
        this.leaved = leaved;
    }
    public boolean haveLeaved(){
        return leaved;
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
    public Races getRaces(){
        return race.getRace();
    }
    public boolean isRace(Races races){
        if (!getGameSettingManager().isRaceActivated()){
            return false;
        }
        return getRaces().equals(races);
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
            player.sendMessage("§cYou don't have the prerequisite");
            return;
        }

        Location location = player.getLocation();
        player.playSound(location, "custom.sound", SoundCategory.MASTER, 1, 1);

        Skill skill;
        Skill sacrificeSkill = null;
        int i = random.nextInt(getPlayerSkills().size());
        skill = getPlayerSkills().get(i);

        while (!skill.getSkillTier().equals(SkillTier.UNIQUE)){
            i = random.nextInt(getPlayerSkills().size());
            skill = getPlayerSkills().get(i);
        }

        if (getPlayerSkills().size() > 1){
            int u = random.nextInt(getPlayerSkills().size());
            sacrificeSkill = getPlayerSkills().get(u);
            while (sacrificeSkill.getName().equals(skill.getName())){
                u = random.nextInt(getPlayerSkills().size());
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
    public void setFrozenHit(boolean b) {
        frozenHit = b;
    }
    public boolean isOppressorActivated(){
        return oppressorActivated;
    }
    public boolean isImperceptibleActivated() { return imperceptibleActivated;}
    public boolean isReflectorActivated() { return ReflectorActivated;}
    public boolean isFrozenHitActivated() {
        return frozenHit;
    }
    public void setMathematicianDodgeLeft(int i){
        mathematicianDodgeLeft = i;
    }
    public int getMathematicianDodgeLeft(){
        return mathematicianDodgeLeft;
    }
    public void setVampirism(boolean b) {
        vampirism = b;
    }
    public boolean isVampirism() {
        return vampirism;
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
    public boolean isFletcherBurst() {
        return fletcherBurst;
    }
    public void setFletcherBurst(boolean fletcherBurst) {
        this.fletcherBurst = fletcherBurst;
    }
    public void setExplosion(boolean b) {
        explosion = b;
    }
    public boolean isExplosion() {
        return explosion;
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
        player.setGameMode(GameMode.SURVIVAL);
        player.teleport(player.getWorld().getSpawnLocation());
        player.sendMessage("You been be resurrected");
    }

    public boolean isDead(){
        return getGameManager().getDeadPlayers().contains(playerUUID);
    }


}
