package fr.erusel.tensura.objects;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.ScoreBoardManager;

public abstract class GScoreboard{

    ScoreBoardManager scoreBoardManager = Main.getInstance().getScoreboardManager();
    GameManager gameManager = Main.getInstance().getGameManager();

    public GameManager getGameManager() {
        return gameManager;
    }

    public ScoreBoardManager getScoreBoardManager() {
        return scoreBoardManager;
    }

    public abstract void refreshPlayingScoreboard();
}
