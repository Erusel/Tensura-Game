package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class SpatialMotionSkill extends Skill implements ExtraSkill {


    public SpatialMotionSkill() {
        super("Spatial motion",  Skills.SPATIALMOTION, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 600, null);
        super.addLore("Lore TODO");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Spatial Motion à diviser";
    }

    @Override
    public void onRightClick(Player player) {
        if (getPlayerManager().getPlayerSpatialLocation(player) != null) {
            player.teleport(getPlayerManager().getPlayerSpatialLocation(player));
            player.sendMessage("§aTeleported");
            getPlayerManager().deletePlayerSpatialLocation(player);
            activateCooldown();
            return;
        }
        getPlayerManager().setPlayerSpatialLocation(player, player.getLocation());
        player.sendMessage("§aTeleport point set");
    }

    @Override
    public void onLeftClick(Player player) {

    }
}
