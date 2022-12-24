package fr.erusel.tensura.inventories;

import fr.erusel.tensura.objects.*;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

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
                        ItemBuilder itemBuilder = new ItemBuilder(Material.ORANGE_WOOL)
                                .name("§6" + skill.getName());
                        for (String string : skill.getLore()){
                            itemBuilder.addLore("§7" + string);
                        }
                        itemBuilder.addLore("§2--------------------")
                                .addLore(skill.getSkillTier().getText());
                        addItem(itemBuilder.build());
                    } else if (skill instanceof ActiveSkill) {
                        ItemBuilder itemBuilder;
                        itemBuilder = new ItemBuilder(Material.ORANGE_WOOL)
                                .name("§6" + skill.getName());
                        for (String string : skill.getLore()){
                            itemBuilder.addLore("§7" + string);
                        }
                        itemBuilder.addLore("§2--------------------")
                                .addLore(skill.getSkillTier().getText())
                                .addLore("§7Left Click to use");

                        if (skill.inCooldown()) {
                            itemBuilder.addLore("§cCooldown : " + skill.getCurrentCooldown() + " seconds");
                        }
                        addItem(itemBuilder.build(), e -> activeSkillUse(skill));
                    }
                });

        // Unique Skill
        gPlayer.getPlayerUniqueSkills()
                .forEach(skill -> {
                    if (skill instanceof PassiveSkill){
                        ItemBuilder itemBuilder = new ItemBuilder(Material.BLUE_WOOL)
                                .name("§6" + skill.getName());
                        for (String string : skill.getLore()){
                            itemBuilder.addLore("§7" + string);
                        }
                        itemBuilder.addLore("§2--------------------")
                                .addLore(skill.getSkillTier().getText());
                        addItem(itemBuilder.build());
                    } else if (skill instanceof ActiveSkill) {
                        ItemBuilder itemBuilder;
                        itemBuilder = new ItemBuilder(Material.RED_WOOL)
                                .name("§6" + skill.getName());
                        for (String string : skill.getLore()){
                            itemBuilder.addLore("§7" + string);
                        }
                        itemBuilder.addLore("§2--------------------")
                                .addLore(skill.getSkillTier().getText())
                                .addLore("§7Left Click to use");
                        if (skill.inCooldown()) {
                            itemBuilder.addLore("§cCooldown : " + skill.getCurrentCooldown() + " seconds");
                        }
                        addItem(itemBuilder.build(), e -> activeSkillUse(skill));
                    }else {
                        addItem(new ItemBuilder(Material.BARRIER).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).build());
                    }
                });

        // Extra Skill
        gPlayer.getPlayerExtraSkill()
                .forEach(skill -> {
                    if (skill instanceof ExtraSkill extraSkill) {
                        ItemBuilder itemBuilder;
                        itemBuilder = new ItemBuilder(Material.YELLOW_WOOL).name("§6" + skill.getName());
                        for (String string : skill.getLore()){
                            itemBuilder.addLore("§7" + string);
                        }
                        itemBuilder.addLore("§2--------------------")
                                .addLore("§6Left Click ->§7 " + extraSkill.getLeftClickSkillLore())
                                .addLore("§6Right Click ->§7 " + extraSkill.getRightClickSkillLore())
                                .addLore("§2--------------------")
                                .addLore(skill.getSkillTier().getText());
                        if (skill.inCooldown()) {
                            itemBuilder.addLore("§cCooldown : " + skill.getCurrentCooldown() + " seconds");
                        }
                        addItem(itemBuilder.build(), e -> extraSkillUse(e, skill));
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

    private void extraSkillUse(InventoryClickEvent event, Skill skill){
        if (skill.inCooldown()){
            player.sendMessage("In cooldown : " + skill.getCurrentCooldown() + " seconds.");
            player.closeInventory();
            return;
        }
        if (event.isLeftClick()){
            ((ExtraSkill)skill).onLeftClick(player);
            player.closeInventory();
            return;
        }
        if (event.isRightClick()){
            ((ExtraSkill)skill).onRightClick(player);
            player.closeInventory();
        }
    }

}
