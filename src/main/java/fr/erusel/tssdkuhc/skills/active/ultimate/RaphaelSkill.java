package fr.erusel.tssdkuhc.skills.active.ultimate;

import fr.erusel.tssdkuhc.enums.Prefixs;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class RaphaelSkill extends Skill implements ActiveSkill {

    public RaphaelSkill() {
        super("Raphael, Lord of Wisdom", "Use for display all entities around", SkillTier.ULTIMATE, 600, null);
    }

    @Override
    public void onUse(Player player) {
        StringBuilder stringBuilder = new StringBuilder(Prefixs.RAPAHEL.getText() + "§aHere are the entity around you: \n");
        for (Entity entity : player.getNearbyEntities(25, 25, 25)){
            stringBuilder.append(entity.getName()).append("\n");
        }
        player.sendMessage(stringBuilder.toString());
        activateCooldown();
    }
}
