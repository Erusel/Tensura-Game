package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.VampirismRunnable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DraculaSkill extends Skill implements ActiveSkill, Eventable {

    public DraculaSkill() {
        super("Dracula, Lord of vampirism",  Skills.ALBERT, SkillScope.OBTAINABLE, SkillTier.ULTIMATE, 800, null);
        super.addLore("Heal yourself when you hit player and a bit on mobs, regen full life if player is killed");
    }

    @Override
    public void onUse(Player player) {
        GPlayer gPlayer = getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        player.sendMessage("§6Dracula activated for 20 seconds");
        gPlayer.setVampirism(true);
        new VampirismRunnable(gPlayer, 20)
                .runTaskTimer(getMain(), 0, 20);
        activateCooldown();
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) {
            return;
        }
        if (event.getEntity() instanceof Player) {
            double damage = event.getDamage();
            player.setHealth(player.getHealth() + damage);
        } else {
            double damage = event.getDamage();
            player.setHealth(player.getHealth() + damage/2);
        }
    }

    @Override
    public void onPlayerKill(Player killer, Player deadPlayer) {
        if (getPlayerManager().getGPlayerByUUID(killer.getUniqueId()).isVampirism()) {
            killer.setHealth(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        }
    }
}
