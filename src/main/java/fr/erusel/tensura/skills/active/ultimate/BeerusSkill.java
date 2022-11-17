package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class BeerusSkill extends Skill implements ActiveSkill {


    public BeerusSkill() {
        super("Beerus, Lord of Death", "Kill every entity around you in a radius of 50 blocs\n if they are under 2 heart", SkillTier.ULTIMATE, 1000, null);
    }

    @Override
    public void onUse(Player player) {
        for (Entity entity : player.getNearbyEntities(50, 50, 50)){
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
