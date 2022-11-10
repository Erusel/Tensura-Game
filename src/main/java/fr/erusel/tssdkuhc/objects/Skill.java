package fr.erusel.tssdkuhc.objects;

import fr.erusel.tssdkuhc.enums.SkillTier;

public abstract class Skill {

    private final String name;
    private final String lore;
    private final SkillTier skillTier;
    int COOLDOWN;
    private int currentCooldown = 0;

    public Skill(String name, String lore, SkillTier skillTier, int cooldown) {
        this.name = name;
        this.lore = lore;
        this.skillTier = skillTier;
        COOLDOWN = cooldown;
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



}
