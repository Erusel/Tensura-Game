package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PotionMasterSkill extends Skill implements ActiveSkill {

    private final Random random = new Random();

    public PotionMasterSkill() {
        super("Potion Master", Skills.POTIONMASTER, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 800, Skills.AMBROISIE);
        super.addLore("Grant you a random buff potion effect");
    }

    @Override
    public void onUse(Player player) {

        PotionEffectType[] potions = Utils.potionsBuff;
        int i = random.nextInt(potions.length);
        int duration;
        int amplifier;

        if (i >= 7) {
            duration=600; amplifier=0;
        }
        else {
            duration=1200; amplifier=1;
        }
        player.addPotionEffect(new PotionEffect(potions[i], duration, amplifier));
        activateCooldown();
    }
}
