package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.ReflectorRunnable;
import org.bukkit.entity.Player;

public class ReflectorSkill extends Skill implements ActiveSkill {


    public ReflectorSkill() {
        super("Reflector", "Reflect melee attack for 10 s", Skills.REFLECTOR, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 800, Skills.OMOIKANE);
    }

    @Override
    public void onUse(Player player) {
        GPlayer gPlayer = getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        gPlayer.setReflector(true);
        new ReflectorRunnable(gPlayer, 10)
                .runTaskTimer(getMain(), 0, 20);
        activateCooldown();
    }
}
