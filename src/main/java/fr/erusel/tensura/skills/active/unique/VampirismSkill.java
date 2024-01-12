package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.VampirismRunnable;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class VampirismSkill extends Skill implements ActiveSkill, Eventable {

    public VampirismSkill() {
        super("Vampirism", Skills.VAMPIRISM, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 600, Skills.DRACULA);
        super.addLore("Heal yourself by hitting a player");
    }

    @Override
    public void onUse(Player player) {
        player.sendMessage("§6Vampirism activated for 10 seconds");
        GPlayer gPlayer = getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        gPlayer.setVampirism(true);
        new VampirismRunnable(gPlayer, 10)
                .runTaskTimer(getMain(), 0, 20);
        activateCooldown();
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player player)) {
            return;
        }
        if (!(event.getEntity() instanceof Player)){
            return;
        }
        if (!getPlayerManager().getGPlayerByUUID(player.getUniqueId()).isVampirism()) {
            return;
        }
        player.setHealth(player.getHealth() + event.getDamage()/2);
    }
}
