package fr.azuriarp.core.listeners;

import fr.azuriarp.api.AzuriaAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final AzuriaAPI api;

    public QuitListener(AzuriaAPI api) {
        this.api = api;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
    }
}
