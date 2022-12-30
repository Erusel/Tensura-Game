package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BeerusSkill extends Skill implements ActiveSkill {


    public BeerusSkill() {
        super("Beerus, Lord of Death", Skills.BEERUS, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 1000, null);
        super.addLore("Kill near entities under 2 hearts or give them nausea if not");
    }

    @Override
    public void onUse(Player player) {
        for (Entity entity : player.getNearbyEntities(50, 50, 50)){
            if (entity instanceof LivingEntity){
                LivingEntity livingEntity = (LivingEntity) entity;
                if (livingEntity.getHealth() <= 4) livingEntity.setHealth(0);
                else if (livingEntity.getHealth() <= 12) livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 1));
            }
        }
        activateCooldown();
    }
}
