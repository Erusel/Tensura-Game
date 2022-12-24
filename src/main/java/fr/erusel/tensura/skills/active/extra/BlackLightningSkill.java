package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class BlackLightningSkill extends Skill implements ExtraSkill {


    public BlackLightningSkill() {
        super("Black Lightning", Skills.BLACKLIGHTNING, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 800, null);
        super.addLore("Lore TODO");
        super.addLore("Ta mere la pute fnafgameur");
        super.addLore("et oui pd");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Strike arounds players";
    }

    @Override
    public void onRightClick(Player player) {
        // Strike near players with a lightning if there is no block above them
        for (Player p : player.getWorld().getPlayers()) {
            if (p.getLocation().distance(player.getLocation()) <= 30 && p.getLocation().getBlock().getLightLevel() == 15) {
                p.getWorld().strikeLightning(p.getLocation());
            }
        }
        activateCooldown();
    }

    @Override
    public void onLeftClick(Player player) {

    }
}
