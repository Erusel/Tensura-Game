package fr.erusel.tssdkuhc.inventorys;

import fr.erusel.tssdkuhc.Main;
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
                addItem(new ItemBuilder(Material.BLUE_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).build());
            } else if (skill instanceof ActiveSkill) {
                ItemBuilder itemBuilder = new ItemBuilder(Material.RED_WOOL).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).addLore("§2--------------------").addLore(skill.getSkillTier().getText()).addLore("§7Clique gauche pour utiliser");
                if (skill.inCooldown()) itemBuilder.addLore("§cCooldown : " + skill.getCurrentCooldown() + " seconde");
                addItem(itemBuilder.build(), e -> activeSkillUse(skill));
            }else {
                addItem(new ItemBuilder(Material.BARRIER).name("§6" + skill.getName()).addLore("§7" + skill.getLore()).build());
            }
        }


    }

    private void activeSkillUse(Skill skill){
        if (skill.inCooldown()){
            player.sendMessage("En cooldown : " + skill.getCurrentCooldown() + " seconde.");
            return;
        }
        player.closeInventory();
        ((ActiveSkill)skill).onUse(player);
        skill.activateCooldown();
    }

}
