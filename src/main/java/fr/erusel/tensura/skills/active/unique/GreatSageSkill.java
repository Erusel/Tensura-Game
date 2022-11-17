package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.Prefixs;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.RaphaelSkill;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class GreatSageSkill extends Skill implements ActiveSkill {

    public GreatSageSkill() {
        super("Great Sage", "Use for display all entities around", SkillTier.UNIQUE, 600, RaphaelSkill.class);
    }

    @Override
    public void onUse(Player player) {
        StringBuilder stringBuilder = new StringBuilder(Prefixs.GREAT_SAGE.getText() + "§aHere are the entity around you: \n");
        for (Entity entity : player.getNearbyEntities(25, 25, 25)){
            stringBuilder.append(entity.getName()).append("\n");
        }
        player.sendMessage(stringBuilder.toString());
        activateCooldown();
    }
}
