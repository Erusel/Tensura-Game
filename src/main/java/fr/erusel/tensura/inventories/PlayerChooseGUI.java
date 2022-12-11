package fr.erusel.tensura.inventories;

import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.inventories.skills.FaustChooseGUI;
import fr.erusel.tensura.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerChooseGUI extends FastInv {

    public PlayerChooseGUI(Skill skill) {
        super(45, skill.getName());

        getGameManager().getPlayerList().stream()
                .filter(uuid -> Bukkit.getPlayer(uuid) != null)
                .forEach(uuid -> addItem(new ItemBuilder(Material.PLAYER_HEAD).name("§7" + Bukkit.getPlayer(uuid).getName()).skullmeta(Bukkit.getPlayer(uuid).getName()).build(),
                        e -> playerChoosed(Bukkit.getPlayer(uuid), e, skill)));

    }


    public void playerChoosed(Player victim, InventoryClickEvent event, Skill skill) {
        Player player = (Player) event.getWhoClicked();

        if (skill.isSkill(Skills.INVESTIGATOR)){
            skill.activateCooldown();
            new VictimInventoryGUI(victim).open(player);
            return;
        }

        if (skill.isSkill(Skills.FAUST)){
            new FaustChooseGUI(skill, victim, player).open(player);
        }
    }
}
