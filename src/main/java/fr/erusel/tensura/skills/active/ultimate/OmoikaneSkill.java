package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.ReflectorRunnable;
import org.bukkit.entity.Player;

public class OmoikaneSkill extends Skill implements ActiveSkill {


    public OmoikaneSkill() {
        super("Omoikane, Lord of Reflection", Skills.OMOIKANE, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 1100, null);
        super.addLore("Return the damage you take to the attacker for 30 seconds");
    }

    @Override
    public void onUse(Player player) {
        GPlayer gPlayer = getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        gPlayer.setReflector(true);
        new ReflectorRunnable(gPlayer, 30)
                .runTaskTimer(getMain(), 0, 20);
        activateCooldown();
    }
}
