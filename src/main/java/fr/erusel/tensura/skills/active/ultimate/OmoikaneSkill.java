package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.ReflectorRunnable;
import org.bukkit.entity.Player;

public class OmoikaneSkill extends Skill implements ActiveSkill {


    public OmoikaneSkill() {
        super("Omoikane, Lord of Reflection", "Reflect melee damage & cancel arrow damage for 30s", Skills.OMOIKANE, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 1100, null);
    }

    @Override
    public void onUse(Player player) {
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setReflector(true);
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setReflectorTime(30);
        new ReflectorRunnable(getPlayerManager().getGPlayerByUUID(player.getUniqueId())).runTaskTimer(Main.getInstance(), 0, 20);
        activateCooldown();
    }
}
