package fr.azuriarp.api.commands;

import org.bukkit.entity.Player;

public interface RunnableCommand {

    void run(Player player, String[] args);
}
