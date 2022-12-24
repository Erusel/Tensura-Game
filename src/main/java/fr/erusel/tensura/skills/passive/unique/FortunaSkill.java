package fr.erusel.tensura.skills.passive.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class FortunaSkill extends Skill implements PassiveSkill, Eventable {

    public FortunaSkill() {
        super("Fortuna", Skills.FORTUNA, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 0, Skills.PLOUTOS);
        super.addLore("Lore TODO");
    }

    Material[] ores = {Material.IRON_ORE, Material.COAL_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE, Material.EMERALD_ORE, Material.DIAMOND_ORE, Material.GOLD_ORE};

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        if (Arrays.asList(ores).contains(event.getBlock().getType())) {
            Block block = event.getBlock();
            event.setDropItems(false);
            for (ItemStack item: block.getDrops()) {
                item.setAmount(item.getAmount()*2);
                block.getWorld().dropItemNaturally(block.getLocation(), item);
                }
            }
        }
    }
