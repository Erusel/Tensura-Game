package fr.erusel.tensura.managers;

import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;

public class GameSettingsManager {

    private static GameSettingsManager instance;

    private int skillOnStart = 1;
    private int borderRadius = 1000;
    private boolean naturalRegen = false;
    private boolean monsterSpawn = true;
    private boolean raceActivated = true;
    private boolean skillDrop = true;
    private int amountLootCrate = 20;

    public GameSettingsManager() {
        instance = this;
    }

    // Game Settings
    public int getSkillOnStart(){
        return skillOnStart;
    }
    public void setSkillOnStart(int i) {
        this.skillOnStart = i;
    }
    public int getMaxPlayer(){
        return (int) Math.floor(Skills.getAllSkillByTier(SkillTier.UNIQUE).size() / getSkillOnStart());
    }
    public boolean getNaturalRegen(){
        return naturalRegen;
    }
    public void setNaturalRegen(boolean b){
        naturalRegen = b;
    }
    public boolean getMonsterSpawn(){
        return monsterSpawn;
    }
    public void setMonsterSpawn(boolean b){
        monsterSpawn = b;
    }
    public boolean isRaceActivated() {
        return raceActivated;
    }
    public void setRaceActivated(boolean raceActivated) {
        this.raceActivated = raceActivated;
    }
    public int getBorderRadius() {
        return borderRadius;
    }
    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }
    public boolean isSkillDrop(){
        return skillDrop;
    }
    public void setSkillDrop(boolean skillDrop) {
        this.skillDrop = skillDrop;
    }
    public int getAmountCrates() {
        return amountLootCrate;
    }
    public void setAmountCrates(int amountLootCrate) {
        this.amountLootCrate = amountLootCrate;
    }

    public static GameSettingsManager getInstance() {
        return instance;
    }


}
