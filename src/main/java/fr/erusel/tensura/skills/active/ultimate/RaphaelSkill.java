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
        super("Raphael, Lord of Wisdom", Skills.RAPHAEL, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 800, null);
        super.addLore("Find all the entities around you and get informations about them");
    }

    @Override
    public void onUse(Player player) {

        StringBuilder stringBuilder = new StringBuilder(Prefixes.RAPAHEL.getText() + "§aHere are the entities around you: \n");

        for (Entity entity : player.getNearbyEntities(50, 50, 50)) {

            if (entity instanceof LivingEntity) {
                if (entity instanceof Player players) {
                    stringBuilder.append(players.getName()).append(" Life : ").append(players.getHealth()).append("\n");
                }
                else {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    stringBuilder.append(entity.getName()).append(" Life : ").append(livingEntity.getHealth()).append("\n");
                }
            }
            else {
                stringBuilder.append(entity.getName()).append("\n");
            }
        }
        player.sendMessage(stringBuilder.toString());
        activateCooldown();
    }
}
