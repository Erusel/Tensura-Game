package fr.erusel.tssdkuhc.skills.active.ultimate;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.erusel.tssdkuhc.threads.OppressorRunnable;
import org.bukkit.entity.Player;

public class JanusSkill extends Skill implements ActiveSkill {


    public JanusSkill() {
        super("Janus, Lord of Gravity", "Increase your knockback for 30s", SkillTier.ULTIMATE, 1100, null);
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
