package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.AmbroisieSkill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PotionMasterSkill extends Skill implements ActiveSkill {


    public PotionMasterSkill() {
        super("Potion Master", "Give you a random buff potion effect", Skills.POTIONMASTER, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 800, AmbroisieSkill.class);
    }

    @Override
    public void onUse(Player player) {
        int i = new Random().nextInt(9);
        int duration;
        int amplifier;
        PotionEffectType[] potions = {
                PotionEffectType.INVISIBILITY,
                PotionEffectType.JUMP,
                PotionEffectType.FIRE_RESISTANCE,
                PotionEffectType.SPEED,
                PotionEffectType.WATER_BREATHING,
                PotionEffectType.SLOW_FALLING,
                PotionEffectType.HEAL,
                PotionEffectType.REGENERATION,
                PotionEffectType.INCREASE_DAMAGE,
        };
        if (i >= 7) {duration=600; amplifier=0;} // Pour éviter des effets trop cheat (regen 2, force 2...)
        else {duration=1200; amplifier=1;}
        player.addPotionEffect(new PotionEffect(potions[i], duration, amplifier));
        }
    }
