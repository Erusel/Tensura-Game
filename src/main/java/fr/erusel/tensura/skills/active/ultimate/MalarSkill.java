package fr.erusel.tensura.skills.active.ultimate;

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
        super("Malar, Lord of Tracking",  Skills.MALAR, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 600, null);
        super.addLore("Lore TODO");
    }

    @Override
    public void onUse(Player player) {
        GPlayer gPlayer = getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        if (gPlayer.getTrackingPlayer() == null) {
            player.sendMessage("§cYou hit nobody");
            return;
        }
        if (Bukkit.getPlayer(gPlayer.getTrackingPlayer()) == null){
            player.sendMessage("§cPlayer not found");
            return;
        }
        else if (getGameManager().getDeadPlayers().contains(player.getUniqueId())){
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
