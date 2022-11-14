package fr.erusel.tssdkuhc.inventorys;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.GItem;
import fr.erusel.tssdkuhc.objects.PassiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ItemGUI extends FastInv {

    Player player;


    public ItemGUI(Player player) {
        super(27, "Your Items | " + player.getName());
        this.player = player;

        for (GItem item : Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getPlayerItems()){
            addItem(item.getItemstack(), e -> item.onUse());
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
