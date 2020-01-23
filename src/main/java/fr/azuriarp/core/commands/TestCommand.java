package fr.azuriarp.core.commands;

import fr.azuriarp.api.AzuriaAPI;
import fr.azuriarp.api.commands.Command;
import fr.azuriarp.api.commands.RunnableCommand;
import org.bukkit.entity.Player;

public class TestCommand extends Command implements RunnableCommand {

    private final AzuriaAPI api;

    public TestCommand(AzuriaAPI api) {
        super(api);
        this.api = api;
    }

    public void run(Player player, String[] args) {
        player.sendMessage("Test");
    }
}
