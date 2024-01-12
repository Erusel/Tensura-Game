package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SenseHeatSourceSkill extends Skill implements ExtraSkill {


    public SenseHeatSourceSkill() {
        super("Sense Heat Source", Skills.SENSEHEAT, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 800, null);
        super.addLore("Boost your senses to find heat sources");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Sense near player heat";
    }

    @Override
    public void onRightClick(Player player) {
        //apply glowing effect to player in a radius of 30 blocks if they're not sneaking
        for (Player p : player.getWorld().getPlayers()) {
            if (p.getLocation().distance(player.getLocation()) <= 30 && !p.isSneaking()) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 1, false, false,true));
            }
        }
        activateCooldown();
    }

    @Override
    public void onLeftClick(Player player) {

    }
}
