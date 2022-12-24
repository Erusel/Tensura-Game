package fr.erusel.tensura.skills.passive.resistance;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.event.entity.EntityDamageEvent;

public class LavaResistantSkill extends Skill implements PassiveSkill, Eventable {

    public LavaResistantSkill() {
        super("Lava Resistant", Skills.LAVARESISTANT, SkillScope.OBTAINABLE,  SkillTier.RESISTANCE, 0, null);
        super.addLore("Lore TODO");
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause().equals(EntityDamageEvent.DamageCause.LAVA)) {
            event.setCancelled(true);
        }
    }


}
