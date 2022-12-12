package fr.erusel.tensura.enums;

import fr.erusel.tensura.modes.BattleRoyalMode;
import fr.erusel.tensura.modes.CharybdisMode;
import fr.erusel.tensura.modes.DebugMode;
import fr.erusel.tensura.objects.Mode;

import java.util.function.Supplier;

public enum Modes {

    NONE(null, "None", false),
    BATTLE_ROYAL(BattleRoyalMode::new, "Battle Royal", false),
    DEBUG(DebugMode::new, "Debug", false),
    CHARYBDIS(CharybdisMode::new, "Charybdis Hunt", true);

    private final Supplier<Mode> modeSupplier;
    private final String modeName;
    private final boolean haveTeam;

    Modes(Supplier<Mode> modeSupplier, String modeName, boolean haveTeam) {
        this.modeSupplier = modeSupplier;
        this.modeName = modeName;
        this.haveTeam = haveTeam;
    }

    public String getModeName(){
        return modeName;
    }
    public boolean haveTeam(){
        return haveTeam;
    }

    public Mode createInstance(){
        return modeSupplier.get();
    }
}
