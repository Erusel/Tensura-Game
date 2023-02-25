package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PersonalDimensionSkill extends Skill implements ActiveSkill {


    public PersonalDimensionSkill() {
        super("Personal dimension", Skills.PERSONALDIM, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 800, null);
        super.addLore("Teleport to a personal dimension for 5 minutes with a selected player");
    }

    @Override
    public void onUse(Player player) {
        player.teleport(Bukkit.getWorld("personal_dimension").getSpawnLocation());
        activateCooldown();
    }
}
