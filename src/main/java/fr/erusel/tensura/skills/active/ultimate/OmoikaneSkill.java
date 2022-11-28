package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.OppressorRunnable;
import fr.erusel.tensura.threads.ReflectorRunnable;
import org.bukkit.entity.Player;

public class OmoikaneSkill extends Skill implements ActiveSkill {


    public OmoikaneSkill() {
        super("Omoikane, Lord of Reflection", "Reflect melee damage & cancel arrow damage for 30s", SkillTier.ULTIMATE, 1100, null);
    }

    @Override
    public void onUse(Player player) {
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setReflector(true);
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setReflectorTime(30);
        new ReflectorRunnable(Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId())).runTaskTimer(Main.getInstance(), 0, 20);
        activateCooldown();
    }
}
