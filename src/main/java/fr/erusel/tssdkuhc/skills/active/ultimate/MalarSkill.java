package fr.erusel.tssdkuhc.skills.active.ultimate;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.GPlayer;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MalarSkill extends Skill implements ActiveSkill {

    public MalarSkill() {
        super("Malar, Lord of Tracking", "Track your last ennemy", SkillTier.ULTIMATE, 600, null);
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
