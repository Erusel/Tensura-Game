package fr.erusel.tssdkuhc.skills.active.unique;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.GPlayer;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TrackerSkill extends Skill implements ActiveSkill {

    public TrackerSkill() {
        super("Tracker", "Track your last ennemy", SkillTier.UNIQUE, 600, null);
    }

    @Override
    public void onUse(Player player) {
        GPlayer gPlayer = Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        if (gPlayer.getTrackingPlayer() == null) return;
        if (Bukkit.getPlayer(gPlayer.getTrackingPlayer()) == null) return;
        Player trackedPlayer = Bukkit.getPlayer(gPlayer.getTrackingPlayer());
        player.sendMessage(trackedPlayer.getName() + "'s coordinate is x: " + trackedPlayer.getLocation().getX() + " y: " + trackedPlayer.getLocation().getY() + " z: " + trackedPlayer.getLocation().getZ());
        activateCooldown();
    }
}
