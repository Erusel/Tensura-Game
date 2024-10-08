package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TrackerSkill extends Skill implements ActiveSkill {

    public TrackerSkill() {
        super("Tracker",Skills.TRACKER, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 600, Skills.MALAR);
        super.addLore("Track the last player you hit");
    }

    @Override
    public void onUse(Player player) {

        GPlayer gPlayer = getPlayerManager().getGPlayerByUUID(player.getUniqueId());

        if (gPlayer.getTrackingPlayer() == null) {
            player.sendMessage("§cYou didn't hit anyone");
            return;
        }

        Player trackedPlayer = Bukkit.getPlayer(gPlayer.getTrackingPlayer());

        if (trackedPlayer == null){
            player.sendMessage("§cPlayer not found");
            return;
        }
        else if (getGameManager().getDeadPlayers().contains(gPlayer.getTrackingPlayer())) {
            player.sendMessage("§cPlayer is dead");
            return;
        }
        long x = Math.round(trackedPlayer.getLocation().getX());
        long y = Math.round(trackedPlayer.getLocation().getY());
        long z = Math.round(trackedPlayer.getLocation().getZ());
        player.sendMessage(trackedPlayer.getName() + "'s coordinate is x: " + x + " y: " + y + " z: " + z);
        activateCooldown();
    }
}
