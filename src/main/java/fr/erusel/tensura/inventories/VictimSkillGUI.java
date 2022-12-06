package fr.erusel.tensura.inventories;

import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class VictimSkillGUI extends FastInv {


    public VictimSkillGUI(Player victim) {
        super(27, victim.getName() + "'s Skills");
        GPlayer gPlayer = getPlayerManager().getGPlayerByUUID(victim.getUniqueId());

        // Ultimate Skills
        for (Skill skill : gPlayer.getPlayerUltimateSkills()){
            if (skill instanceof PassiveSkill){
                addItem(new ItemBuilder(Material.ORANGE_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).build());
            } else if (skill instanceof ActiveSkill) {
                ItemBuilder itemBuilder;
                itemBuilder = new ItemBuilder(Material.ORANGE_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText());
                addItem(itemBuilder.build());
            }else {
                addItem(new ItemBuilder(Material.BARRIER).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).build());
            }
        }

        // Unique Skill
        for (Skill skill : gPlayer.getPlayerUniqueSkills()){
            if (skill instanceof PassiveSkill){
                addItem(new ItemBuilder(Material.BLUE_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).build());
            } else if (skill instanceof ActiveSkill) {
                ItemBuilder itemBuilder;
                itemBuilder = new ItemBuilder(Material.RED_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText());
                addItem(itemBuilder.build());
            }else {
                addItem(new ItemBuilder(Material.BARRIER).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).build());
            }
        }

        // Extra Skill
        for (Skill skill : gPlayer.getPlayerExtraSkill()){
            if (skill instanceof PassiveSkill){
                addItem(new ItemBuilder(Material.YELLOW_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).build());
            } else if (skill instanceof ActiveSkill) {
                ItemBuilder itemBuilder;
                itemBuilder = new ItemBuilder(Material.YELLOW_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText());
                addItem(itemBuilder.build());
            }else {
                addItem(new ItemBuilder(Material.BARRIER).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).build());
            }
        }

        // Resistance Skill
        for (Skill skill : gPlayer.getPlayerResistance()){
            addItem(new ItemBuilder(Material.GREEN_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).build());
        }


    }
}
