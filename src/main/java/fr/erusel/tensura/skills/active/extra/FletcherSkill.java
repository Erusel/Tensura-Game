package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class FletcherSkill extends Skill implements ExtraSkill {


    public FletcherSkill() {
        super("Fletcher", Skills.FLETCHER, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 800, null);
        super.addLore("Lore TODO");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Change next arrow effect";
    }


    @Override
    public void onRightClick(Player player) {
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

    @Override
    public void onLeftClick(Player player) {
        // TODO
    }
}