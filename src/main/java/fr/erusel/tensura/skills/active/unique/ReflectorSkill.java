package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.OmoikaneSkill;
import fr.erusel.tensura.threads.ReflectorRunnable;
import org.bukkit.entity.Player;

public class ReflectorSkill extends Skill implements ActiveSkill {


    public ReflectorSkill() {
        super("Reflector", "Reflect melee attack for 10 s", Skills.REFLECTOR, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 800, OmoikaneSkill.class);
    }

    @Override
    public void onUse(Player player) {
        PlayerManager.getInstance().getGPlayerByUUID(player.getUniqueId()).setReflector(true);
        PlayerManager.getInstance().getGPlayerByUUID(player.getUniqueId()).setReflectorTime(10);
        new ReflectorRunnable(PlayerManager.getInstance().getGPlayerByUUID(player.getUniqueId())).runTaskTimer(Main.getInstance(), 0, 20);
        activateCooldown();
    }
}
