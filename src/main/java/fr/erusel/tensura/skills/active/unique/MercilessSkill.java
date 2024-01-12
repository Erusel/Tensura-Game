package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class MercilessSkill extends Skill implements ActiveSkill {



    public MercilessSkill() {
        super("Merciless", Skills.MERCILESS, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 1000, Skills.BEERUS);
        super.addLore("Kill near entities under 2 hearts");
    }

    @Override
    public void onUse(Player player) {
        for (Entity entity : player.getNearbyEntities(30, 30, 30)){
            if (!(entity instanceof LivingEntity livingEntity)) {
                continue;
            }
            if (livingEntity.getHealth() <= 4){
                livingEntity.setHealth(0);
            }
        }
        activateCooldown();
    }
}
