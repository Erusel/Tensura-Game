package fr.erusel.tensura.skills.passive.resistance;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallResistantSkill extends Skill implements PassiveSkill, Eventable {

    public FallResistantSkill() { super("Fall Resistant", "Cancel Fall Damage", Skills.FALLRESISTANT, SkillScope.OBTAINABLE,  SkillTier.RESISTANCE, 0, null);}

    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            event.setCancelled(true);
        }
    }

}

