package fr.erusel.tssdkuhc.inventorys.skills;

import fr.erusel.tssdkuhc.inventorys.VictimInventoryGUI;
import fr.erusel.tssdkuhc.inventorys.VictimSkillGUI;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class FaustChooseGUI extends FastInv {

    public FaustChooseGUI(Skill skill, Player victim, Player player) {
        super(27, "Faust | Choose the category");

        setItems(getBorders(), new ItemBuilder(Material.BLACK_STAINED_GLASS).build());
        setItem(11, new ItemBuilder(Material.CHEST).name("Inventory").build(), e -> inventory(skill, victim, player));
        setItem(13, new ItemBuilder(Material.ORANGE_WOOL).name("Skills").build());
    }


    private void inventory(Skill skill, Player victim, Player player){
        new VictimInventoryGUI(victim).open(player);
        skill.activateCooldown();
    }




    private void skills(Skill skill, Player victim, Player player){
        new VictimSkillGUI(victim).open(player);
    }


}
