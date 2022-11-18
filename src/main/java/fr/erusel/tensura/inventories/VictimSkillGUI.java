package fr.erusel.tensura.inventories;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class VictimSkillGUI extends FastInv {


    public VictimSkillGUI(Player victim) {
        super(27, victim.getName() + "'s Skills");

        for (Skill skill : Main.getInstance().getPlayerManager().getGPlayerByUUID(victim.getUniqueId()).getPlayerSkills()){
            if (skill instanceof PassiveSkill){
                if (skill.getSkillTier().equals(SkillTier.ULTIMATE)) {
                    addItem(new ItemBuilder(Material.ORANGE_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).build());
                }else {
                    addItem(new ItemBuilder(Material.BLUE_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).build());
                }
            } else if (skill instanceof ActiveSkill) {
                ItemBuilder itemBuilder;
                if (skill.getSkillTier().equals(SkillTier.ULTIMATE)) {
                    itemBuilder = new ItemBuilder(Material.ORANGE_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).addLore("§7Left Click to use");
                }else {
                    itemBuilder = new ItemBuilder(Material.RED_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).addLore("§7Left Click to use");
                }

                if (skill.inCooldown()) itemBuilder.addLore("§cCooldown : " + skill.getCurrentCooldown() + " seconds");
                addItem(itemBuilder.build());
            }else {
                addItem(new ItemBuilder(Material.BARRIER).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).build());
            }
        }


    }
}
