package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.OppressorRunnable;
import org.bukkit.entity.Player;

public class OppressorSkill extends Skill implements ActiveSkill {


    public OppressorSkill() {
        super("Oppressor", Skills.OPPRESSOR, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 800, Skills.JANUS);
        super.addLore("Lore TODO");
    }

    @Override
    public void onUse(Player player) {
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setOppressor(true);
        new OppressorRunnable(getPlayerManager().getGPlayerByUUID(player.getUniqueId()), 30)
                .runTaskTimer(getMain(), 0, 20);
        activateCooldown();
    }
}
