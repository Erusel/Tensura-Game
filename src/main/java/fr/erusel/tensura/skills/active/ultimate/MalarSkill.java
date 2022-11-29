package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MalarSkill extends Skill implements ActiveSkill {

    public MalarSkill() {
        super("Malar, Lord of Tracking", "Track your last enemy and give him glowing effect", Skills.MALAR, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 600, null);
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
        else if (Main.getInstance().getGameManager().getDeadPlayers().contains(player.getUniqueId())){
            player.sendMessage("§cPlayer is dead.");
            return;
        }
        Player trackedPlayer = Bukkit.getPlayer(gPlayer.getTrackingPlayer());
        long x = Math.round(trackedPlayer.getLocation().getX());
        long y = Math.round(trackedPlayer.getLocation().getY());
        long z = Math.round(trackedPlayer.getLocation().getZ());
        player.sendMessage(trackedPlayer.getName() + "'s coordinate is x: " + x + " y: " + y + " z: " + z);
        trackedPlayer.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 600, 1));
        activateCooldown();
    }
}
