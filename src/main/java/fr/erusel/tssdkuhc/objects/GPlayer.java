package fr.erusel.tssdkuhc.objects;

import java.util.ArrayList;
import java.util.UUID;

public class GPlayer {

    private final UUID playerUUID;
    private int playerKill;
    private Race race;
    private boolean glutonnyActivated;
    private boolean oppressorActivated;
    private int oppressorTime;
    private int mathematicianDodgeLeft;
    private final ArrayList<Skill> playerSkills = new ArrayList<>();

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
    public void evolve(){

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
    public void setOppressorTime(int i){
        oppressorTime = i;
    }
    public int getOppressorTime(){
        return oppressorTime;
    }
    public boolean isOppressorActivated(){
        return oppressorActivated;
    }

    public void setMathematicianDodgeLeft(int i){
        mathematicianDodgeLeft = i;
    }
    public int getMathematicianDodgeLeft(){
        return mathematicianDodgeLeft;
    }

}
