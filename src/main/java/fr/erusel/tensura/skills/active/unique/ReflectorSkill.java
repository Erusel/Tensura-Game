package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.OmoikaneSkill;
import fr.erusel.tensura.threads.ReflectorRunnable;
import org.bukkit.entity.Player;

public class ReflectorSkill extends Skill implements ActiveSkill {


    public ReflectorSkill() {
        super("Reflector", "Reflect melee attack for 10 s", SkillTier.UNIQUE, 800, OmoikaneSkill.class);
    }

    @Override
    public void onUse(Player player) {
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setReflector(true);
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setReflectorTime(10);
        new ReflectorRunnable(Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId())).runTaskTimer(Main.getInstance(), 0, 20);
        activateCooldown();
    }
}
