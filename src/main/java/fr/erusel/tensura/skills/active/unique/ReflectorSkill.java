package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.ReflectorRunnable;
import org.bukkit.entity.Player;

public class ReflectorSkill extends Skill implements ActiveSkill {


    public ReflectorSkill() {
        super("Reflector", "Reflect melee attack from opponents for 10 sec", SkillTier.UNIQUE, 800, null);
    }

    @Override
    public void onUse(Player player) {
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setReflector(true);
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setReflectorTime(10);
        new ReflectorRunnable(Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId())).runTaskTimer(Main.getInstance(), 0, 20);
        activateCooldown();
    }
}
