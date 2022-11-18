package fr.erusel.tensura.inventories.skills;

import fr.erusel.tensura.inventories.VictimInventoryGUI;
import fr.erusel.tensura.inventories.VictimSkillGUI;
import fr.erusel.tensura.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class FaustChooseGUI extends FastInv {

    public FaustChooseGUI(Skill skill, Player victim, Player player) {
        super(27, "Faust | Choose the category");

        setItems(getBorders(), new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
        setItem(11, new ItemBuilder(Material.CHEST).name("§6Inventory").build(), e -> inventory(skill, victim, player));
        setItem(13, new ItemBuilder(Material.ORANGE_WOOL).name("§6Skills").build(), e -> skills(skill, victim, player));
    }


    private void inventory(Skill skill, Player victim, Player player){
        new VictimInventoryGUI(victim).open(player);
        skill.activateCooldown();
    }




    private void skills(Skill skill, Player victim, Player player){
        new VictimSkillGUI(victim).open(player);
    }


}
