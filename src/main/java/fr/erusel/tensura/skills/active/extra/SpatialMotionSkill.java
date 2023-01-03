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
        super.addLore("Navigate through Space");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Place a spatial point";
    }

    @Override
    public void onRightClick(Player player) {
        getPlayerManager().setPlayerSpatialLocation(player, player.getLocation());
        int locationx = player.getLocation().getBlockX();
        int locationy = player.getLocation().getBlockY();
        int locationz = player.getLocation().getBlockZ();
        player.sendMessage("§6Teleportation point set to " + locationx + " " + locationy + " " + locationz);
    }

    @Override
    public String getLeftClickSkillLore() {
        return "Teleport yourself to the spatial point";
    }


    @Override
    public void onLeftClick(Player player) {
        player.teleport(getPlayerManager().getPlayerSpatialLocation(player));
        player.sendMessage("§6Successfully teleported to your spatial location point");
        player.playSound(player.getLocation(), "minecraft:entity.enderman.teleport", 1, 1);
        player.spawnParticle(org.bukkit.Particle.PORTAL, player.getLocation(), 100, 1, 1, 1);
        getPlayerManager().deletePlayerSpatialLocation(player);
        activateCooldown();
    }
}
