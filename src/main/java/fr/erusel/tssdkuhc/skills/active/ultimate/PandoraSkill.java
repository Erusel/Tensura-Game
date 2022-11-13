package fr.erusel.tssdkuhc.skills.active.ultimate;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Player;

public class PandoraSkill extends Skill implements ActiveSkill {


    public PandoraSkill() {
        super("Pandora, Lord of Gift", "Open personnal chest", SkillTier.ULTIMATE, 20, null);
    }

    @Override
    public void onUse(Player player) {
        player.openInventory(player.getEnderChest());
        activateCooldown();
    }
}
