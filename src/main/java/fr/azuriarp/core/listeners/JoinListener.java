package fr.azuriarp.core.listeners;

import fr.azuriarp.api.AzuriaAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final AzuriaAPI api;

    public JoinListener(AzuriaAPI api) {
        this.api = api;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        player.sendMessage("Bienvenue " + player.getName());
    }
}
