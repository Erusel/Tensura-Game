package fr.erusel.tssdkuhc.skills.active.ultimate;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.GPlayer;
import fr.erusel.tssdkuhc.objects.Skill;
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
