package fr.erusel.tensura.enums;

import fr.erusel.tensura.modes.BattleRoyalMode;
import fr.erusel.tensura.modes.CharybdisMode;
import fr.erusel.tensura.modes.DebugMode;
import fr.erusel.tensura.objects.Mode;

import java.lang.reflect.InvocationTargetException;

public enum Modes {

    NONE(null, "None", false),
    BATTLE_ROYAL(BattleRoyalMode.class, "Battle Royal", false),
    DEBUG(DebugMode.class, "Debug", false),
    CHARYBDIS(CharybdisMode.class, "Charybdis Hunt", true);

    private final Class<? extends Mode> modeClass;
    private final String modeName;
    private final boolean haveTeam;

    Modes(Class<? extends Mode> modeClass, String modeName, boolean haveTeam) {
        this.modeClass = modeClass;
        this.modeName = modeName;
        this.haveTeam = haveTeam;
    }

    public Class<? extends Mode> getModeClass(){
        return modeClass;
    }
    public String getModeName(){
        return modeName;
    }
    public boolean haveTeam(){
        return haveTeam;
    }

    public Mode createInstance(){
        try {
            return modeClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
