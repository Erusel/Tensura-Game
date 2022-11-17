package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class PandoraSkill extends Skill implements ActiveSkill {


    public PandoraSkill() {
        super("Pandora, Lord of Gift", "Open bigger personnal chest", SkillTier.ULTIMATE, 20, null);
    }

    @Override
    public void onUse(Player player) {
        GPlayer gPlayer = Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        gPlayer.createPandoraInventory();
        player.openInventory(gPlayer.getPandoraInventory());
        activateCooldown();
    }
}
