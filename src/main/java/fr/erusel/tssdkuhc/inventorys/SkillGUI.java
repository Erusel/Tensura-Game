package fr.erusel.tssdkuhc.inventorys;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.PassiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SkillGUI extends FastInv {

    Player player;


    public SkillGUI(Player player) {
        super(27, "Your Skills | " + player.getName());
        this.player = player;

        for (Skill skill : Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getPlayerSkills()){
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
                addItem(itemBuilder.build(), e -> activeSkillUse(skill));
            }else {
                addItem(new ItemBuilder(Material.BARRIER).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).build());
            }
        }


    }

    private void activeSkillUse(Skill skill){
        if (skill.inCooldown()){
            player.sendMessage("In cooldown : " + skill.getCurrentCooldown() + " seconds.");
            return;
        }
        player.closeInventory();
        ((ActiveSkill)skill).onUse(player);
    }

}
