package fr.erusel.tensura.objects;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.managers.*;

public abstract class GameElement {
    GameManager gameManager = GameManager.getInstance();
    PlayerManager playerManager = PlayerManager.getInstance();
    GameSettingsManager gameSettingManager = GameSettingsManager.getInstance();
    WorldManager worldManager = WorldManager.getInstance();
    ScoreBoardManager scoreBoardManager = ScoreBoardManager.getInstance();
    TeamManager teamManager = TeamManager.getInstance();
    GItemManager gItemManager = GItemManager.getInstance();
    Main main = Main.getInstance();



    public GameManager getGameManager() {
        return gameManager;
    }
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    public WorldManager getWorldManager() {
        return worldManager;
    }
    public GameSettingsManager getGameSettingManager() {
        return gameSettingManager;
    }
    public ScoreBoardManager getScoreBoardManager() {
        return scoreBoardManager;
    }
    public TeamManager getTeamManager() {
        return teamManager;
    }
    public GItemManager getGItemManager() {
        return gItemManager;
    }
    public Main getMain() {
        return main;
    }


}
