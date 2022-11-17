package fr.erusel.tssdkuhc.enums;

import fr.erusel.tssdkuhc.objects.Skill;
import fr.erusel.tssdkuhc.skills.active.ultimate.*;
import fr.erusel.tssdkuhc.skills.active.unique.*;
import fr.erusel.tssdkuhc.skills.passive.resistance.*;
import fr.erusel.tssdkuhc.skills.passive.ultimate.*;
import fr.erusel.tssdkuhc.skills.passive.unique.*;

import java.util.ArrayList;
import java.util.List;

public enum Skills {

    // Resistance Skill

    FIRERESISTANT("Fire Resistant", SkillTier.RESISTANCE, FireResistantSkill.class),
    LAVARESISTANT("Lava Resistant", SkillTier.RESISTANCE, LavaResistantSkill.class),
    FALLRESISTANT("Fall Resistant",SkillTier.RESISTANCE, FallResistantSkill.class),
    ARROWRESISTANT("Arrow Resistant",SkillTier.RESISTANCE, ArrowResistantSkill.class),
    POISONRESISTANT("Poison Resistant",SkillTier.RESISTANCE, PoisonResistantSkill.class),
    EXPLOSIONRESISTANT("Explosion Resistant", SkillTier.RESISTANCE, ExplosionResistantSkill.class),
    DROWNINGRESISTANT("Drowning Resistant", SkillTier.RESISTANCE, DrowningResistantSkill.class),
    LIGHTNINGRESISTANT("Lightning Resistant", SkillTier.RESISTANCE, LightningResistantSkill.class),
    INVESTIGATORRESISTANT("Investigator Resistant", SkillTier.RESISTANCE, InvestigatorResistantSkill.class),

    // Unique Skills
    GREATSAGE("Great Sage", SkillTier.UNIQUE, GreatSageSkill.class),
    SPEEDY("Speedy", SkillTier.UNIQUE, SpeedySkill.class),
    GLUTONNY("Glutonny", SkillTier.UNIQUE, GlutonnySkill.class),
    BERSERKER("Berserker", SkillTier.UNIQUE, BerserkerSkill.class),
    OPPRESSOR("Oppressor", SkillTier.UNIQUE, OppressorSkill.class),
    MERCILESS("Merciless", SkillTier.UNIQUE, MercilessSkill.class),
    MATHEMATICIAN("Mathematician", SkillTier.UNIQUE, MathematicianSkill.class),
    STOMACH("Stomach", SkillTier.UNIQUE, StomachSkill.class),
    INVESTIGATOR("Investigator",SkillTier.UNIQUE, InvestigatorSkill.class),
    GUARDIAN("Guardian", SkillTier.UNIQUE, GuardianSkill.class),
    HASTY("Hasty", SkillTier.UNIQUE, HastySkill.class),
    FORTUNA("Fortuna", SkillTier.UNIQUE, FortunaSkill.class),
    TRACKER("Tracker", SkillTier.UNIQUE, TrackerSkill.class),
    LUST("Lust", SkillTier.UNIQUE, LustSkill.class),

    // Ultimate Skills
    RAPHAEL("Raphael, Lord of Wisdom", SkillTier.ULTIMATE, RaphaelSkill.class),
    FLASH("Flash, Lord of Speed", SkillTier.ULTIMATE, FlashSkill.class),
    BEELZEBUTH("Beelzebuth, Lord of Gluttony", SkillTier.ULTIMATE, BeelzebuthSkill.class),
    MOGIS("Mogis, Lord of Berserk", SkillTier.ULTIMATE, MogisSkill.class),
    JANUS("Janus, Lord of Gravity", SkillTier.ULTIMATE, JanusSkill.class),
    BEERUS("Beerus, Lord of Death", SkillTier.ULTIMATE, BeerusSkill.class),
    ALBERT("Albert, Lord of Algebria", SkillTier.ULTIMATE, AlbertSkill.class),
    PANDORA("Pandora, Lord of Gift", SkillTier.ULTIMATE, PandoraSkill.class),
    FAUST("Faust, Lord of Investigation", SkillTier.ULTIMATE, FaustSkill.class),
    HECATE("Hecate, Lord of Protection", SkillTier.ULTIMATE, HecateSkill.class),
    HERMES("Hermes, Lord of Haste", SkillTier.ULTIMATE, HermesSkill.class),
    MALAR("Malar, Lord of Tracking", SkillTier.ULTIMATE, MalarSkill.class),
    OSIRIS("Osiris, Lord of Resurrection", SkillTier.ULTIMATE, OsirisSkill.class),
    PLOUTOS("Ploutos, Lord of Wealth", SkillTier.ULTIMATE, PloutosSkill.class);

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
