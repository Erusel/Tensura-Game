package fr.erusel.tensura.inventories;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.GItem;
import fr.erusel.tensura.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
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
