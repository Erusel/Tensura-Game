package fr.erusel.tensura.skills.passive.resistance;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ArrowResistantSkill extends Skill implements PassiveSkill, Eventable {

    public ArrowResistantSkill() {
        super("Arrow Resistant", Skills.ARROWRESISTANT, SkillScope.OBTAINABLE,  SkillTier.RESISTANCE, 0, null);
        super.addLore("Ignore Arrow damage");

    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Arrow)) {
            return;
        }
        event.setCancelled(true);
    }
}