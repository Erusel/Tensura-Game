package fr.erusel.tensura.enums;

import fr.erusel.tensura.objects.Mode;

public enum Modes {

    NONE(null, "None"),
    BATTLE_ROYAL(null, "Battle Royal"),
    CHARYBDIS(null, "Charybdis Hunt");

    private final Class<? extends Mode> modeClass;
    private final String modeName;

    Modes(Class<? extends Mode> modeClass, String modeName) {
        this.modeClass = modeClass;
        this.modeName = modeName;
    }

    public Class<? extends Mode> getModeClass(){
        return modeClass;
    }
    public String getModeName(){
        return modeName;
    }
}
