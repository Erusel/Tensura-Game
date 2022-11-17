package fr.erusel.tensura.enums;

public enum SkillTier {

    EXTRA("§2§bExtra Skill"),
    UNIQUE("§1§bUnique Skill"),
    ULTIMATE("§6§bUltimate Skill"),
    RESISTANCE("§9§bResistance");


    private String text;

    SkillTier(String text) {
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
