package fr.erusel.tensura.skills.passive.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.utils.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class PloutosSkill extends Skill implements PassiveSkill, Eventable {

    public PloutosSkill() {
        super("Ploutos, Lord of Wealth", Skills.PLOUTOS, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 0, null);
        super.addLore("Triple the drops from ores");
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {

        Material[] ores = Utils.ores;

        if (!Arrays.asList(ores).contains(event.getBlock().getType())) {
            return;
        }

        Block block = event.getBlock();
        event.setDropItems(false);

        for (ItemStack item : block.getDrops()) {
            item.setAmount(item.getAmount()*3);
            block.getWorld().dropItemNaturally(block.getLocation(), item);
        }
    }
}
