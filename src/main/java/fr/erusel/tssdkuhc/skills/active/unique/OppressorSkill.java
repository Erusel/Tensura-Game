package fr.erusel.tssdkuhc.skills.active.unique;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.erusel.tssdkuhc.threads.OppressorRunnable;
import org.bukkit.entity.Player;

public class OppressorSkill extends Skill implements ActiveSkill {


    public OppressorSkill() {
        super("Oppressor", "Increase your knockback for 30s", SkillTier.UNIQUE, 800);
    }

    @Override
    public void onUse(Player player) {
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).
                setOppressor(true);
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).
                setOppressorTime(30);
        new OppressorRunnable(Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()))
                .runTaskTimer(Main.getInstance(), 0, 20);
    }
}
