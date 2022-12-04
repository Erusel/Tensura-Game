package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.PandoraSkill;
import org.bukkit.entity.Player;

public class StomachSkill extends Skill implements ActiveSkill {


    public StomachSkill() {
        super("Stomach", "Open personnal chest", Skills.STOMACH, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 20, PandoraSkill.class);
    }

    @Override
    public void onUse(Player player) {
        GPlayer gPlayer = getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        gPlayer.createStomachInventory();
        player.openInventory(gPlayer.getStomachInventory());
        activateCooldown();
    }
}
