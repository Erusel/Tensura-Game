package fr.erusel.tensura.skills.passive.resistance;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.event.entity.EntityDamageEvent;

public class ExplosionResistantSkill extends Skill implements PassiveSkill, Eventable {

    public ExplosionResistantSkill() {
        super("Explosion-Resistant", Skills.EXPLOSIONRESISTANT, SkillScope.OBTAINABLE,  SkillTier.RESISTANCE, 0, null);
        super.addLore("Ignore Explosion damage");
    }


    @Override
    public void onEntityDamage(EntityDamageEvent event) {

        EntityDamageEvent.DamageCause blockExplosion = EntityDamageEvent.DamageCause.BLOCK_EXPLOSION;
        EntityDamageEvent.DamageCause entityExplosion = EntityDamageEvent.DamageCause.ENTITY_EXPLOSION;

        if (!event.getCause().equals(blockExplosion) && !event.getCause().equals(entityExplosion)) {
            return;
        }
        event.setCancelled(true);
    }
}
