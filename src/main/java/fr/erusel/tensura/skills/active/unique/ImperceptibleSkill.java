package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.ImperceptibleRunnable;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ImperceptibleSkill extends Skill implements ActiveSkill {


    public ImperceptibleSkill() {
        super("Imperceptible", Skills.IMPERCEPTIBLE, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 600, Skills.HADES);
        super.addLore("Lore TODO");
    }

    @Override
    public void onUse(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 0, false, true));
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setImperceptible(true);
        new ImperceptibleRunnable(getPlayerManager().getGPlayerByUUID(player.getUniqueId()), 30)
                .runTaskTimer(getMain(), 0, 20);
        activateCooldown();
    }
}