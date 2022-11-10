package fr.erusel.tssdkuhc.enums;

import fr.erusel.tssdkuhc.skills.active.unique.*;
import fr.erusel.tssdkuhc.skills.passive.unique.BerserkerSkill;
import fr.erusel.tssdkuhc.skills.passive.unique.SpeedySkill;

import java.util.ArrayList;
import java.util.List;

public enum Skills {

    // Unique Skills
    GREATSAGE("GreatSage", SkillTier.UNIQUE, GreatSageSkill.class),
    SPEEDY("Speedy", SkillTier.UNIQUE, SpeedySkill.class),
    GLUTONNY("Glutonny", SkillTier.UNIQUE, GlutonnySkill.class),
    BERSERKER("Berserker", SkillTier.UNIQUE, BerserkerSkill.class),
    OPPRESSOR("Oppressor", SkillTier.UNIQUE, OppressorSkill.class),
    MERCILESS("Merciless", SkillTier.UNIQUE, MercilessSkill.class),
    MATHEMATICIAN("Mathematician", SkillTier.UNIQUE, MathematicianSkill.class),
    STOMACH("Stomach", SkillTier.UNIQUE, StomachSkill.class),
    INVESTIGATOR("Investigator",SkillTier.UNIQUE, InvestigatorSkill.class);


    private final String name;
    private final SkillTier skillTier;
    private final Class<?> skillClass;

    Skills(String skillName, SkillTier skillTier, Class<?> skillClass) {
        this.name = skillName;
        this.skillTier = skillTier;
        this.skillClass = skillClass;
    }

    public String getSkillName(){
        return name;
    }
    public SkillTier getSkillTier(){
        return skillTier;
    }
    public Class<?> getSkillClass(){
        return skillClass;
    }

    public static Skills getSkillWithName(String name){
        for (Skills skills : Skills.values()){
            if (skills.getSkillName().equalsIgnoreCase(name)){
                return skills;
            }
        }
        return null;
    }

    public static List<Skills> getAllSkillByTier(SkillTier skillTier){
        List<Skills> list = new ArrayList<>();
        for (Skills skills : Skills.values()) if (skills.getSkillTier().equals(skillTier)) list.add(skills);
        return list;
    }








}
