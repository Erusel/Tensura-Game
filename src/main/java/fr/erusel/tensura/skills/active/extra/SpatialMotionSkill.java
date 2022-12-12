package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class SpatialMotionSkill extends Skill implements ActiveSkill {

    PlayerManager playerManager;

    public SpatialMotionSkill() {
        super("Spatial motion", "Place a TP point and teleport you on demand", Skills.SPATIALMOTION, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 600, null);
        this.playerManager = PlayerManager.getInstance();
    }

    @Override
    public void onUse(Player player) {
        if (playerManager.getPlayerSpatialLocation(player) != null) {
            player.teleport(playerManager.getPlayerSpatialLocation(player));
            player.sendMessage("§aTeleported");
            playerManager.deletePlayerSpatialLocation(player);
            activateCooldown();
            return;
        }
        playerManager.setPlayerSpatialLocation(player, player.getLocation());
        player.sendMessage("§aTeleport point set");
    }
}
