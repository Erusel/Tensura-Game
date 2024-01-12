package fr.erusel.tensura.skills.passive.resistance;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.event.entity.EntityDamageEvent;

public class FireResistantSkill extends Skill implements PassiveSkill, Eventable {

    public FireResistantSkill() {
        super("Fire Resistant", Skills.FIRERESISTANT, SkillScope.OBTAINABLE,  SkillTier.RESISTANCE, 0, null);
        super.addLore("Ignore Fire damage");
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event) {

        EntityDamageEvent.DamageCause fire = EntityDamageEvent.DamageCause.FIRE;
        EntityDamageEvent.DamageCause fireTick = EntityDamageEvent.DamageCause.FIRE_TICK;

        if (!event.getCause().equals(fire) && !event.getCause().equals(fireTick)) {
            return;
        }
        event.setCancelled(true);
    }
}
