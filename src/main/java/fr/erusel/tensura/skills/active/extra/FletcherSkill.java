package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class FletcherSkill extends Skill implements ActiveSkill {


    public FletcherSkill() {
        super("Fletcher", "Your next arrow will give a debuff effect", Skills.FLETCHER, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 800, null);
    }

    @Override
    public void onUse(Player player) {
        PotionEffectType[] potionsDebuff = {
                PotionEffectType.SLOW,
                PotionEffectType.POISON,
                PotionEffectType.WEAKNESS,
                PotionEffectType.SLOW_DIGGING,
                PotionEffectType.BLINDNESS,
                PotionEffectType.CONFUSION,
                PotionEffectType.HUNGER,
        };
        int i = new Random().nextInt(potionsDebuff.length);
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setFletcherEffect(potionsDebuff[i]);
        player.sendMessage("§6Your next arrow will inflict " + potionsDebuff[i].getName());
        activateCooldown();
    }
}