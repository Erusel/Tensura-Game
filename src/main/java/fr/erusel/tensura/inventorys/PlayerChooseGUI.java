package fr.erusel.tensura.inventorys;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.inventorys.skills.FaustChooseGUI;
import fr.erusel.tensura.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class PlayerChooseGUI extends FastInv {

    public PlayerChooseGUI(Skill skill) {
        super(45, skill.getName());

        for (UUID uuid : Main.getInstance().getGameManager().getPlayerList()){
            if (Bukkit.getPlayer(uuid) == null) continue;
            Player p = Bukkit.getPlayer(uuid);
            if (!Main.getInstance().getPlayerManager().getGPlayerByUUID(uuid).haveSkill(Skills.INVESTIGATORRESISTANT)) {
                addItem(new ItemBuilder(Material.PLAYER_HEAD).name("§7" + p.getName()).skullmeta(p.getName()).build(), e -> playerChoosed(p, e, skill));
            }

        }

    }


    public void playerChoosed(Player victim, InventoryClickEvent event, Skill skill) {
        Player player = (Player) event.getWhoClicked();
        if (skill.getName().equals(Skills.INVESTIGATOR.getSkillName())){
            skill.activateCooldown();
            new VictimInventoryGUI(victim).open(player);
        }
        if (skill.getName().equals(Skills.FAUST.getSkillName())){
            new FaustChooseGUI(skill, victim, player).open(player);
        }
    }
}
