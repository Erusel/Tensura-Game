package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.OppressorRunnable;
import org.bukkit.entity.Player;

public class JanusSkill extends Skill implements ActiveSkill {


    public JanusSkill() {
        super("Janus, Lord of Gravity",  Skills.JANUS, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 1100, null);
        super.addLore("Knockback away the players you hit");
    }

    @Override
    public void onUse(Player player) {
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setOppressor(true);
        new OppressorRunnable(getPlayerManager().getGPlayerByUUID(player.getUniqueId()), 60)
                .runTaskTimer(getMain(), 0, 20);
        activateCooldown();
    }
}
