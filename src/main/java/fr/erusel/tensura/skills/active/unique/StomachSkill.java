package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class StomachSkill extends Skill implements ActiveSkill {


    public StomachSkill() {
        super("Stomach", Skills.STOMACH, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 20, Skills.PANDORA);
        super.addLore("Lore TODO");
    }
    @Override
    public void onUse(Player player) {
        GPlayer gPlayer = getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        gPlayer.createStomachInventory();
        player.openInventory(gPlayer.getStomachInventory());
        activateCooldown();
    }
}
