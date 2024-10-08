package fr.erusel.tensura.skills.passive.resistance;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.event.entity.EntityDamageEvent;

public class DrowningResistantSkill extends Skill implements PassiveSkill, Eventable {

    public DrowningResistantSkill() {
        super("Drowning Resistant", Skills.DROWNINGRESISTANT, SkillScope.OBTAINABLE,  SkillTier.RESISTANCE, 0, null);
        super.addLore("Ignore Drowning damage");
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        if (!event.getCause().equals(EntityDamageEvent.DamageCause.DROWNING)) {
            return;
        }
        event.setCancelled(true);
    }
}
