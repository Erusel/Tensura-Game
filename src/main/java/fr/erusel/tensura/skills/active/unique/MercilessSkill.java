package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.BeerusSkill;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class MercilessSkill extends Skill implements ActiveSkill {



    public MercilessSkill() {
        super("Merciless", "Kill every entity around you if they are under 2 heart", Skills.MERCILESS, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 1000, BeerusSkill.class);
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
        activateCooldown();
    }
}
