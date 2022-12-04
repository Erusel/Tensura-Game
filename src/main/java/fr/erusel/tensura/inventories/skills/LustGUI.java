package fr.erusel.tensura.inventories.skills;

import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LustGUI extends FastInv {

    Player p;

    public LustGUI(Skill skill, Player p) {
        super(45, "Lust");

        for (UUID uuid : GameManager.getInstance().getDeadPlayers()){
            if (Bukkit.getPlayer(uuid) == null) continue;
            Player pl = Bukkit.getPlayer(uuid);
            addItem(new ItemBuilder(Material.PLAYER_HEAD).name("§7" + p.getName()).skullmeta(p.getName()).build(), e -> resurrect(pl, skill, p));

        }

    }


    public void resurrect(Player player, Skill skill, Player p){
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).ressurect();
        skill.activateCooldown();
        p.closeInventory();
    }

}
