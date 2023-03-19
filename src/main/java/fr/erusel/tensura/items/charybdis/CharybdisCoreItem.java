package fr.erusel.tensura.items.charybdis;

import fr.erusel.tensura.enums.Modes;
import fr.erusel.tensura.objects.GItem;
import fr.erusel.tensura.objects.GameElement;
import fr.erusel.tensura.threads.CharybdisationRunnable;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class CharybdisCoreItem extends GameElement implements GItem {

    @Override
    public void onUseAtEntity(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (!(event.getRightClicked() instanceof Player victim)){
            return;
        }
        if (!getGameManager().getGameMode().equals(Modes.CHARYBDIS)){
            return;
        }
        if (getTeamManager().getPlayerTeam(player.getUniqueId()).equals(getTeamManager().getPlayerTeam(victim.getUniqueId()))){
            player.sendMessage("Pas dans la meme team kasos");
            return;
        }
        if (getPlayerManager().getGPlayerByUUID(victim.getUniqueId()).isCharibdised()){
            player.sendMessage("deja entrain pd");
            return;
        }
        getPlayerManager().getGPlayerByUUID(victim.getUniqueId()).setCharibdised(true);
        new CharybdisationRunnable(getTeamManager().getPlayerTeam(player.getUniqueId()),victim.getUniqueId(), getPlayerManager(), getGameManager()).runTaskTimer(getMain(), 0, 20);
        event.getPlayer().getInventory().getItemInMainHand().setType(Material.AIR);
    }

    @Override
    public ItemStack getItemstack(){
        ItemBuilder itemBuilder = new ItemBuilder(Material.HEART_OF_THE_SEA);
        itemBuilder.name("§6Charybdis Core");
        itemBuilder.addLore("§7bouhhh");

        return itemBuilder.build();
    }




}
