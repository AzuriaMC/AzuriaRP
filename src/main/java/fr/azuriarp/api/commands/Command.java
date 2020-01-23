package fr.azuriarp.api.commands;

import fr.azuriarp.api.AzuriaAPI;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Command implements CommandExecutor {

    private AzuriaAPI api;

    private String name;
    private String description;
    private String[] aliases;
    private String permission;

    private HashMap<String, CommandUsage> usages;

    public Command(AzuriaAPI api) {
        this.api = api;
    }

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;

            ((RunnableCommand) this).run(player, args);
        } else {
            sender.sendMessage("Only players can execute a command");
        }

        return true;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }
}
