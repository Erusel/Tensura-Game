package fr.erusel.tensura.enums;

import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.extra.*;
import fr.erusel.tensura.skills.active.ultimate.*;
import fr.erusel.tensura.skills.active.unique.*;
import fr.erusel.tensura.skills.passive.extra.TelekinesisSkill;
import fr.erusel.tensura.skills.passive.resistance.*;
import fr.erusel.tensura.skills.passive.ultimate.*;
import fr.erusel.tensura.skills.passive.unique.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public enum Skills {

    // Resistance Skill

    FIRERESISTANT("Fire Resistant", SkillTier.RESISTANCE, FireResistantSkill::new),
    LAVARESISTANT("Lava Resistant", SkillTier.RESISTANCE, LavaResistantSkill::new),
    FALLRESISTANT("Fall Resistant",SkillTier.RESISTANCE, FallResistantSkill::new),
    ARROWRESISTANT("Arrow Resistant",SkillTier.RESISTANCE, ArrowResistantSkill::new),
    POISONRESISTANT("Poison Resistant",SkillTier.RESISTANCE, PoisonResistantSkill::new),
    EXPLOSIONRESISTANT("Explosion Resistant", SkillTier.RESISTANCE, ExplosionResistantSkill::new),
    DROWNINGRESISTANT("Drowning Resistant", SkillTier.RESISTANCE, DrowningResistantSkill::new),
    LIGHTNINGRESISTANT("Lightning Resistant", SkillTier.RESISTANCE, LightningResistantSkill::new),
    INVESTIGATORRESISTANT("Investigator Resistant", SkillTier.RESISTANCE, InvestigatorResistantSkill::new),
    WITHERRESISTANT("Wither Resistant", SkillTier.RESISTANCE, WitherResistantSkill::new),

    // Unique Skills
    GREATSAGE("Great Sage", SkillTier.UNIQUE, GreatSageSkill::new),
    SPEEDY("Speedy", SkillTier.UNIQUE, SpeedySkill::new),
    GLUTONNY("Glutonny", SkillTier.UNIQUE, GlutonnySkill::new),
    BERSERKER("Berserker", SkillTier.UNIQUE, BerserkerSkill::new),
    OPPRESSOR("Oppressor", SkillTier.UNIQUE, OppressorSkill::new),
    MERCILESS("Merciless", SkillTier.UNIQUE, MercilessSkill::new),
    MATHEMATICIAN("Mathematician", SkillTier.UNIQUE, MathematicianSkill::new),
    STOMACH("Stomach", SkillTier.UNIQUE, StomachSkill::new),
    INVESTIGATOR("Investigator",SkillTier.UNIQUE, InvestigatorSkill::new),
    GUARDIAN("Guardian", SkillTier.UNIQUE, GuardianSkill::new),
    HASTY("Hasty", SkillTier.UNIQUE, HastySkill::new),
    FORTUNA("Fortuna", SkillTier.UNIQUE, FortunaSkill::new),
    TRACKER("Tracker", SkillTier.UNIQUE, TrackerSkill::new),
    LUST("Lust", SkillTier.UNIQUE, LustSkill::new),
    IMPERCEPTIBLE("Imperceptible", SkillTier.UNIQUE, ImperceptibleSkill::new),
    MAGICWOLF("Magic Wolf", SkillTier.UNIQUE, MagicWolfSkill::new),
    POTIONMASTER("Potion Master", SkillTier.UNIQUE, PotionMasterSkill::new),
    REFLECTOR("Reflector", SkillTier.UNIQUE, ReflectorSkill::new),
    VAMPIRISM("Vampirism", SkillTier.UNIQUE, VampirismSkill::new),
    PERSONALDIM("Personal Dimension", SkillTier.UNIQUE, PersonalDimensionSkill::new),

    // Extra Skills
    FLETCHER("Fletcher", SkillTier.EXTRA, FletcherSkill::new),
    SENSEHEAT("Sense Heat", SkillTier.EXTRA, SenseHeatSourceSkill::new),
    LIGHTNING("Lightning Manipulation", SkillTier.EXTRA, LightningManipulationSkill::new),
    WINDMANIPULATION("Wind Manipulation", SkillTier.EXTRA, WindManipulationSkill::new),
    ICEMANIPULATION("Ice Manipulation", SkillTier.EXTRA, IceManipulationSkill::new),
    SPATIALMOTION("Spatial Motion", SkillTier.EXTRA, SpatialMotionSkill::new),
    ULTRASPEEDREGEN("Ultra Speed Regen", SkillTier.EXTRA, UltraSpeedRegenSkill::new),
    FIREMANIPULATION("Fire Manipulation", SkillTier.EXTRA, FireManipulationSkill::new),
    TELEKINESIS("Telekinesis", SkillTier.EXTRA, TelekinesisSkill::new),

    // Ultimate Skills
    RAPHAEL("Raphael, Lord of Wisdom", SkillTier.ULTIMATE, RaphaelSkill::new),
    FLASH("Flash, Lord of Speed", SkillTier.ULTIMATE, FlashSkill::new),
    BEELZEBUTH("Beelzebuth, Lord of Gluttony", SkillTier.ULTIMATE, BeelzebuthSkill::new),
    MOGIS("Mogis, Lord of Berserk", SkillTier.ULTIMATE, MogisSkill::new),
    JANUS("Janus, Lord of Gravity", SkillTier.ULTIMATE, JanusSkill::new),
    BEERUS("Beerus, Lord of Death", SkillTier.ULTIMATE, BeerusSkill::new),
    ALBERT("Albert, Lord of Algebria", SkillTier.ULTIMATE, AlbertSkill::new),
    PANDORA("Pandora, Lord of Gift", SkillTier.ULTIMATE, PandoraSkill::new),
    FAUST("Faust, Lord of Investigation", SkillTier.ULTIMATE, FaustSkill::new),
    HECATE("Hecate, Lord of Protection", SkillTier.ULTIMATE, HecateSkill::new),
    HERMES("Hermes, Lord of Haste", SkillTier.ULTIMATE, HermesSkill::new),
    MALAR("Malar, Lord of Tracking", SkillTier.ULTIMATE, MalarSkill::new),
    HADES("Hades, Lord of Invisibility", SkillTier.ULTIMATE, HadesSkill::new),
    OSIRIS("Osiris, Lord of Resurrection", SkillTier.ULTIMATE, OsirisSkill::new),
    PLOUTOS("Ploutos, Lord of Wealth", SkillTier.ULTIMATE, PloutosSkill::new),
    RANGA("Ranga, Lord of the toutous", SkillTier.ULTIMATE, RangaSkill::new),
    AMBROISIE("Ambroisie, Lord of Alchemy", SkillTier.ULTIMATE, AmbroisieSkill::new),
    OMOIKANE("Omoikane, Lord of Reflection", SkillTier.ULTIMATE, OmoikaneSkill::new),
    DRACULA("Dracula, Lord of Vampirism", SkillTier.ULTIMATE, DraculaSkill::new);

    private final String name;
    private final SkillTier skillTier;
    private final Supplier<Skill> skillSupplier;

    Skills(String skillName, SkillTier skillTier, Supplier<Skill> skillSupplier) {
        this.name = skillName;
        this.skillTier = skillTier;
        this.skillSupplier = skillSupplier;
    }

    public String getSkillName(){
        return name;
    }
    public SkillTier getSkillTier(){
        return skillTier;
    }
    public static List<Skills> getAllSkillByTier(SkillTier skillTier){
        List<Skills> list = new ArrayList<>();
        for (Skills skills : Skills.values()) if (skills.getSkillTier().equals(skillTier)) list.add(skills);
        return list;
    }
    public static Skills getRandomSkillByTier(SkillTier skillTier){
        List<Skills> skills = getAllSkillByTier(skillTier);
        return skills.get(new Random().nextInt(skills.size()));
    }
    public Skill createInstance(){
        return skillSupplier.get();
    }


}
