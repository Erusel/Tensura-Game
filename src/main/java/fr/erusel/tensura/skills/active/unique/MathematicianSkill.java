package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MathematicianSkill extends Skill implements ActiveSkill, Eventable {

    private final int DODGE = 3;

    public MathematicianSkill() {
        super("Mathematician", Skills.MATHEMATICIAN, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 1000, Skills.ALBERT);
        super.addLore("Lore TODO");
    }

    @Override
    public void onUse(Player player) {
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setMathematicianDodgeLeft(DODGE);
        activateCooldown();
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player damaged)){
            return;
        }
        if (getPlayerManager().getGPlayerByUUID(event.getDamager().getUniqueId()).getMathematicianDodgeLeft() >=1){
            event.setCancelled(true);
            getPlayerManager().getGPlayerByUUID(damaged.getUniqueId())
                    .setMathematicianDodgeLeft(getPlayerManager().getGPlayerByUUID(damaged.getUniqueId()).getMathematicianDodgeLeft()-1);
        }
    }
}
