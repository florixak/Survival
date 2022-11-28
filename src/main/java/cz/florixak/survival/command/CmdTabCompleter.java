package cz.florixak.survival.command;

import cz.florixak.survival.Survival;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class CmdTabCompleter implements org.bukkit.command.TabCompleter {

    private FileConfiguration guilds, locations;

    public CmdTabCompleter(Survival plugin) {
        plugin.getCommand("guild").setTabCompleter(this);
        plugin.getCommand("warp").setTabCompleter(this);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> options = new ArrayList<>();

        if (command.getName().equalsIgnoreCase("guild")) {
            if (args.length == 1) {

                options.add("create");
                options.add("join");
                options.add("leave");
                options.add("invite");
                options.add("accept");
                options.add("kick");
                options.add("promote");
                options.add("list");
                options.add("disband");

                return options;
            }
            else if (args.length == 2) {

                if (args[1].equalsIgnoreCase("join")) {
                    if (guilds.getConfigurationSection("guilds") != null) {
                        for (String option : guilds.getConfigurationSection("guilds").getKeys(false)) {
                            if (option != null) options.add(option);
                        }
                    }
                }
                return options;
            }
        }
        if (command.getName().equalsIgnoreCase("warp")) {
            if (args.length == 1) {
                if (sender.hasPermission("sal.warp")) {
                    options.add("set");
                    options.add("delete");
                }
                options.add("list");
                if (locations.getConfigurationSection("warps") != null) {
                    for (String option : locations.getConfigurationSection("warps").getKeys(false)) {
                        if (option != null) options.add(option);
                    }
                }

                return options;
            }
            else if (args.length == 2) {

                if (args[1].equalsIgnoreCase("delete")) {
                    if (locations.getConfigurationSection("warps") != null) {
                        for (String option : locations.getConfigurationSection("guilds").getKeys(false)) {
                            if (option != null) options.add(option);
                        }
                    }
                }
                return options;
            }
        }


        return null;
    }
}
