package fr.erusel.tensura.inventories;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SkillGUI extends FastInv {

    Player player;


    public SkillGUI(Player player) {
        super(27, "Your Skills | " + player.getName());
        this.player = player;

        // Ultimate Skills
        for (Skill skill : Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getPlayerUltimateSkills()){
            if (skill instanceof PassiveSkill){
                addItem(new ItemBuilder(Material.ORANGE_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).build());
            } else if (skill instanceof ActiveSkill) {
                ItemBuilder itemBuilder;
                itemBuilder = new ItemBuilder(Material.ORANGE_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).addLore("§7Left Click to use");

                if (skill.inCooldown()) itemBuilder.addLore("§cCooldown : " + skill.getCurrentCooldown() + " seconds");
                addItem(itemBuilder.build(), e -> activeSkillUse(skill));
            }else {
                addItem(new ItemBuilder(Material.BARRIER).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).build());
            }
        }

        // Unique Skill
        for (Skill skill : Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getPlayerUniqueSkills()){
            if (skill instanceof PassiveSkill){
                addItem(new ItemBuilder(Material.BLUE_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).build());
            } else if (skill instanceof ActiveSkill) {
                ItemBuilder itemBuilder;
                    itemBuilder = new ItemBuilder(Material.RED_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).addLore("§7Left Click to use");
                if (skill.inCooldown()) itemBuilder.addLore("§cCooldown : " + skill.getCurrentCooldown() + " seconds");
                addItem(itemBuilder.build(), e -> activeSkillUse(skill));
            }else {
                addItem(new ItemBuilder(Material.BARRIER).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).build());
            }
        }

        // Extra Skill
        for (Skill skill : Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getPlayerExtraSkill()){
            if (skill instanceof PassiveSkill){
                addItem(new ItemBuilder(Material.YELLOW_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).build());
            } else if (skill instanceof ActiveSkill) {
                ItemBuilder itemBuilder;
                itemBuilder = new ItemBuilder(Material.YELLOW_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).addLore("§7Left Click to use");
                if (skill.inCooldown()) itemBuilder.addLore("§cCooldown : " + skill.getCurrentCooldown() + " seconds");
                addItem(itemBuilder.build(), e -> activeSkillUse(skill));
            }else {
                addItem(new ItemBuilder(Material.BARRIER).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).build());
            }
        }


    }

    private void activeSkillUse(Skill skill){
        if (skill.inCooldown()){
            player.sendMessage("In cooldown : " + skill.getCurrentCooldown() + " seconds.");
            player.closeInventory();
            return;
        }
        player.closeInventory();
        ((ActiveSkill)skill).onUse(player);
    }

}
