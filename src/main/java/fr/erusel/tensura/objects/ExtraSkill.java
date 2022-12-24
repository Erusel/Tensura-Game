package fr.erusel.tensura.objects;

import org.bukkit.entity.Player;

public interface ExtraSkill {

    default String getRightClickSkillLore(){
        return "None";
    }
    default String getLeftClickSkillLore(){
        return "None";
    }

    void onRightClick(Player player);
    void onLeftClick(Player player);

}
