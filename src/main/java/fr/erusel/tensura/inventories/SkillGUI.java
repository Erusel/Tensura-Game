package fr.erusel.tensura.inventories;

import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.GPlayer;
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
        GPlayer gPlayer = getPlayerManager().getGPlayerByUUID(player.getUniqueId());

        // Ultimate Skills
        gPlayer.getPlayerUltimateSkills()
                .forEach(skill -> {
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
                });

        // Unique Skill
        gPlayer.getPlayerUniqueSkills()
                .forEach(skill -> {
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
                });

        // Extra Skill
        gPlayer.getPlayerExtraSkill()
                .forEach(skill -> {
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
                });


        // Resistance Skill
        gPlayer.getPlayerResistance()
                .forEach(skill -> addItem(new ItemBuilder(Material.GREEN_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).build()));


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
