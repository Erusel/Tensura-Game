package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.Prefixes;
import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class GreatSageSkill extends Skill implements ActiveSkill {

    public GreatSageSkill() {
        super("Great Sage", Skills.GREATSAGE, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 600, Skills.RAPHAEL);
        super.addLore("Lore TODO");
    }

    @Override
    public void onUse(Player player) {
        StringBuilder stringBuilder = new StringBuilder(Prefixes.GREAT_SAGE.getText() + "§aHere are the entity around you: \n");
        for (Entity entity : player.getNearbyEntities(25, 25, 25)){
            stringBuilder.append(entity.getName()).append("\n");
        }
        player.sendMessage(stringBuilder.toString());
        activateCooldown();
    }
}
