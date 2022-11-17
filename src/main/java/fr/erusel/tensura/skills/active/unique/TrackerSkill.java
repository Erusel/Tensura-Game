package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TrackerSkill extends Skill implements ActiveSkill {

    public TrackerSkill() {
        super("Tracker", "Track your last ennemy", SkillTier.UNIQUE, 600, null);
    }

    @Override
    public void onUse(Player player) {
        GPlayer gPlayer = Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        if (gPlayer.getTrackingPlayer() == null) {
            player.sendMessage("§cYou hit nobody");
            return;
        }
        if (Bukkit.getPlayer(gPlayer.getTrackingPlayer()) == null){
            player.sendMessage("§cPlayer not found");
            return;
        }
        Player trackedPlayer = Bukkit.getPlayer(gPlayer.getTrackingPlayer());
        long x = Math.round(trackedPlayer.getLocation().getX());
        long y = Math.round(trackedPlayer.getLocation().getY());
        long z = Math.round(trackedPlayer.getLocation().getZ());
        player.sendMessage(trackedPlayer.getName() + "'s coordinate is x: " + x + " y: " + y + " z: " + z);
        activateCooldown();
    }
}
