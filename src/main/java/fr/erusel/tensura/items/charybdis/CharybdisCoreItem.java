package fr.erusel.tensura.items.charybdis;

import fr.erusel.tensura.objects.GItem;
import fr.erusel.tensura.objects.GameElement;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class CharybdisCoreItem extends GameElement implements GItem {

    @Override
    public void onUseAtEntity(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof Player victim)){
            return;
        }

    }

    @Override
    public ItemStack getItemstack(){
        ItemBuilder itemBuilder = new ItemBuilder(Material.HEART_OF_THE_SEA);
        itemBuilder.name("§6Charybdis Core");
        itemBuilder.addLore("§7bouhhh");

        return itemBuilder.build();
    }




}
