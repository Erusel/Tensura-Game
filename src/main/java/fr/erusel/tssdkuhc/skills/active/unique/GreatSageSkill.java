package fr.erusel.tssdkuhc.skills.active.unique;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class GreatSageSkill extends Skill implements ActiveSkill {

    public GreatSageSkill() {
        super("Great Sage", "Use for display all entities around", SkillTier.UNIQUE, 600);
    }

    @Override
    public void onUse(Player player) {
        StringBuilder stringBuilder = new StringBuilder("[§bGreat Sage§f] §aHere the entity around you: \n");
        for (Entity entity : player.getNearbyEntities(25, 25, 25)){
            stringBuilder.append(entity.getName()).append("\n");
        }
        player.sendMessage(stringBuilder.toString());
    }
}
