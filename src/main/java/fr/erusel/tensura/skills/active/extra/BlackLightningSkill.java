package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class BlackLightningSkill extends Skill implements ActiveSkill {


    public BlackLightningSkill() {
        super("Black Lightning", "Strike near players with a lightning if they're not under a block", Skills.BLACKLIGHTNING, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 800, null);
    }

    @Override
    public void onUse(Player player) {
        // Strike near players with a lightning if there is no block above them
        for (Player p : player.getWorld().getPlayers()) {
            if (p.getLocation().distance(player.getLocation()) <= 30 && p.getLocation().getBlock().getLightLevel() == 15) {
                p.getWorld().strikeLightning(p.getLocation());
            }
        }
        activateCooldown();
    }
}
