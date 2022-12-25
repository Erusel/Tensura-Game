package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class LightningManipulationSkill extends Skill implements ExtraSkill {


    public LightningManipulationSkill() {
        super("Lightning Manipulation", Skills.LIGHTNING, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 800, null);
        super.addLore("Use the power of the Lightning");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Strike arounds players if they're not under a block";
    }

    @Override
    public void onRightClick(Player player) {
        // Strike near players with a lightning if there is no block above them
        for (Player p : player.getWorld().getPlayers()) {
            if (p != player) {
                if (p.getLocation().distance(player.getLocation()) <= 30 && p.getLocation().getBlock().getLightLevel() == 15) {
                    p.getWorld().strikeLightning(p.getLocation());
                }
            }
        }
        activateCooldown();
    }

    @Override
    public String getLeftClickSkillLore() {
        return "Strike where you're looking";
    }

    @Override
    public void onLeftClick(Player player) {
        // Strike where the player is looking with multiple lightning
        for (int i = 0; i < 10; i++) {
            player.getWorld().strikeLightning(player.getTargetBlock(null, 100).getLocation());
        }
        activateCooldown();
    }
}
