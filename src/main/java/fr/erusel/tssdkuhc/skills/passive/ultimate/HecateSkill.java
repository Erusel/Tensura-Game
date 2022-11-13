package fr.erusel.tssdkuhc.skills.passive.ultimate;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.inventorys.InvestigatorGUI;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.PassiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HecateSkill extends Skill implements PassiveSkill {


    public HecateSkill() {
        super("Hecate, Lord of Protection", "Grant you resistance 2", SkillTier.ULTIMATE, 900, null);
    }

    @Override
    public void eachSecond(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 1));
    }

    @Override
    public void onDeath(PlayerDeathEvent event) {
    }

    @Override
    public void onKill(Player killer, Player deadPlayer) {
    }

    @Override
    public void onDamage(EntityDamageEvent event) {
    }

    @Override
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
    }
}

