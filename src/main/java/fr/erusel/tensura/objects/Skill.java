package fr.erusel.tensura.objects;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;

public abstract class Skill {

    GameManager gameManager;
    PlayerManager playerManager;

    private final String name;
    private final String lore;
    private final SkillTier skillTier;
    private final Skills skill;
    private final SkillScope skillScope;
    int COOLDOWN;
    private int currentCooldown = 0;
    private final Class<?> ultimateSkill;

    public Skill(String name, String lore,Skills skill, SkillScope skillScope, SkillTier skillTier, int cooldown, Class<?> ultimateSkill) {
        this.name = name;
        this.lore = lore;
        this.skill = skill;
        this.skillScope = skillScope;
        this.skillTier = skillTier;
        COOLDOWN = cooldown;
        this.ultimateSkill = ultimateSkill;
        this.gameManager = GameManager.getInstance();
        this.playerManager = PlayerManager.getInstance();
    }

    public String getName(){
        return name;
    }
    public String getLore(){
        return lore;
    }
    public SkillTier getSkillTier(){
        return skillTier;
    }
    public void setCurrentCooldown(int i){
        currentCooldown = i;
    }
    public int getCurrentCooldown(){
        return currentCooldown;
    }
    public void activateCooldown(){
        setCurrentCooldown(COOLDOWN);
    }
    public boolean inCooldown(){
        return (currentCooldown > 0);
    }
    public Class<?> getUltimateSkillClass(){
        return ultimateSkill;
    }
    public SkillScope getSkillScope(){
        return skillScope;
    }
    public Skills getSkill(){
        return skill;
    }
    public boolean isSkill(Skills skill){
        return this.skill == skill;
    }
    public GameManager getGameManager() {
        return gameManager;
    }
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
