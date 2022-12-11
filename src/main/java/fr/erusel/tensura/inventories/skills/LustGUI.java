package fr.erusel.tensura.inventories.skills;

import fr.erusel.tensura.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LustGUI extends FastInv {

    public LustGUI(Skill skill, Player p) {
        super(45, "Lust");

        getGameManager().getDeadPlayers().stream()
                .filter(uuid -> Bukkit.getPlayer(uuid) != null)
                .forEach(uuid -> addItem(new ItemBuilder(Material.PLAYER_HEAD).name("§7" + Bukkit.getPlayer(uuid).getName()).skullmeta(Bukkit.getPlayer(uuid).getName()).build(),
                        e -> resurrect(Bukkit.getPlayer(uuid), skill, p)));

    }


    public void resurrect(Player player, Skill skill, Player p){
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).ressurect();
        skill.activateCooldown();
        p.closeInventory();
    }

}
