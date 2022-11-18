package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.JanusSkill;
import fr.erusel.tensura.threads.OppressorRunnable;
import org.bukkit.entity.Player;

public class OppressorSkill extends Skill implements ActiveSkill {


    public OppressorSkill() {
        super("Oppressor", "Increase your knockback for 30s", SkillTier.UNIQUE, 800, JanusSkill.class);
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