package fr.erusel.tensura.enums;

import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.*;
import fr.erusel.tensura.skills.active.unique.*;
import fr.erusel.tensura.skills.passive.resistance.*;
import fr.erusel.tensura.skills.passive.ultimate.*;
import fr.erusel.tensura.skills.passive.unique.*;

import java.lang.reflect.InvocationTargetException;
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
    WITHERRESISTANT("Wither Resistant", SkillTier.RESISTANCE, WitherResistantSkill.class),

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
    IMPERCEPTIBLE("Imperceptible", SkillTier.UNIQUE, ImperceptibleSkill.class),
    MAGICWOLF("Magic Wolf", SkillTier.UNIQUE, MagicWolfSkill.class),
    POTIONMASTER("Potion Master", SkillTier.UNIQUE, PotionMasterSkill.class),
    REFLECTOR("Reflector", SkillTier.UNIQUE, ReflectorSkill.class),

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
    HADES("Hades, Lord of Invisibility", SkillTier.ULTIMATE, HadesSkill.class),
    OSIRIS("Osiris, Lord of Resurrection", SkillTier.ULTIMATE, OsirisSkill.class),
    PLOUTOS("Ploutos, Lord of Wealth", SkillTier.ULTIMATE, PloutosSkill.class),
    RANGA("Ranga, Lord of the toutous", SkillTier.ULTIMATE, RangaSkill.class),
    AMBROISIE("Ambroisie, Lord of Alchemy", SkillTier.ULTIMATE, AmbroisieSkill.class),
    OMOIKANE("Omoikane, Lord of Reflection", SkillTier.ULTIMATE, OmoikaneSkill.class);

    private final String name;
    private final SkillTier skillTier;
    private final Class<? extends Skill> skillClass;

    Skills(String skillName, SkillTier skillTier, Class<? extends Skill> skillClass) {
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
    public Class<? extends Skill> getSkillClass(){
        return skillClass;
    }

    public static List<Skills> getAllSkillByTier(SkillTier skillTier){
        List<Skills> list = new ArrayList<>();
        for (Skills skills : Skills.values()) if (skills.getSkillTier().equals(skillTier)) list.add(skills);
        return list;
    }

    public Skill createInstance(){
        try {
            return skillClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
