package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class AmbroisieSkill extends Skill implements ActiveSkill {


    public AmbroisieSkill() {
        super("Ambroisie, Lord of Alchemy", "Give you a random buff potion effect & debuff others near players", SkillTier.ULTIMATE, 1200, null);
    }

    @Override
    public void onUse(Player player) {
        int i = new Random().nextInt(9);
        int duration;
        int amplifier;
        PotionEffectType[] potionsBuff = {
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
        PotionEffectType[] potionsDebuff = {
                PotionEffectType.SLOW,
                PotionEffectType.POISON,
                PotionEffectType.WEAKNESS,
                PotionEffectType.SLOW_DIGGING,
                PotionEffectType.BLINDNESS,
        };
        if (i >= 7) {duration=600; amplifier=0;} // Avoid cheat effect (strength 2, regen 2...)
        else {duration=1200; amplifier=1;}
        player.addPotionEffect(new PotionEffect(potionsBuff[i], duration, amplifier));
        for (Entity entity: player.getNearbyEntities(20,20,20)) {
            if (entity instanceof Player) {
                Player players = (Player) entity;
                int i2 = new Random().nextInt(5);
                players.addPotionEffect(new PotionEffect(potionsDebuff[i2], 600, 0));
            }
        }
    }
}