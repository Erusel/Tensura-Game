package fr.erusel.tensura.objects;

import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.managers.ScoreBoardManager;

public abstract class GScoreboard{

    GameManager gameManager = GameManager.getInstance();
    PlayerManager playerManager = PlayerManager.getInstance();
    ScoreBoardManager scoreBoardManager = ScoreBoardManager.getInstance();

    public abstract void refreshPlayingScoreboard();

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    public GameManager getGameManager() {
        return gameManager;
    }
    public ScoreBoardManager getScoreBoardManager() {
        return scoreBoardManager;
    }
}
