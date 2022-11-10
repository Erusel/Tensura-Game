package fr.erusel.tssdkuhc.skills.active.unique;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Player;

public class StomachSkill extends Skill implements ActiveSkill {


    public StomachSkill() {
        super("Stomach", "Open personnal chest", SkillTier.UNIQUE, 20);
    }

    @Override
    public void onUse(Player player) {
        player.openInventory(player.getEnderChest());
    }
}
