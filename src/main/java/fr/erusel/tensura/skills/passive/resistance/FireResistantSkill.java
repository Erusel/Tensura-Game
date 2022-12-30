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
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
            event.setCancelled(true);
        }
    }

}
