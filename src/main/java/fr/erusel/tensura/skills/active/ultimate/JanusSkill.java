package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.OppressorRunnable;
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
        activateCooldown();
    }
}
