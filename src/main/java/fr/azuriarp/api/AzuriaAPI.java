package fr.azuriarp.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.azuriarp.api.commands.Command;
import fr.azuriarp.api.data.DatabaseCredentials;
import fr.azuriarp.api.data.DatabaseManager;
import fr.azuriarp.core.listeners.JoinListener;
import fr.azuriarp.core.listeners.QuitListener;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.SortedMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AzuriaAPI extends JavaPlugin {

    private Logger logger;

    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        this.logger = this.getLogger();

        Configuration configuration = this.getConfig();

        String host = configuration.getString("sql-host");
        String username = configuration.getString("sql-username");
        String password = configuration.getString("sql-password");
        String dbName = configuration.getString("sql-dbname");
        int port = configuration.getInt("sql-port");

        this.databaseManager = new DatabaseManager(new DatabaseCredentials(host, username, password, dbName, port));

        // Commands register
        Gson gson = new Gson();

        Type hashType = new TypeToken<SortedMap<String, Command>>() {}.getType();

        try {
            System.out.println(this.getDataFolder().getAbsolutePath());
            SortedMap<String, Command> commands = gson.fromJson(new FileReader(new File(this.getDataFolder(), "commands.json")), hashType);

            for (Map.Entry<String, Command> entry : commands.entrySet()) {
                String name = entry.getKey();
                Command command = entry.getValue();

                final String ANCHOR = "commands." + name + ".";

                // Register the command in the plugin.yml
                configuration.set(ANCHOR + "description", command.getDescription());
                configuration.set(ANCHOR + "aliases", command.getAliases());

                this.getCommand(name).setExecutor(command);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            this.log(Level.SEVERE, "Command file could not be found");
            //this.getServer().shutdown();
        }

        this.registerListeners();

        this.log("Plugin has successfully enabled");
    }

    @Override
    public void onDisable() {
        this.databaseManager.closePool(); // Close connection
    }

    public DatabaseManager getDatabaseManager() {
        return this.databaseManager;
    }

    public void log(String message) {
        this.log(Level.INFO, message);
    }

    public void log(Level level, String message) {
        this.logger.log(level, message);
    }

    private void registerListeners() {
        PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvents(new JoinListener(this), this);
        pm.registerEvents(new QuitListener(this), this);
    }
}
