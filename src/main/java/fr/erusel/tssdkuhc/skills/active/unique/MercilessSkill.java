package fr.erusel.tssdkuhc.skills.active.unique;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class MercilessSkill extends Skill implements ActiveSkill {


    public MercilessSkill() {
        super("Merciless", "Kill every entity around you in a radius of 30 blocs if they are under 2 heart", SkillTier.UNIQUE, 1000);
    }

    @Override
    public void onUse(Player player) {
        for (Entity entity : player.getNearbyEntities(30, 30, 30)){
            if (entity instanceof LivingEntity){
                LivingEntity livingEntity = (LivingEntity) entity;
                if (livingEntity.getHealth() <= 4){
                    livingEntity.setHealth(0);
                }
            }
        }
    }
}
