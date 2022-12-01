package cz.florixak.survival.command;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.commands.*;
import cz.florixak.survival.command.commands.admin.Arena;
import cz.florixak.survival.command.commands.admin.ClearInventory;
import cz.florixak.survival.command.commands.admin.GameMode;
import cz.florixak.survival.command.commands.admin.Reload;
import cz.florixak.survival.command.commands.home.DelHome;
import cz.florixak.survival.command.commands.home.Home;
import cz.florixak.survival.command.commands.home.Homes;
import cz.florixak.survival.command.commands.home.SetHome;
import cz.florixak.survival.command.commands.tpa.*;
import cz.florixak.survival.command.commands.vip.*;
import cz.florixak.survival.command.commands.spawn.DelSpawn;
import cz.florixak.survival.command.commands.spawn.SetSpawn;
import cz.florixak.survival.command.commands.spawn.Spawn;
import cz.florixak.survival.command.commands.warp.DelWarp;
import cz.florixak.survival.command.commands.warp.SetWarp;
import cz.florixak.survival.command.commands.warp.Warp;
import cz.florixak.survival.command.commands.warp.Warps;
import cz.florixak.survival.config.Messages;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    // https://github.com/Vexentric/CommandManager/blob/master/src/com/vexentric/commandmanager/manager/Command.java

    private ArrayList<Command> commands;

    private Survival plugin;

    public CommandManager(Survival plugin) {
        this.plugin = plugin;
        commands = new ArrayList<Command>();
        addCommand(new Money(plugin));
        addCommand(new Shop(plugin));
        addCommand(new Jobs(plugin));
        addCommand(new Kits(plugin));
        addCommand(new Arena(plugin));
        addCommand(new Roulette(plugin));
        addCommand(new Fly(plugin));
        addCommand(new Stats(plugin));
        addCommand(new Tp(plugin));
        addCommand(new Coordinates(plugin));
        addCommand(new Heal(plugin));
        addCommand(new Home(plugin));
        addCommand(new DelHome(plugin));
        addCommand(new Homes(plugin));
        addCommand(new SetHome(plugin));
        addCommand(new Spawn(plugin));
        addCommand(new SetSpawn(plugin));
        addCommand(new DelSpawn(plugin));
        addCommand(new Help(plugin));
        addCommand(new Anvil(plugin));
        addCommand(new EnderChest(plugin));
        addCommand(new Workbench(plugin));
        addCommand(new RandomTP(plugin));
        addCommand(new Reload(plugin));
        addCommand(new GameMode(plugin));
        addCommand(new ClearInventory(plugin));
        addCommand(new Warp(plugin));
        addCommand(new SetWarp(plugin));
        addCommand(new DelWarp(plugin));
        addCommand(new Warps(plugin));
        addCommand(new Pay(plugin));
        addCommand(new Tpa(plugin));
        addCommand(new TpBlock(plugin));
        addCommand(new Tpaccept(plugin));
        addCommand(new Tpdeny(plugin));

        registerCommands();
    }

    public void addCommand(Command c) {
        commands.add(c);
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void registerCommands() {
        for (Command c : this.getCommands()) {
            for (String l : c.getAliases()) {
                plugin.getCommand(l).setExecutor(this);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        for (Command c : getCommands()) {
            if (c.getAliases().contains(label.toLowerCase())) {
                if (c.onlyPlayer() && sender instanceof ConsoleCommandSender) {
                    sender.sendMessage("This can be used only by player!");
                    return true;
                }

                if (!meetsRequirements(c, sender)) {
                    sender.sendMessage(Messages.NO_PERM.toString());
                    return false;
                }

                try {
                    c.execute(sender, args);
                } catch (Exception e) {
                    sender.sendMessage(Messages.NO_PERM.toString());
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    public boolean meetsRequirements(Command c, CommandSender s) {
        for (String r : c.getRequirements()) {
            if (!c.hasRequirement(s, r)) {
                return false;
            }
        }
        return true;
    }
}