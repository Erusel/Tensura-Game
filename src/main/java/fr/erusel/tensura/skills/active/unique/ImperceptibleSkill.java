package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.HadesSkill;
import fr.erusel.tensura.threads.skills.ImperceptibleRunnable;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ImperceptibleSkill extends Skill implements ActiveSkill {


    public ImperceptibleSkill() {
        super("Imperceptible", "Become invisible for 30 seconds but you can't attack", Skills.IMPERCEPTIBLE, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 600, HadesSkill.class);
    }

    @Override
    public void onUse(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 0, false, true));
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).
                setImperceptible(true);
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).
                setImperceptibleTime(30);
        new ImperceptibleRunnable(PlayerManager.getInstance().getGPlayerByUUID(player.getUniqueId()))
                .runTaskTimer(Main.getInstance(), 0, 20);
        activateCooldown();
    }
}