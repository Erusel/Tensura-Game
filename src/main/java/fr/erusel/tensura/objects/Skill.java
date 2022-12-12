package fr.erusel.tensura.objects;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;

public abstract class Skill extends GameElement {

    private final String name;
    private final String lore;
    private final SkillTier skillTier;
    private final Skills skill;
    private final SkillScope skillScope;
    int COOLDOWN;
    private int currentCooldown = 0;
    private final Skills ultimateSkill;

    public Skill(String name, String lore,Skills skill, SkillScope skillScope, SkillTier skillTier, int cooldown, Skills ultimateSkill) {
        this.name = name;
        this.lore = lore;
        this.skill = skill;
        this.skillScope = skillScope;
        this.skillTier = skillTier;
        COOLDOWN = cooldown;
        this.ultimateSkill = ultimateSkill;
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
    public Skills getUltimateSkill(){
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
}
