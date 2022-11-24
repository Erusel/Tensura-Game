package fr.erusel.tensura.managers;

import fr.erusel.tensura.objects.GPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    private final HashMap<UUID, GPlayer> GPlayers = new HashMap<>();

    // GPlayers
    public void createPlayerGPlayer(Player player){
        if (!GPlayers.containsKey(player.getUniqueId())) GPlayers.put(player.getUniqueId(), new GPlayer(player.getUniqueId()));
    }
    public void removePlayerGPlayer(Player player){
        GPlayers.remove(player.getUniqueId());
    }
    public GPlayer getGPlayerByUUID(UUID uuid){
        return GPlayers.get(uuid);
    }



}
