package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class PandoraSkill extends Skill implements ActiveSkill {


    public PandoraSkill() {
        super("Pandora, Lord of Gift", Skills.PANDORA, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 20, null);
        super.addLore("Open you a personal double chest");
    }

    @Override
    public void onUse(Player player) {
        GPlayer gPlayer = getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        gPlayer.createPandoraInventory();
        player.openInventory(gPlayer.getPandoraInventory());
        activateCooldown();
    }
}
