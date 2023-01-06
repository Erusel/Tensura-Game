package fr.erusel.tensura.skills.passive.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MogisSkill extends Skill implements PassiveSkill, Eventable {


    public MogisSkill() {
        super("Mogis, Lord of Berserk", Skills.MOGIS, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 0, null);
        super.addLore("Gain speed I and strength II for 20 seconds if you killed a player");
    }

    @Override
    public void eachSecond(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0));
    }

    @Override
    public void onPlayerKill(Player killer, Player deadPlayer) {
        killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 1));
    }


}
