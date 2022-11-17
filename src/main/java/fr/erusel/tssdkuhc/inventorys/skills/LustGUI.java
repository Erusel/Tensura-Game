package fr.erusel.tssdkuhc.inventorys.skills;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.Skills;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class LustGUI extends FastInv {

    Player p;

    public LustGUI(Skill skill, Player p) {
        super(45, "Lust");

        for (UUID uuid : Main.getInstance().getGameManager().getDeadPlayers()){
            if (Bukkit.getPlayer(uuid) == null) continue;
            Player pl = Bukkit.getPlayer(uuid);
            addItem(new ItemBuilder(Material.PLAYER_HEAD).name("§7" + p.getName()).skullmeta(p.getName()).build(), e -> resurrect(pl, skill, p));


        }

    }


    public void resurrect(Player player, Skill skill, Player p){
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).ressurect();
        skill.activateCooldown();
        p.closeInventory();
    }

}
