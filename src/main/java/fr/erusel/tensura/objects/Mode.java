package fr.erusel.tensura.objects;

import fr.erusel.tensura.enums.Modes;

public abstract class Mode extends GameElement implements Eventable{


    private final String modeName;
    private final Modes mode;
    private final boolean haveTeam;
    private final GScoreboard gScoreboard;

    public Mode(String modeName, Modes mode, GScoreboard gScoreboard, boolean haveTeam) {
        this.modeName = modeName;
        this.mode = mode;
        this.haveTeam = haveTeam;
        this.gScoreboard = gScoreboard;
    }

    public Modes getMode() {
        return mode;
    }
    public String getModeName() {
        return modeName;
    }
    public boolean haveTeam(){
        return haveTeam;
    }
    public GScoreboard getGScoreboard(){
        return gScoreboard;
    }

    public void refreshScoreboard(){
        gScoreboard.refreshPlayingScoreboard();
    }

}
