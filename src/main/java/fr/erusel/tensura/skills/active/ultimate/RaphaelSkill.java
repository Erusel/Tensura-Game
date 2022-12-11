package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.Prefixes;
import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class RaphaelSkill extends Skill implements ActiveSkill {

    public RaphaelSkill() {
        super("Raphael, Lord of Wisdom", "Use for display all entities around", Skills.RAPHAEL, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 800, null);
    }

    @Override
    public void onUse(Player player) {
        StringBuilder stringBuilder = new StringBuilder(Prefixes.RAPAHEL.getText() + "§aHere are the entity around you: \n");
        for (Entity entity : player.getNearbyEntities(50, 50, 50)){
            if (entity instanceof LivingEntity) {
                if (entity instanceof Player) stringBuilder.append(((Player) entity).getDisplayName()).append(" Life : ").append(((LivingEntity) entity).getHealth()).append("\n");
                else stringBuilder.append(entity.getName()).append(" Life : ").append(((LivingEntity) entity).getHealth()).append("\n");
            }
            else stringBuilder.append(entity.getName()).append("\n");
        }
        player.sendMessage(stringBuilder.toString());
        activateCooldown();
    }
}
