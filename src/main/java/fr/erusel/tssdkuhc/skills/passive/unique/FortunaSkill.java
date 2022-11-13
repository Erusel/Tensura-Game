package fr.erusel.tssdkuhc.skills.passive.unique;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.enums.Skills;
import fr.erusel.tssdkuhc.objects.PassiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.erusel.tssdkuhc.skills.passive.ultimate.HecateSkill;
import fr.erusel.tssdkuhc.skills.passive.ultimate.PloutosSkill;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class FortunaSkill extends Skill implements PassiveSkill {

    public FortunaSkill() {
        super("Fortuna", "Double mined ores", SkillTier.UNIQUE, 0, PloutosSkill.class);
    }

    Material[] ores = {Material.IRON_ORE, Material.COAL_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE, Material.EMERALD_ORE, Material.DIAMOND_ORE, Material.GOLD_ORE};

    @Override
    public void eachSecond(Player player) {
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
        if (Arrays.stream(ores).anyMatch(event.getBlock().getType()::equals)) {
            Block block = event.getBlock();
            event.setDropItems(false);
            for (ItemStack item: block.getDrops()) {
                item.setAmount(item.getAmount()*2);
                block.getWorld().dropItemNaturally(block.getLocation(), item);
                }
            }
        }
    }
