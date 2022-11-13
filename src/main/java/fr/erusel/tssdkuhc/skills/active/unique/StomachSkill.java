package fr.erusel.tssdkuhc.skills.active.unique;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.erusel.tssdkuhc.skills.active.ultimate.PandoraSkill;
import org.bukkit.entity.Player;

public class StomachSkill extends Skill implements ActiveSkill {


    public StomachSkill() {
        super("Stomach", "Open personnal chest", SkillTier.UNIQUE, 20, PandoraSkill.class);
    }

    @Override
    public void onUse(Player player) {
        player.openInventory(player.getEnderChest());
        activateCooldown();
    }
}
