package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.utils.Utils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class AmbroisieSkill extends Skill implements ActiveSkill {

    private final Random random = new Random();

    public AmbroisieSkill() {
        super("Ambroisie, Lord of Alchemy", Skills.AMBROISIE, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 1200, null);
        super.addLore("Grant you a random buff potion effect and a random debuff potion effect");
        super.addLore("for players around you");
    }

    @Override
    public void onUse(Player player) {

        PotionEffectType[] potionsBuff = Utils.potionsBuff;
        PotionEffectType[] potionsDebuff = Utils.potionsDebuff;
        int i = random.nextInt(potionsBuff.length);
        int duration;
        int amplifier;

        // Avoid cheat effect (strength 2, regen 2...)
        if (i >= 7) {
            duration=600;
            amplifier=0;
        }
        else {
            duration=1200;
            amplifier=1;
        }

        player.addPotionEffect(new PotionEffect(Utils.potionsBuff[i], duration, amplifier));

        for (Entity entity: player.getNearbyEntities(20,20,20)) {

            if (!(entity instanceof Player players)) {
                continue;
            }
            int i2 = random.nextInt(potionsDebuff.length);
            players.addPotionEffect(new PotionEffect(Utils.potionsDebuff[i2], 600, 0));
        }
    }
}