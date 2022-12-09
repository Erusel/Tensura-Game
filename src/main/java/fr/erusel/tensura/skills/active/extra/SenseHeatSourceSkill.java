package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SenseHeatSourceSkill extends Skill implements ActiveSkill {


    public SenseHeatSourceSkill() {
        super("Sense Heat Source", "Apply glowing effect to near player if they're not sneaking", Skills.SENSEHEAT, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 800, null);
    }

    @Override
    public void onUse(Player player) {
        //apply glowing effect to player in a radius of 30 blocks if they're not sneaking
        for (Player p : player.getWorld().getPlayers()) {
            if (p.getLocation().distance(player.getLocation()) <= 30 && !p.isSneaking()) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 1));
            }
        }
        activateCooldown();
    }
}
